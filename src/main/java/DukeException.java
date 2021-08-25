/**
 * Class that handles all exceptions specific to Duke.
 *
 * @author Ruth Poh (Lab 10H)
 */
public class DukeException extends Exception {
    protected String linebreak = "~~*********___\\(owo)/___\\(owo)/___*********~~";

    /**
     * Constructor to initialize all DukeExceptions.
     *
     * @param message To be printed when error in Main (Duke.java) occurs.
     */
    DukeException(String message) {
        super(message + '\n');
    }
}

/**
 * Occurs when Duke doesn't recognise the input.
 */
class InvalidInputException extends DukeException {
    InvalidInputException() {
        super ("Sowwy, thiws commandw iswn't supporwted! TwT");
    }
}


class IncorrectInputException extends DukeException {
    /**
     * Occurs when Duke recognises keyword, but input after it is invalid.
     * @param keyword keyword input recognised by Duke.
     * @param suggestion suggestion made to user.
     */
    IncorrectInputException(String keyword, String suggestion) {
        super ("Ohw no! Youw cawn't use " + keyword + " likw that! Twy " + suggestion + " inswead!");
    }
}

class MissingInputException extends DukeException {
    /**
     * Occurs when Duke recognises keyword, but there is no input after it.
     * @param keyword keyword input recognised by Duke.
     */
    MissingInputException(String keyword) {
        super ("Oopsie uwu! Youw cawn't use " + keyword + " withoutw a descwiption forw it!");
    }
}

class MissingNoException extends DukeException {
    /**
     * Occurs when Duke recognises keyword, but input (number) after it is invalid.
     * @param keyword keyword input recognised by Duke.
     */
    MissingNoException(String keyword) {
        super ("Thwere's no suchw taskw! Pwease enterw a *validw* numbewr after '"+ keyword + "'!");
    }
}

class ReadError extends DukeException {
    /**
     * Occurs when Duke tries to read txt file for Task list, but comes up empty.
     */
    ReadError() {
        super("Uwu! File not found!");
    }
}

class TaskDoneError extends DukeException {
    TaskDoneError() {
        super ("Looksw wike thisw taskw is alweady done! That's gweat!\n");
    }
}