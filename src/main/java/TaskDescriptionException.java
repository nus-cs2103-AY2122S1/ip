public class TaskDescriptionException extends DukeException{
    TaskDescriptionException() {
        super();
    }

    @Override
    public String toString() {
        return super.toString() + "Sorry can't have missing description for task!";
    }
}
