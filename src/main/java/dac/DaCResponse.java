package dac;

/**
 * This class represents Duke's response to the user input.
 * The class contains the relevant fields for the GUI to output.
 */
public class DaCResponse {
    private final String response;
    private final String listToPrint;
    private final Boolean isErrorMessage;

    /**
     * Constructor.
     *
     * @param response Duke's response to the user input.
     * @param listToPrint The appropriate list of tasks to print.
     * @param isErrorMessage Whether Duke's response is an error message
     */
    public DaCResponse(String response, String listToPrint, boolean isErrorMessage) {
        this.response = response;
        this.listToPrint = listToPrint;
        this.isErrorMessage = isErrorMessage;
    }

    /**
     * Gets Duke's response.
     *
     * @return Duke's response.
     */
    public String getResponse() {
        return response;
    }

    /**
     * Gets the appropriate list of tasks to print.
     *
     * @return The list of tasks to print.
     */
    public String getListToPrint() {
        return listToPrint;
    }

    /**
     * Checks if Duke's response is an error message.
     *
     * @return True if Duke's response is an error message; false otherwise.
     */
    public Boolean isErrorMessage() {
        return isErrorMessage;
    }
}
