package duke.exceptions;

public class InvalidNumberInputException extends DukeException{

    public InvalidNumberInputException() {
        super("Please enter a valid duke.task number");
    }

}
