package exceptions;

import exceptions.DukeException;

public class InvalidNumberInputException extends DukeException {

    public InvalidNumberInputException() {
        super("Please enter a valid task number");
    }

}
