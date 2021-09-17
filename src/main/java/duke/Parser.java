package duke;

/**
 * An abstract class representing a parser.
 *
 * @param <T> The type of Object for the string to be parsed into.
 */
public abstract class Parser<T> {

    /**
     * Attempts the string into an object of type T.
     *
     * @param s The string to be parsed.
     * @return An object of type T if the string can be parsed, null otherwise.
     * @throws DukeException If the string cannot be parsed.
     */
    public abstract T parse(String s) throws DukeException;
}
