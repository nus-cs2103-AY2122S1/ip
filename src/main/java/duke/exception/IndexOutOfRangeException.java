package duke.exception;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 *
 * Description:
 * Class that encapsulates an index out of range exception that might be
 * raised whilst interacting with chatbot, Duke.
 *
 * @author Keith Tan
 */
public class IndexOutOfRangeException extends DukeException {

    /**
     * Constructor for IndexOutOfRangeException
     * Exception when user inputs a integer that is out of the range of the
     * current tasklist
     */
    public IndexOutOfRangeException(int enteredIndex, int listSize) {

        super("Hi, " + enteredIndex + " is not a valid index. List has currently " + listSize + " items.");
        assert enteredIndex > listSize : "File writing is supposed to return true";

    }

}
