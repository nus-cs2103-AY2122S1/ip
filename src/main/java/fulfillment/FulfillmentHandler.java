package fulfillment;

import io.InputHandler;
import io.OutputHandler;
import messages.Message;
import messages.MessageConstants;

import java.io.IOException;

/**
 * Handles commands from user.
 *
 * @author kevin9foong
 */
public class FulfillmentHandler {
    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;

    public FulfillmentHandler() {
        this.inputHandler = new InputHandler();
        this.outputHandler = new OutputHandler();
    }

    /**
     * Initializes the Chatbot
     * @throws IOException thrown when an error connecting
     * to input/output stream occurs.
     */
    public void initializeChatbot() throws IOException {
        handleGreeting();

        while(true) {
            String userInput = inputHandler.readInput();
            if (userInput.equals("bye")) {
                handleBye();
                break;
            } else {
                echoCommand(userInput);
            }
        }
    }

    private void handleGreeting() {
        outputHandler.writeMessage(new Message(MessageConstants.GREETING_MESSAGE));
    }

    private void handleBye() {
        outputHandler.writeMessage(new Message(MessageConstants.BYE_MESSAGE));
    }

    private void echoCommand(String messageText) {
        outputHandler.writeMessage(new Message(messageText));
    }
}
