package duke;

/**
 * Class that handles all exceptions specific to Duke.
 *
 * @author Ruth Poh
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

class LoadingFileError extends DukeException {
    /**
     * Occurs when Duke tries to read txt file for Task list, but comes up empty.
     */
    LoadingFileError(String message) {
        super(message);
    }
}

class TaskDoneError extends DukeException {
    TaskDoneError() {
        super ("Looksw wike thisw taskw is alweady done! That's gweat!\n");
    }
}

class SaveFileError extends DukeException {
    SaveFileError() {
        super ("Canw't save to a filew that doesn't exist, sorry!");
    }
}

class LoadFileError extends DukeException {
    LoadFileError() {
        super ("Oops! Somethingw's wrong with the text in thew saved file! "
                + "You may have to dewete itw!");
    }
}

class SaveDirectoryError extends DukeException {
    SaveDirectoryError(){
        super ("Oh now! I guess the diwectowwy doesn't exist.");
    }
}



