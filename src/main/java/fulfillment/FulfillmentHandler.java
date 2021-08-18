package fulfillment;

import exceptions.DukeException;
import exceptions.InvalidCommandException;
import exceptions.InvalidTaskNumberException;
import io.InputHandler;
import io.OutputHandler;
import messages.*;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

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
            String[] splitUserInput = userInput.trim().split(" ", 2);
            String userCommand = splitUserInput[0];
            String userInputBody = null;

            if (splitUserInput.length == 2) {
                userInputBody = splitUserInput[1];
            }

            try {
                switch (userCommand) {
                    case "list":
                        handleTaskList();
                        break;
                    case "todo":
                        handleTaskAdd(new ToDo(userInputBody));
                        break;
                    case "deadline":
                        handleTaskAdd(new Deadline(userInputBody));
                        break;
                    case "event":
                        handleTaskAdd(new Event(userInputBody));
                        break;
                    case "done":
                        handleTaskDone(userInputBody);
                        break;
                    case "bye":
                        handleBye();
                        return;
                    default:
                        throw new InvalidCommandException();
                }
            } catch (DukeException e) {
                outputHandler.writeMessage(new Message(e.getMessage()));
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

    private void handleTaskAdd(Task task) {
        Task addedTask = Task.addTask(task);
        outputHandler.writeMessage(new TaskAddMessage(addedTask.toString(),
                Task.getNumOfTasks()));
    }

    private void handleTaskDone(String userInputBody) throws InvalidTaskNumberException {
        try {
            // user input is 1 greater than index.
            int index = Integer.parseInt(userInputBody) - 1;
            Task doneTask = Task.getTask(index);
            doneTask.setDone();
            outputHandler.writeMessage(new TaskDoneMessage(doneTask));
        } catch (NumberFormatException nfe) {
            outputHandler.writeMessage(new Message(MessageConstants.INVALID_INTEGER_MESSAGE));
        }
    }
}
