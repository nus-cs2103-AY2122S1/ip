package agent.messages;

/**
 * Class is responsible for generating message to say bye to user.
 *
 * @author kevin9foong
 */
public class ByeMessage extends Message {

    /**
     * Constructs an instance of <code>ByeMessage</code> which
     * consists of chat bot response to user's 'bye' command.
     */
    public ByeMessage() {
        super(MessageConstants.MESSAGE_BYE);
    }
}
