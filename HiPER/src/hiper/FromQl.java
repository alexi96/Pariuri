package hiper;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface FromQl<D> {

    D value(ResultSet result, String field) throws SQLException;
}
