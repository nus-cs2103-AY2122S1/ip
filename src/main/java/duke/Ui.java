package duke;

import duke.task.Task;
import duke.task.Todo;

import java.io.IOException;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;
    private TaskList tasks;
    private boolean isRunning;

    public Ui(TaskList tasks) {
        scanner = new Scanner(System.in);
        this.tasks = tasks;
        isRunning = true;
    }

    private void handleAddTask(String description) throws DukeException, IOException {
        if (description == null || description.equals("")) {
            throw new DukeException("Oops!!! The description of a todo cannot be empty.");
        }
        Task addedTask = tasks.addTask(new Todo(description));
        Message.printAddTaskMessage(addedTask, tasks.getSize());
    }

    private void handleAddDateTask(String descriptionAndDate, String command) throws DukeException, IOException {
        Task task = Parser.parseDateTask(descriptionAndDate, command);
        if (task == null) {
            Message.printInvalidCommandMessage();
            return;
        }
        tasks.addTask(task);
        Message.printAddTaskMessage(task, tasks.getSize());
    }

    private void handleMarkTask(String taskNumberString) throws DukeException, IOException {
        int taskNumber = retrieveTaskNumber(taskNumberString);
        Task markedTask = tasks.markTask(taskNumber);
        Message.printMarkTaskDoneMessage(markedTask);
    }

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

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public void scanUserInput() {
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

    public void start() {
        Message.printWelcomeMessage();
        scanUserInput();
        Message.printExitMessage();
    }

}
