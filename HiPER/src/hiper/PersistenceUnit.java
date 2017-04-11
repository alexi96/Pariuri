package hiper;

import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import test.StatisticTypeDB;
import test.UserDB;

public class PersistenceUnit {

    private Connection connection;
    private Statement statement;
    private final HashMap<Class, EntityManager> managers = new HashMap<>();

    public PersistenceUnit() {
    }

    public void connect(String url) throws SQLException {
        this.connection = DriverManager.getConnection(url);
        this.statement = this.connection.createStatement();
    }

    public void connect(String url, String user, String pass) throws SQLException {
        this.connection = DriverManager.getConnection(url, user, pass);
        this.statement = this.connection.createStatement();
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
        EntityManager em = this.managers.get(clazz);

        if (em == null) {
            throw new IllegalArgumentException("No registerd table with this name.");
        }

        String sql = em.selectQl();
        ResultSet rs = this.statement.executeQuery(sql);

        List<E> res = new ArrayList<>();

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

        return res;
    }

    public <E> List<E> select(QueryBy<E> q) throws IllegalArgumentException, SQLException {
        EntityManager em = this.managers.get(q.clazz);

        if (em == null) {
            throw new IllegalArgumentException("No registerd table with this type.");
        }

        String sql = em.selectQl(q);
        ResultSet rs = this.statement.executeQuery(sql);

        List<E> res = new ArrayList<>();

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

        return res;
    }

    public <E> E select(Class<E> type, Object... ids) throws IllegalArgumentException, SQLException {
        EntityManager em = this.managers.get(type);

        if (em == null) {
            throw new IllegalArgumentException("No registerd table with this type.");
        }

        String sql = em.selectQl(ids);
        ResultSet rs = this.statement.executeQuery(sql);

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

    public void create(Object entity) throws IllegalArgumentException, SQLException {
        EntityManager em = this.managers.get(entity.getClass());
        if (em == null) {
            throw new IllegalArgumentException(entity.getClass() + " not a Hiper entity");
        }

        try {
            String sql = em.createQl(entity);
            this.statement.execute(sql);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PersistenceUnit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(Object entity) throws IllegalArgumentException, SQLException {
        EntityManager em = this.managers.get(entity.getClass());
        if (em == null) {
            throw new IllegalArgumentException(entity.getClass() + " not a Hiper entity");
        }

        try {
            String sql = em.deleteQl(entity);
            this.statement.execute(sql);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PersistenceUnit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void edit(Object entity) throws IllegalArgumentException, SQLException {
        EntityManager em = this.managers.get(entity.getClass());
        if (em == null) {
            throw new IllegalArgumentException(entity.getClass() + " not a Hiper entity");
        }

        try {
            String sql = em.updateQl(entity);
            this.statement.executeUpdate(sql);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PersistenceUnit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) throws Exception {
        PersistenceUnit pu = new PersistenceUnit();
        pu.connect("jdbc:mysql://localhost:3306/pariuri", "root", "");
        pu.register(StatisticTypeDB.class);
        pu.register(UserDB.class);

        List<StatisticTypeDB> all = pu.select(StatisticTypeDB.class);
        System.out.println(all);

        QueryBy q = new QueryBy(UserDB.class, "firstName", "password");
        q.parameters("Radu", "pass");
        List<UserDB> users = pu.select(q);
        System.out.println(users);

        System.out.println(pu.select(UserDB.class, 17));
    }
}
