package Exceptions;

import Exceptions.DukeException;

public class IndexNotInListException extends DukeException {
    public IndexNotInListException(String message) {
        super(message);
    }
}
