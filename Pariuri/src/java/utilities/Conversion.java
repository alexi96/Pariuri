package utilities;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conversion {

    public static <T, F> T model(F fo, Class<T> tc) {
        try {
            Class<F> fc = (Class<F>) fo.getClass();
            Constructor<T> tCons = tc.getDeclaredConstructor();
            tCons.setAccessible(true);
            T to = tCons.newInstance();

            Field[] tfs = tc.getDeclaredFields();
            for (Field tf : tfs) {
                if (Modifier.isStatic(tf.getModifiers())) {
                    continue;
                }

                try {
                    Field ff = fc.getDeclaredField(tf.getName());

                    ff.setAccessible(true);
                    tf.setAccessible(true);

                    if (ff.getType().equals(tf.getType())) {
                        tf.set(to, ff.get(fo));
                    } else {
                        Object t = Conversion.model(ff.get(fo), tf.getType());
                        if (t != null) {
                            tf.set(to, t);
                        }
                    }
                } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException e) {
                }
            }
            return to;
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(Conversion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
