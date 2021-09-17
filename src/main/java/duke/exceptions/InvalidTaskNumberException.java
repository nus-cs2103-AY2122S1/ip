package duke.exceptions;

public class InvalidTaskNumberException extends DukeException {

    /**
     * InvalidTaskNumberException constructor.
     *
     * @param n Current size of tasklist.
     */
    public InvalidTaskNumberException(int n) {
        super(n > 0
                ? String.format("Sorry, this task index is invalid! Please input an integer between 1-%d", n)
                : "Sorry, this task index is invalid! Please add tasks before using this command");
    }

}
