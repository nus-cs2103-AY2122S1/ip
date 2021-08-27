package Exceptions;

import Exceptions.DukeException;

public class WrongInputException extends DukeException {
    public WrongInputException(String message) {
        super(message);
    }
}
