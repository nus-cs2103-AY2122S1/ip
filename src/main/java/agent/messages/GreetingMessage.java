package agent.messages;

/**
 * Class is responsible for generating message to greet user.
 *
 * @author kevin9foong
 */
public class GreetingMessage extends Message {

    /**
     * Constructs an instance of <code>GreetingMessage</code> which
     * greets the user.
     */
    public GreetingMessage() {
        super(MessageConstants.MESSAGE_GREETING);
    }
}
