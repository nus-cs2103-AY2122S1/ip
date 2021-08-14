@FunctionalInterface
public interface QuadFunction<P, Q, R, S> {
    public void apply (P p, Q q, R r, S s) throws LifelineException;
}
