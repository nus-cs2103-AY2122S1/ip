public class MissingDateException extends DukeException{
    MissingDateException() {
        super();
    }

    @Override
    public String toString() {
        return super.toString() + "Sorry can't have missing date for task type!";
    }
}
