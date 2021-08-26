package duke;

import duke.task.Task;
import duke.task.Todo;

import java.io.IOException;
import java.util.Scanner;

/**
 * Represents a class that deals with interaction with the user.
 *
 * @author botr99
 */
public class Ui {
    private Scanner scanner;
    private TaskList tasks;
    private boolean isRunning;

    /**
     * Constructs an ui with the user's tasks,
     * initialising a scanner to deal with user input.
     *
     * @param tasks The user's list of tasks.
     */
    public Ui(TaskList tasks) {
        scanner = new Scanner(System.in);
        this.tasks = tasks;
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
    private void handleAddTask(String description) throws DukeException, IOException {
        if (description == null || description.equals("")) {
            throw new DukeException("Oops!!! The description of a todo cannot be empty.");
        }
        Task addedTask = tasks.addTask(new Todo(description));
        Message.printAddTaskMessage(addedTask, tasks.getSize());
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
    private void handleAddDateTask(String descriptionAndDate, String command) throws DukeException, IOException {
        Task task = Parser.parseDateTask(descriptionAndDate, command);
        if (task == null) {
            Message.printInvalidCommandMessage();
            return;
        }
        tasks.addTask(task);
        Message.printAddTaskMessage(task, tasks.getSize());
    }

    /**
     * Handles the scenario when the user wants to mark a task as done.
     *
     * @param taskNumberString The nth task to be marked as done, represented as a string.
     * @throws DukeException When the task number cannot be retrieved from the task number string.
     * @throws IOException When there is an error in writing to the file
     *                     that stores the user's task list.
     */
    private void handleMarkTask(String taskNumberString) throws DukeException, IOException {
        int taskNumber = retrieveTaskNumber(taskNumberString);
        Task markedTask = tasks.markTask(taskNumber);
        Message.printMarkTaskDoneMessage(markedTask);
    }

    /**
     * Handles the scenario when the user wants to delete a task.
     *
     * @param taskNumberString The nth task to be deleted, represented as a string.
     * @throws DukeException When the task number cannot be retrieved from the task number string.
     * @throws IOException When there is an error in writing to the file
     *                     that stores the user's task list.
     */
    private void handleDeleteTask(String taskNumberString) throws DukeException, IOException {
        int taskNumber = retrieveTaskNumber(taskNumberString);
        Task removedTask = tasks.deleteTask(taskNumber);
        Message.printDeleteTaskMessage(removedTask, tasks.getSize());
    }

    /**
     * Handles the scenario when the user wants to find tasks that contains
     * what he/she wants to query for.
     *
     * @param query The keyword(s) to find the tasks in the task list.
     */
    private void handleFindTask(String query) {
        String filteredTasksString = tasks.getFilteredTasksString(query);
        Message.printFindTasksMessage(filteredTasksString);
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
    private int retrieveTaskNumber(String taskNumberString) throws DukeException {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(taskNumberString);
        } catch (NumberFormatException e) {
            throw new DukeException("Oops!!! The done or delete command should be followed by an integer.");
        }

        if (taskNumber < 1 || taskNumber > tasks.getSize()) {
            throw new DukeException("Oops!!! The task number provided is not valid.");
        }
        return taskNumber;
    }

    /**
     * Sets the running state of the ui.
     *
     * @param isRunning A boolean value indicating whether ui
     *                  should continue to run.
     */
    private void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    /**
     * Scans user input and delegates the various cases of handling
     * user input to the respective functions.
     */
    private void scanUserInput() {
        while (isRunning) {
            String[] userInput = scanner.nextLine().trim().split(" ", 2);
            String command = userInput[0];
            String action = userInput.length == 2 ? userInput[1].trim() : "";

            try {
                switch (command) {
                case "bye":
                    setIsRunning(false);
                    break;
                case "list":
                    Message.printTasksMessage(tasks);
                    break;
                case "done":
                    handleMarkTask(action);
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
                    handleFindTask(action);
                    break;
                default:
                    Message.printInvalidCommandMessage();
                    break;
                }
            } catch (DukeException e) {
                Message.printDukeExceptionMessage(e);
            } catch (IOException e) {
                Message.printTryAgainMessage();
            }
        }
        scanner.close();
    }

    /**
     * Starts the ui and prints an exit message
     * when the scanner is closed.
     */
    public void start() {
        Message.printWelcomeMessage();
        scanUserInput();
        Message.printExitMessage();
    }

}
