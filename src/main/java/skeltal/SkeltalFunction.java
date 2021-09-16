package skeltal;

/**
 * The interface Skeltal function that is used to wrap a function.
 *
 * @param <T> The type parameter that apply will accept as a parameter.
 * @param <R> The type parameter that apply will return.
 */
public interface SkeltalFunction<T,R> {
    /**
     * Apply r.
     *
     * @param t The paramater of type T.
     * @return The return value of type R.
     * @throws SkeltalException the skeltal exception.
     */
    public R apply(T t) throws SkeltalException;
}
