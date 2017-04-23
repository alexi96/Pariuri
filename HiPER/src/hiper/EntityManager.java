package hiper;

import hiper.anotations.HiperField;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import hiper.anotations.HiperEntity;
import hiper.anotations.HiperPrimaryKey;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

public class EntityManager<E> {

    private static final HashMap<Class, ToQl> TO_QLS = new HashMap<>();
    private static final HashMap<Class, FromQl> FROM_QLS = new HashMap<>();
    private static final SimpleDateFormat DATABASE_FORMAT = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

    static {
        EntityManager.TO_QLS.put(String.class, (v) -> "'" + v + "'");
        ToQl simple = (v) -> "" + v;
        EntityManager.TO_QLS.put(Long.class, simple);
        EntityManager.TO_QLS.put(Integer.class, simple);
        EntityManager.TO_QLS.put(Short.class, simple);
        EntityManager.TO_QLS.put(Byte.class, simple);
        EntityManager.TO_QLS.put(Float.class, simple);
        EntityManager.TO_QLS.put(float.class, simple);
        EntityManager.TO_QLS.put(Double.class, simple);
        EntityManager.TO_QLS.put(Date.class, (v) -> {
            if (v instanceof Date) {
                return "'" + DATABASE_FORMAT.format(v) + "'";
            } else {
                return "'" + v + "'";
            }
        });

        EntityManager.FROM_QLS.put(String.class, (r, f) -> r.getString(f));
        EntityManager.FROM_QLS.put(Long.class, (r, f) -> r.getLong(f));
        EntityManager.FROM_QLS.put(Integer.class, (r, f) -> r.getInt(f));
        EntityManager.FROM_QLS.put(Short.class, (r, f) -> r.getShort(f));
        EntityManager.FROM_QLS.put(Byte.class, (r, f) -> r.getByte(f));
        EntityManager.FROM_QLS.put(Float.class, (r, f) -> r.getFloat(f));
        EntityManager.FROM_QLS.put(float.class, (r, f) -> r.getFloat(f));
        EntityManager.FROM_QLS.put(Date.class, (r, f) -> r.getDate(f));
        EntityManager.FROM_QLS.put(Double.class, (r, f) -> r.getDouble(f));
    }

    private Class<E> clazz;
    private String tableName;
    private final ArrayList<Field> keys = new ArrayList<>();
    private final HashMap<Field, String> fields = new HashMap<>();

    public Class<E> getClazz() {
        return clazz;
    }

    public String getTableName() {
        return tableName;
    }

    protected void load(Class<E> entity) throws IllegalArgumentException {
        HiperEntity tn = entity.getDeclaredAnnotation(HiperEntity.class);

        if (tn == null) {
            throw new IllegalArgumentException(entity + " is not a Hiper entity");
        }

        this.clazz = (Class<E>) entity.getClass();
        if (tn.value().isEmpty()) {
            this.tableName = entity.getSimpleName();
        } else {
            this.tableName = tn.value();
        }

        this.fields.clear();
        this.keys.clear();

        Field[] fs = entity.getDeclaredFields();
        for (Field f : fs) {
            if (Modifier.isStatic(f.getModifiers())) {
                continue;
            }

            HiperField fn = f.getDeclaredAnnotation(HiperField.class);
            if (fn == null) {
                continue;
            }

            if (fn.value().isEmpty()) {
                this.fields.put(f, f.getName());
            } else {
                this.fields.put(f, fn.value());
            }

            HiperPrimaryKey key = f.getDeclaredAnnotation(HiperPrimaryKey.class);
            if (key != null) {
                this.keys.add(f);
            }
        }
    }

    protected String createQl(E bean) throws IllegalArgumentException, IllegalAccessException {
        StringBuilder ql = new StringBuilder("insert into `");
        ql.append(tableName);
        ql.append("` (");

        Collection<Field> fs = new ArrayList<>(this.fields.keySet());
        fs.removeAll(this.keys);
        fs.forEach((f) -> {
            ql.append("`");
            ql.append(this.fields.get(f));
            ql.append("`,");
        });
        if (!fs.isEmpty()) {
            ql.deleteCharAt(ql.length() - 1);
        }
        ql.append(") values (");

        for (Field f : fs) {
            ToQl bds = EntityManager.TO_QLS.get(f.getType());
            f.setAccessible(true);
            ql.append(bds.value(f.get(bean)));
            ql.append(",");
        }
        if (!fs.isEmpty()) {
            ql.deleteCharAt(ql.length() - 1);
        }
        ql.append(")");

        return ql.toString();
    }

    protected String deleteQl(E bean) throws IllegalArgumentException, IllegalAccessException {
        StringBuilder ql = new StringBuilder("DELETE FROM `" + this.tableName + "` where ");
        Collection<Field> fs = this.keys;
        for (Field f : fs) {
            ql.append("`");
            ql.append(this.fields.get(f));
            ql.append("`=");
            ToQl bds = EntityManager.TO_QLS.get(f.getType());
            f.setAccessible(true);
            ql.append(bds.value(f.get(bean)));
            ql.append(",");
        }
        if (!fs.isEmpty()) {
            ql.deleteCharAt(ql.length() - 1);
        }
        return ql.toString();
    }

    protected String updateQl(E bean) throws IllegalArgumentException, IllegalAccessException {
        StringBuilder ql = new StringBuilder("update `" + this.tableName + "` set ");

        Collection<Field> fs = new ArrayList<>(this.fields.keySet());
        fs.removeAll(this.keys);
        for (Field f : fs) {
            ql.append("`");
            ql.append(this.fields.get(f));
            ql.append("`=");
            ToQl bds = EntityManager.TO_QLS.get(f.getType());
            f.setAccessible(true);
            ql.append(bds.value(f.get(bean)));
            ql.append(",");
        }
        if (!fs.isEmpty()) {
            ql.deleteCharAt(ql.length() - 1);
        }

        ql.append(" where ");
        fs = this.keys;
        for (Field f : fs) {
            ql.append("`");
            ql.append(this.fields.get(f));
            ql.append("`=");
            ToQl bds = EntityManager.TO_QLS.get(f.getType());
            f.setAccessible(true);
            ql.append(bds.value(f.get(bean)));
            ql.append(",");
        }
        if (!fs.isEmpty()) {
            ql.deleteCharAt(ql.length() - 1);
        }

        return ql.toString();
    }

    protected String selectQl() {
        String ql = "select * from `" + this.tableName + "`";
        return ql;
    }

    protected String selectQl(Object... ids) throws IllegalArgumentException {
        if (ids.length != this.keys.size()) {
            throw new IllegalArgumentException("Entity`s keys and parameters differ in length!");
        }

        StringBuilder ql = new StringBuilder("select * from `" + this.tableName + "` where ");
        int index = 0;
        for (Field k : this.keys) {
            ql.append("`");
            ql.append(this.fields.get(k));
            ql.append("`=");

            ToQl bds = EntityManager.TO_QLS.get(k.getType());//Not in this context
            ql.append(bds.value(ids[index]));
            ql.append(" and ");

            ++index;
        }
        ql.delete(ql.length() - 5, ql.length());

        return ql.toString();
    }

    protected String selectQl(QueryBy q) {
        StringBuilder ql = new StringBuilder("select * from `" + this.tableName + "`");

        if (q.values.isEmpty()) {
            return ql.toString();
        }

        ql.append(" where ");
        Set<String> fss = q.values.keySet();
        fss.forEach(fstr -> {
            Field f = this.fields.keySet().stream()
                    .filter((fld) -> fld.getName().equals(fstr))
                    .findFirst()
                    .orElse(null);

            ql.append("`");
            ql.append(this.fields.get(f));
            ql.append("`=");

            ToQl bds = EntityManager.TO_QLS.get(f.getType());
            ql.append(bds.value(q.values.get(fstr)));
            ql.append(" and ");
        });
        ql.delete(ql.length() - 5, ql.length());

        return ql.toString();
    }

    protected E select(ResultSet res, E e) throws SQLException, IllegalArgumentException, IllegalAccessException {
        Collection<Field> fs = this.fields.keySet();

        for (Field f : fs) {
            FromQl ql = EntityManager.FROM_QLS.get(f.getType());
            f.setAccessible(true);
            f.set(e, ql.value(res, this.fields.get(f)));
        }
        return e;
    }

    public static SimpleDateFormat getDatabaseFormat() {
        return DATABASE_FORMAT;
    }
}
