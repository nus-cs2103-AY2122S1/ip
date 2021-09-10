package duke.exception;

public class InvalidDateTimeException extends DukeException {
    public InvalidDateTimeException() {
        super("The format of your command is incorrect! It should be <task type>/by " + "<yyyy-mm-dd HHmm>");
    }
    
    public String toString() {
        return super.toString();
    }
}
