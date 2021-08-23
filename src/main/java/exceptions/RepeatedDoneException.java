package exceptions;

import exceptions.DukeException;

public class RepeatedDoneException extends DukeException {

    public RepeatedDoneException() {
        super("The task has already been completed");
    }

}
