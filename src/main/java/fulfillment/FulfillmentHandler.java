package fulfillment;

import io.InputHandler;
import io.OutputHandler;
import messages.*;
import tasks.Task;

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
            switch (userInput) {
                case "list":
                    handleTaskList();
                    break;
                case "bye":
                    handleBye();
                    return;
                default:
                    handleTaskAdd(userInput);
            }
        }
    }

    private void handleGreeting() {
        outputHandler.writeMessage(new GreetingMessage());
    }

    private void handleBye() {
        outputHandler.writeMessage(new ByeMessage());
    }

    private void handleTaskList() {
        outputHandler.writeMessage(new TaskListMessage(Task.getAllTasks()));
    }

    private void handleTaskAdd(String taskText) {
        Task.addTask(new Task(taskText));
        outputHandler.writeMessage(new TaskAddMessage(taskText));
    }
}
