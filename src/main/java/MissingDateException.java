public class MissingDateException extends DukeException{

    MissingDateException() {
        super();
    }

    /**
     * Return string message specific for the exception.
     *
     * @return string message.
     */
    @Override
    public String toString() {
        return super.toString() + "Sorry can't have missing date for task type!";
    }
}
