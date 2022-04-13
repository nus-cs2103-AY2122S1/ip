package duke;

public class IndexOutOfBoundException extends DukeException {
    public IndexOutOfBoundException() {
        super();
    }

    /**
     * Return string message specific for the exception.
     *
     * @return String message.
     */
    @Override
    public String toString() {
        return super.toString() + "There is no such index in the task list";
    }
}
