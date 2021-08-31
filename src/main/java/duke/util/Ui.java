package duke.util;

/**
 * Represents how Duke deals with interactions with the user.
 *
 * @author Javier Phon Zhee Kai.
 * @version CS2103T AY21/22 Sem 1.
 */
public class Ui {
    /**
     * Returns an error message as a string.
     *
     * @param errorMessage The error message to be returned.
     * @return A string representing the error message.
     */
    public String showError(String errorMessage) {
        return errorMessage;
    }

    /**
     * Returns an error message from failed file loading.
     *
     *  @return A string representing the error message.
     */
    public String showLoadingError() {
        return "Something went wrong! Seems like I'm unable to load your file!";
    }

    /**
     * Returns an error message from failed file saving.
     *
     * @return A string representing the error message.
     */
    public String showSavingError() {
        return "Something went wrong! Seems like I'm unable to save to your file!";
    }

    /**
     * Returns an exit message before Duke exits.
     *
     * @return A string representing the exit message.
     */
    public String showExit() {
        return "Bye. Hope to see you again soon!";
    }
}
