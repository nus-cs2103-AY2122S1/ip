package duke.ui;

import java.io.IOException;

import duke.Message;
import duke.Parser;
import duke.TaskList;
import duke.command.Command;
import duke.exception.DukeException;
import duke.storage.TaskStorage;

/**
 * Represents a class that deals with interaction with the user.
 *
 * @author botr99
 */
public abstract class Ui {
    private TaskStorage taskStorage;
    private TaskList taskList;
    private boolean isRunning;
    private String currentMessage;

    /**
     * Constructs an ui with the user's storage and task list.
     *
     * @param taskStorage The user's storage of tasks in the hard disk.
     * @param taskList The user's list of tasks.
     */
    public Ui(TaskStorage taskStorage, TaskList taskList) {
        this.taskStorage = taskStorage;
        this.taskList = taskList;
        isRunning = true;
    }

    public boolean getIsRunning() {
        return isRunning;
    }

    /**
     * Sets the running state of the ui.
     *
     * @param isRunning A boolean value indicating whether ui
     *                  should continue to run.
     */
    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    /**
     * Retrieves the current message of the ui that is set as a response
     * to user input.
     *
     * @param userInput The user input.
     * @return A string representing Duke's response.
     */
    public String getResponse(String userInput) {
        try {
            Command command = Parser.parseUserInput(userInput);
            command.execute(taskList, this, taskStorage);
        } catch (DukeException e) {
            setCurrentMessage(Message.getDukeExceptionMessage(e));
        } catch (IOException e) {
            setCurrentMessage(Message.getTryAgainMessage());
        }
        return currentMessage;
    }

    public void setCurrentMessage(String message) {
        currentMessage = message;
    }

    /**
     * Starts the ui.
     */
    public abstract void start();

}
