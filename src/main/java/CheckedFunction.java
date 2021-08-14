public interface CheckedFunction<T,R> {
    R apply(T t) throws DukeException;
}
