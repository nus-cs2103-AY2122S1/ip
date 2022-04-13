package duke;

public class Ui {

    private String currentResponse;

    /**
     * Constructor for class Ui
     */
    public Ui() {
    }

    /**
     * Prints out the response
     *
     * @param dukeReply response to be printed
     */
    public void reply(String dukeReply) {
        currentResponse = dukeReply;
    }

    public String getCurrentResponse() {
        return this.currentResponse;
    }

    /**
     * Prints out the error message
     *
     * @param message error message to be printed
     */
    public void showLoadingError(String message) {
        this.reply(message);
    }


    /**
     * Prints the closing String
     */
    public void showClosingMessage() {
        this.reply("Good bye, see you soon!");
    }
}
