package duke;

/**
 * Represents and describes a response from Duke.
 */
public class DukeResponse {
    private boolean isException;
    private String response;

    /**
     * Constructor for DukeResponse.
     *
     * @param response Response String.
     * @param isException Boolean indicating whether response is indicating an error or not.
     */
    public DukeResponse(String response, Boolean isException) {
        this.isException = isException;
        this.response = response;
    }

    /**
     * Returns a boolean indicating whether this DukeResponse contains a response that indicates an error.
     *
     * @return Boolean indicating whether this DukeResponse contains a response that indicates an error.
     */
    public boolean isError() {
        return this.isException;
    }

    /**
     * Returns the response string contained within this DukeResponse.
     *
     * @return Response string contained within this DukeResponse.
     */
    public String getResponse() {
        return response;
    }

    /**
     * Indicates whether another object is equals to this DukeResponse.
     *
     * @param obj Other object to be compared to.
     * @return A boolean indicating whether the other object is equal to this DukeResponse.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DukeResponse) {
            DukeResponse other = (DukeResponse) obj;
            boolean isResponseSame = this.response.equals(other.response);
            boolean isExceptionSame = this.isException == other.isException;
            return isResponseSame && isExceptionSame;
        }
        return false;
    }
}
