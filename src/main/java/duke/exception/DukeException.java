package duke.exception;

import duke.Response;

/**
 * Encapsulates all exceptions that can occur in Duke bot.
 */
public class DukeException extends IllegalArgumentException {
    private Response response;

    public DukeException(String message) {
        this.response = new Response(message);
    }

    /**
     * Get the response to be printed.
     * 
     * @return response
     */
    public Response getResponse() {
        return this.response;
    }

}