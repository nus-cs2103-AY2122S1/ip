package duke.util;

/**
 * Represents how Duke deals with interactions with the user.
 *
 * @author Javier Phon Zhee Kai.
 * @version CS2103T AY21/22 Sem 1.
 */
public class Ui {
    /**
     * Returns a welcome message everytime Duke is activated.
     * @return A String representing a welcome message.
     */
    public String showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return logo + "\n" + "Hello! I am Duke. What can I do for you?";
    }

    /**
     * Returns am error messages.
     * @param errorMessage A string representing the error message.
     * @return A string representing the error message.
     */
    public String showError(String errorMessage) {
        return errorMessage;
    }

    /**
     * Returns the an error message from failed file loading.
     * @return A string representing the error message.
     */
    public String showLoadingError() {
        return "Something went wrong! Seems like I'm unable to load your file!";
    }

    /**
     * Returns the an error message from failed file saving.
     * @return A string representing the error message.
     */
    public String showSavingError() {
        return "Something went wrong! Seems like I'm unable to save to your file!";
    }

    /**
     * Returns an exit message before Duke exits.
     * @return A string representing an exit message.
     */
    public String showExit() {
        return "Bye. Hope to see you again soon!";
    }
}
