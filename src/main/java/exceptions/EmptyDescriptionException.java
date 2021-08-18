package exceptions;

import exceptions.DukeException;

public class EmptyDescriptionException extends DukeException {
    public EmptyDescriptionException(String taskCategory) {
        super("☹ OOPS!!! The description of a " + taskCategory + " cannot be empty.");
    }
}