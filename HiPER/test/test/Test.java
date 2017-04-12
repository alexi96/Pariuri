package test;

import hiper.PersistenceUnit;
import hiper.QueryBy;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Test {

    public static void main(String[] args) {
        try {
            PersistenceUnit pu = new PersistenceUnit();
            pu.connect("jdbc:mysql://localhost:3306/pariuri", "root", "");
            pu.register(StatisticTypeDB.class);
            pu.register(UserDB.class);

            for (int i = 0; i < 10; i++) {
                pu.create(new StatisticTypeDB(i, "name " + (8 * i)));
            }

            System.out.println(pu.select(StatisticTypeDB.class));

            QueryBy<StatisticTypeDB> q = new QueryBy<>(StatisticTypeDB.class, "name");
            q.parameters("name 64");
            StatisticTypeDB t = pu.select(q).get(0);
            System.out.println(t);

            t.setName("name edited");
            pu.edit(t);

            t = pu.select(StatisticTypeDB.class, t.getId() - 5);
            t.setName("name edited again");
            pu.edit(t);

            System.out.println(pu.select(StatisticTypeDB.class));

            QueryBy uq = new QueryBy(UserDB.class);
            uq.parameter("email", "alex_i96@yahoo.com");
            uq.parameter("password", "pass");
            System.out.println(pu.select(uq));
        } catch (SQLException | IllegalArgumentException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
