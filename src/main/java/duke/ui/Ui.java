package duke.ui;

import java.io.IOException;

import duke.Message;
import duke.Parser;
import duke.TaskList;
import duke.exception.DukeException;
import duke.storage.TaskStorage;
import duke.task.Task;
import duke.task.Todo;

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

    /**
     * Handles the scenario when the user wants to add a task
     * to his/her task list.
     *
     * @param description The description of the task.
     * @throws DukeException When there is no description.
     * @throws IOException When there is an error in writing to the file
     *                     that stores the user's task list.
     */
    public void handleAddTask(String description) throws DukeException, IOException {
        if (description == null || description.equals("")) {
            throw new DukeException("Oops!!! The description of a todo cannot be empty.");
        }
        Task task = taskList.addTask(new Todo(description));
        taskStorage.addTaskToStorage(task);
        setCurrentMessage(Message.getAddTaskMessage(task, taskList.getSize()));
    }

    /**
     * Handles the scenario when the user wants to add a task
     * with a date to his/her task list.
     *
     * @param descriptionAndDate The description of the task, with the date
     *                           in a single string.
     * @param command The type of date task to be created.
     * @throws DukeException When the description and date string cannot be split in 2;
     *                       when the date task cannot be constructed due to invalid date string.
     * @throws IOException When there is an error in writing to the file
     *                     that stores the user's task list.
     */
    public void handleAddDateTask(String descriptionAndDate, String command) throws DukeException, IOException {
        Task task = Parser.parseDateTask(descriptionAndDate, command);
        if (task == null) {
            setCurrentMessage(Message.getInvalidCommandMessage());
            return;
        }
        taskList.addTask(task);
        taskStorage.addTaskToStorage(task);
        setCurrentMessage(Message.getAddTaskMessage(task, taskList.getSize()));
    }

    /**
     * Handles the scenario when the user wants to mark a task as done.
     *
     * @param taskNumberString The nth task to be marked as done, represented as a string.
     * @throws DukeException When the task number cannot be retrieved from the task number string.
     * @throws IOException When there is an error in writing to the file
     *                     that stores the user's task list.
     */
    public void handleMarkTaskAsDone(String taskNumberString) throws DukeException, IOException {
        int taskNumber = retrieveTaskNumber(taskNumberString);
        Task markedTask = taskList.markTask(taskNumber);
        taskStorage.editTaskFromStorage(taskNumber, markedTask);
        setCurrentMessage(Message.getMarkTaskAsDoneMessage(markedTask));
    }

    /**
     * Handles the scenario when the user wants to delete a task.
     *
     * @param taskNumberString The nth task to be deleted, represented as a string.
     * @throws DukeException When the task number cannot be retrieved from the task number string.
     * @throws IOException When there is an error in writing to the file
     *                     that stores the user's task list.
     */
    public void handleDeleteTask(String taskNumberString) throws DukeException, IOException {
        int taskNumber = retrieveTaskNumber(taskNumberString);
        Task removedTask = taskList.deleteTask(taskNumber);
        taskStorage.removeTaskFromStorage(taskNumber);
        setCurrentMessage(Message.getDeleteTaskMessage(removedTask, taskList.getSize()));
    }

    /**
     * Handles the scenario when the user wants to find tasks that contains
     * what he/she wants to query for.
     *
     * @param query The keyword(s) to find the tasks in the task list.
     */
    public void handleFindTasks(String query) {
        TaskList filteredTaskList = taskList.getFilteredTaskList(query);
        setCurrentMessage(Message.getFilteredTaskListMessage(filteredTaskList));
    }

    /**
     * Retrieves the task number as an integer from the task number string,
     * that is within the boundaries of the task list size.
     *
     * @param taskNumberString The string containing the task number.
     * @return The integer parsed from the task number string.
     * @throws DukeException When the task number string is not an integer;
     *                       when the task number integer parsed is out of range
     *                       of the size of the task lists.
     */
    public int retrieveTaskNumber(String taskNumberString) throws DukeException {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(taskNumberString);
        } catch (NumberFormatException e) {
            throw new DukeException("Oops!!! The done or delete command should be followed by an integer.");
        }

        if (taskNumber < 1 || taskNumber > taskList.getSize()) {
            throw new DukeException("Oops!!! The task number provided is not valid.");
        }
        return taskNumber;
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
     * Delegates the various cases of possible user input to the
     * respective handler functions.
     *
     * @param userInput A string the user has inputted.
     */
    public void handleUserInput(String userInput) {
        String[] userInputSplit = userInput.trim().split(" ", 2);
        String command = userInputSplit[0];
        String action = userInputSplit.length == 2 ? userInputSplit[1].trim() : "";

        try {
            switch (command) {
            case "bye":
                setIsRunning(false);
                setCurrentMessage(Message.getExitMessage());
                break;
            case "list":
                setCurrentMessage(Message.getTaskListMessage(taskList));
                break;
            case "done":
                handleMarkTaskAsDone(action);
                break;
            case "delete":
                handleDeleteTask(action);
                break;
            case "todo":
                handleAddTask(action);
                break;
            case "deadline":
            case "event":
                handleAddDateTask(action, command);
                break;
            case "find":
                handleFindTasks(action);
                break;
            default:
                setCurrentMessage(Message.getInvalidCommandMessage());
                break;
            }
        } catch (DukeException e) {
            setCurrentMessage(Message.getDukeExceptionMessage(e));
        } catch (IOException e) {
            setCurrentMessage(Message.getTryAgainMessage());
        }
    }

    public String getCurrentMessage() {
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
