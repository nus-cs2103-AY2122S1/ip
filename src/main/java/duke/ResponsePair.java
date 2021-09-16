package duke;

/**
 * Send multiple objects from GUI to Duke logic.
 */
public class ResponsePair {
    private final String response;
    private final boolean isExit;

    /**
     * Create an object that encapsulates multiple objects
     */
    public ResponsePair(String response, boolean isExit) {
        this.response = response;
        this.isExit = isExit;
    }

    /**
     * Extracts Response portion.
     *
     * @return String representing Response.
     */
    public String getResponse() {
        return response;
    }

    /**
     * Extracts isExit portion.
     *
     * @return Boolean representing isExit.
     */
    public boolean getIsExit() {
        return isExit;
    }

}
