public class DukeException extends Exception {
    protected String linebreak = "~~*********___\\(owo)/___\\(owo)/___*********~~\n\n";

    public DukeException(String message) {
        super("~~*********___\\(owo)/___\\(owo)/___*********~~\n\n"
                + message
                + "~~*********___\\(owo)/___\\(owo)/___*********~~\n\n");
    }

    public DukeException(String message, Throwable cause) {
        super (message, cause);
    }


}
