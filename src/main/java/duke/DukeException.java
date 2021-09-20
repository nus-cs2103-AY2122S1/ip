package duke;

/**
 * Basic unique exception for Duke chatbot.
 */
public class DukeException extends RuntimeException{
    public DukeException(String msg) {
        super(msg);
    }
}
