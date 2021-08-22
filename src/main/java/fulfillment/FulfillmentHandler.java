package fulfillment;

import command.Command;
import exceptions.DukeException;
import exceptions.InvalidTaskNumberException;
import io.UserInputHandler;
import io.UserOutputHandler;
import messages.ByeMessage;
import messages.GreetingMessage;
import messages.Message;
import messages.MessageConstants;
import messages.TaskAddMessage;
import messages.TaskDeleteMessage;
import messages.TaskDoneMessage;
import messages.TaskListMessage;
import tasks.Task;
import tasks.TaskList;

import java.io.IOException;

/**
 * Handles commands from user.
 *
 * @author kevin9foong
 */
public class FulfillmentHandler {
    private final UserInputHandler userInputHandler;
    private final UserOutputHandler userOutputHandler;
    private final TaskList taskList;

    public FulfillmentHandler(UserInputHandler userInputHandler,
                              UserOutputHandler userOutputHandler, TaskList taskList) {
        this.userInputHandler = userInputHandler;
        this.userOutputHandler = userOutputHandler;
        this.taskList = taskList;
    }

    /**
     * Initializes the Chatbot
     *
     * @throws IOException thrown when an error connecting
     *                     to input/output stream occurs.
     */
    public void runChatbot() throws IOException {
        handleGreeting();
        boolean isExit = false;

        while (!isExit) {
            String userInput = userInputHandler.readInput();
            try {
                Command userCommand = Parser.parse(userInput);
                userCommand.execute(userOutputHandler, taskList);
                isExit = userCommand.isExit();
            } catch (DukeException e) {
                userOutputHandler.writeMessage(new Message(e.getMessage()));
            }
        }
    }

    private void handleGreeting() {
        userOutputHandler.writeMessage(new GreetingMessage());
    }
}
