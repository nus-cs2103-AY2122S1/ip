@FunctionalInterface
public interface QuadFunction<P, Q, R, S, T> {
    public T apply (P p, Q q, R r, S s) throws LifelineException;
}
