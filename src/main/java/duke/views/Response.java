package duke.views;

public class Response {
    public enum ResponseType {
        INFO,
        ERROR;
    }

    private String responseMessage;
    private ResponseType responseType;

    /**
     * Creates a Response object with the specified message and type.
     *
     * @param responseMessage Text message.
     * @param responseType Category of response.
     */
    public Response(String responseMessage, ResponseType responseType) {
        this.responseMessage = responseMessage;
        this.responseType = responseType;
    }

    /**
     * Creates a Response object with the given message.
     *
     * @param responseMessage Text message.
     * @return Response object with type defaulting to info.
     */
    public static Response withMessage(String responseMessage) {
        return new Response(responseMessage, ResponseType.INFO);
    }

    public String getMessage() {
        return this.responseMessage;
    }

    public ResponseType getResponseType() {
        return this.responseType;
    }
}
