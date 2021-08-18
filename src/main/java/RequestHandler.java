import exception.BotException;

/**
 * RequestHandler is the controller for handling of requests.
 */
public abstract class RequestHandler {
    private static final String name = "Catobot";
    private static final String banner = "(=^^=)(=^^=)(=^^=)(=^^=)";

    private static final String greeting
            = "Hello I am " + name + " (>^^<)\n    What can I do for you meow?";
    private static final String byeMessage
            = "Bye meow! I will always wait here meow(>^^<)";

    protected RequestHandler() {
        initialize();
    }

    protected void respond(String message) {
        String s = String.format("    %s\n    %s\n    %s", banner, message, banner);
        System.out.println(s);
    };

    /**
     * Send an initialization message.
     */
    public void initialize() {
        respond(greeting);
    }

    /**
     * Send an exit message.
     */
    public void exit() {
        respond(byeMessage);
    }

    /**
     * Decide the response to the requests.
     * @param command The action word at the start of request.
     * @param request The full request message from a line of input.
     * @throws BotException if the request is invalid.
     */
    public abstract void decideResponse(
            Command command, String request) throws BotException;
}
