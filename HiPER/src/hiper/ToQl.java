package hiper;

@FunctionalInterface
public interface ToQl<D> {
    String value(D value);
}
