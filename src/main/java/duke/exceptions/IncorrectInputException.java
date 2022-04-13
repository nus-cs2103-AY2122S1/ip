package duke.exceptions;

public class IncorrectInputException extends DukeException {
    /**
     * Occurs when Duke recognises keyword, but input after it is invalid.
     * @param keyword keyword input recognised by Duke.
     * @param suggestion suggestion made to user.
     */
    public IncorrectInputException(String keyword, String suggestion) {
        super("Ohw no! Youw cawn't use " + keyword + " likw that! Twy " + suggestion + " inswead!");
    }
}