package duke;

public class Response {

    private boolean isBye;
    private String message;

    /**
     * Returns a pair class of isBye and response
     * @param isBye
     * @param response
     */
    public Response(boolean isBye, String message) {
        this.isBye = isBye;
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public boolean isBye() {
        return this.isBye;
    }
}
