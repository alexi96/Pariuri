package hiper;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersistenceUnit {

    private Connection connection;
    private Statement statement;
    private final HashMap<Class, EntityManager> managers = new HashMap<>();

    public PersistenceUnit() {
    }

    public PersistenceUnit(String url) throws SQLException {
        this.connect(url);
    }

    public PersistenceUnit(String url, String user, String pass) throws SQLException {
        this.connect(url, user, pass);
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public final void connect(String url) throws SQLException {
        this.connection = DriverManager.getConnection(url);
    }

    public final void connect(String url, String user, String pass) throws SQLException {
        this.connection = DriverManager.getConnection(url, user, pass);
    }

    public void register(Class entity) {
        if (this.managers.containsKey(entity)) {
            return;
        }

        EntityManager em = new EntityManager();
        em.load(entity);
        this.managers.put(entity, em);
    }

    public <E> List<E> select(Class<E> clazz) throws IllegalArgumentException, SQLException {
        return this.select(clazz, this.statement);
    }

    public <E> List<E> select(Class<E> clazz, Statement st) throws IllegalArgumentException, SQLException {
        EntityManager em = this.managers.get(clazz);

        if (em == null) {
            throw new IllegalArgumentException("No registerd table with this name.");
        }

        String sql = em.selectQl();

        List<E> res = new ArrayList<>();

        try (ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                try {
                    Constructor<E> cons = clazz.getDeclaredConstructor();
                    cons.setAccessible(true);
                    E r = cons.newInstance();
                    em.select(rs, r);
                    res.add(r);
                } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | InvocationTargetException ex) {
                    Logger.getLogger(PersistenceUnit.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return res;
    }

    public <E> List<E> selectNative(Class<E> clazz, String sql) throws IllegalArgumentException, SQLException {
        return this.selectNative(clazz, sql, this.statement);
    }

    public <E> List<E> selectNative(Class<E> clazz, String sql, Statement st) throws IllegalArgumentException, SQLException {
        EntityManager em = this.managers.get(clazz);

        if (em == null) {
            throw new IllegalArgumentException("No registerd table with this name.");
        }

        List<E> res = new ArrayList<>();
        try (ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                try {
                    Constructor<E> cons = clazz.getDeclaredConstructor();
                    cons.setAccessible(true);
                    E r = cons.newInstance();
                    em.select(rs, r);
                    res.add(r);
                } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | InvocationTargetException ex) {
                    Logger.getLogger(PersistenceUnit.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return res;
    }

    public <E> List<E> select(QueryBy<E> q) throws IllegalArgumentException, SQLException {
        return this.select(q, this.statement);
    }

    public <E> List<E> select(QueryBy<E> q, Statement st) throws IllegalArgumentException, SQLException {
        EntityManager em = this.managers.get(q.clazz);

        if (em == null) {
            throw new IllegalArgumentException("No registerd table with this type.");
        }

        String sql = em.selectQl(q);

        List<E> res = new ArrayList<>();
        try (ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                try {
                    Constructor<E> cons = q.clazz.getDeclaredConstructor();
                    cons.setAccessible(true);
                    E r = cons.newInstance();
                    em.select(rs, r);
                    res.add(r);
                } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | InvocationTargetException ex) {
                    Logger.getLogger(PersistenceUnit.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return res;
    }

    public <E> E select(Class<E> type, Object... ids) throws IllegalArgumentException, SQLException {
        return this.select(type, this.statement, ids);
    }

    public <E> E select(Class<E> type, Statement st, Object... ids) throws IllegalArgumentException, SQLException {
        EntityManager em = this.managers.get(type);

        if (em == null) {
            throw new IllegalArgumentException("No registerd table with this type.");
        }

        String sql = em.selectQl(ids);

        try (ResultSet rs = st.executeQuery(sql)) {

            E res = null;

            while (rs.next()) {
                try {
                    Constructor<E> cons = type.getDeclaredConstructor();
                    cons.setAccessible(true);
                    res = cons.newInstance();
                    em.select(rs, res);
                } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | InvocationTargetException ex) {
                    Logger.getLogger(PersistenceUnit.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            return res;
        }
    }

    public void create(Object entity) throws IllegalArgumentException, SQLException {
        this.create(entity, this.statement);
    }

    public void create(Object entity, Statement st) throws IllegalArgumentException, SQLException {
        EntityManager em = this.managers.get(entity.getClass());
        if (em == null) {
            throw new IllegalArgumentException(entity.getClass() + " not a Hiper entity");
        }

        try {
            String sql = em.createQl(entity);
            st.execute(sql);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PersistenceUnit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(Object entity) throws IllegalArgumentException, SQLException {
        this.delete(entity, this.statement);
    }

    public void delete(Object entity, Statement st) throws IllegalArgumentException, SQLException {
        EntityManager em = this.managers.get(entity.getClass());
        if (em == null) {
            throw new IllegalArgumentException(entity.getClass() + " not a Hiper entity");
        }

        try {
            String sql = em.deleteQl(entity);
            st.execute(sql);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PersistenceUnit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void edit(Object entity) throws IllegalArgumentException, SQLException {
        this.edit(entity, this.statement);
    }

    public void edit(Object entity, Statement st) throws IllegalArgumentException, SQLException {
        EntityManager em = this.managers.get(entity.getClass());
        if (em == null) {
            throw new IllegalArgumentException(entity.getClass() + " not a Hiper entity");
        }

        try {
            String sql = em.updateQl(entity);
            st.executeUpdate(sql);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PersistenceUnit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Deprecated
    public int lastId(Statement st) throws SQLException {
        try (ResultSet res = st.executeQuery("SElect LAST_INSERT_ID() from dual")) {
            if (res.next()) {
                return res.getInt(1);
            }
        }
        return 0;
    }
}
