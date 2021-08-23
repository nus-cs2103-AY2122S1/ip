package exceptions;

import exceptions.DukeException;

public class EmptyDescriptionException extends DukeException {

    public EmptyDescriptionException(String desc) {
        super(desc);
    }

}
