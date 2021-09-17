package duke;

public class Response {

    private boolean isTerminate;
    private boolean hasError;
    private String message;

    /**
     * Returns a pair class of isTerminate and response
     * @param isTerminate
     * @param response
     */
    public Response(boolean isTerminate, boolean hasError, String message) {
        this.isTerminate = isTerminate;
        this.hasError = hasError;
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public boolean isTerminate() {
        return this.isTerminate;
    }

    public boolean hasError() {
        return this.hasError;
    }
}
