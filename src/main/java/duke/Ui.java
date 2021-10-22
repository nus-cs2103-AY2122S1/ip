package duke;

import duke.exception.DukeException;

public class Ui {

    private String message = "";

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Howdy! The name's\n" + logo + "\nWhat can I do for ya?");
    }

    /**
     * Sets the next response to user as a given String.
     * @param message message to be printed to console.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Appends a string to the next message.
     * @param appendix String to append to existing message.
     */
    public void appendMessage(String appendix) {
        this.message += appendix;
    }


    public String getMessage() {
        return this.message;
    }

    /**
     * Shows a given exception's error message to user.
     * @param error Error to show to user.
     */
    public void showError(DukeException error) {
        setMessage(error.getMessage());
    }

}