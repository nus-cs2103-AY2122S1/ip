package Exceptions;

import Exceptions.DukeException;

public class NoPreviousFileException extends DukeException {
    public NoPreviousFileException(String message) {
        super(message);
    }
}
