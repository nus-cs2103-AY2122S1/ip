public class DukeException extends Exception {
    protected String linebreak = "~~*********___\\(owo)/___\\(owo)/___*********~~";

    public DukeException(String message) {
        super("~~*********___\\(owo)/___\\(owo)/___*********~~\n\n"
                + message
                + "\n~~*********___\\(owo)/___\\(owo)/___*********~~");
    }

    public DukeException(String message, Throwable cause) {
        super (message, cause);
    }
}
