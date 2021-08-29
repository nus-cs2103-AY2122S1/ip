package duke.exceptions;

public class WrongDateFormatException extends DukeException{
    public WrongDateFormatException() {
        super("Date format needs to be of the form dd-MM-yyyy or dd/MM/yyyy!");
    }
}
