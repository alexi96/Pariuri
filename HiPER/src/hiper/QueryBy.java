package hiper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class QueryBy<E> {

    protected Class<E> clazz;
    protected final LinkedHashMap<String, String> values = new LinkedHashMap<>();

    protected Object clone() {
        return null;
    }
    
    
    public QueryBy() {
    }

    public QueryBy(Class<E> clazz) {
        this.clazz = clazz;
    }

    public QueryBy(Class<E> clazz, String... qs) {
        this.clazz = clazz;
        this.queryes(qs);
    }

    public QueryBy(String... qs) {
        this.queryes(qs);
    }

    public Class<E> getClazz() {
        return clazz;
    }

    public void setClazz(Class<E> clazz) {
        this.clazz = clazz;
    }

    public final void queryes(String... qs) {
        for (String q : qs) {
            this.values.put(q, null);
        }
    }

    public void parameters(String... ps) throws IllegalArgumentException {
        if (ps.length > this.values.size()) {
            throw new IllegalArgumentException("Too many parameters. Too few fields.");
        }

        int index = 0;
        List<String> fs = new ArrayList<>(this.values.keySet());
        for (String p : ps) {
            this.values.put(fs.get(index), p);
            ++index;
        }
    }

    public void parameter(String field, String value) {
        this.values.put(field, value);
    }
}
