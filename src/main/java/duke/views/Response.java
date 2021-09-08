package duke.views;

public class Response {
    public enum ResponseType {
        INFO,
        SUCCESS,
        ERROR;
    }

    private String responseMessage;
    private ResponseType responseType;

    public Response(String responseMessage, ResponseType responseType) {
        this.responseMessage = responseMessage;
        this.responseType = responseType;
    }

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
