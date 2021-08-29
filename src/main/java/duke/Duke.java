package duke;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Represents the Duke chatbot to store different types of tasks,
 * todos, deadlines and events.
 */
public class Duke {
    private static final String DATABASE_PATH = "data/duke.txt";
    private static final Pattern DATE_PATTERN = Pattern.compile("^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-"
            + "(0[1-9]|[12][0-9]|3[01]) ([01]?[0-9]|2[0-3])[0-5][0-9]$");
    private TaskList tasks;
    private final DukeUI ui;
    private final Storage storage;
    private final Parser parser;

    /**
     * Returns a <code>Duke</code> object that can reply to
     * commands. Duke can save the tasks at the specified <code>filePath</code>.
     *
     * @param filePath The file path where Duke will save the tasks.
     */
    public Duke(String filePath) {
        ui = new DukeUI();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = TaskList.createTaskList();
            this.storage.loadTasksFromFile(this.tasks);
        } catch (DukeException e) {
            this.ui.showError(e);
        }
    }

    /**
     * The main method run by Duke, to respond to commands. The functional
     * commands are: bye, done, deadline, todo, event
     * delete, list. Other commands are ignored.
     */
    public void run() {
        String command;
        boolean stillRunning = true;
        Scanner scanner = new Scanner(System.in);
        while (stillRunning) {
            command = scanner.nextLine();
            parser.interpretCommand(command);
            String firstCommand = this.parser.getFirstCommand();
            try {
                switch (firstCommand) {
                case "bye":
                    this.ui.goodBye();
                    stillRunning = false;
                    break;
                case "done":
                    markDone();
                    break;
                case "deadline":
                case "todo":
                case "event":
                    addTask();
                    break;
                case "delete":
                    deleteTask();
                    break;
                case "list":
                    this.tasks.listTasks();
                    break;
                case "find":
                    String keyword = this.parser.findKeyword();
                    ArrayList<Task> tasksWithKeyword = this.tasks.findTasksUsingKeyword(keyword);
                    this.ui.showTasksWithKeyword(tasksWithKeyword);
                    break;
                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                this.ui.showError(e);
            }
        }
    }

    /**
     * Method to delete task.
     *
     * @throws DukeException Thrown whenever user requests delete of a
     * task out of range or not a number. eg. <code>delete hi</code>
     */
    public void deleteTask() throws DukeException {
        try {
            this.tasks.deleteTask(parser.findCommandIndex());
            this.ui.showDeleteTaskMessage(this.tasks.getTasksLength());
            this.writeDataToDuke();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! Index out of range!");
        } catch (NumberFormatException e) {
            throw new DukeException("☹ OOPS!!! Put a number after 'delete'!");
        }
    }

    private void findTasks() {
        String keyword = parser.findKeyword();

    }

    private void writeDataToDuke() {
        this.storage.writeData(this.tasks);
    }

    /**
     * Method to add task to duke.Duke.
     *
     * @throws DukeException Thrown when the task is not given a description
     * or when the user does not give a date for an event or deadline task,
     * or when the user formats the date wrongly.
     */
    public void addTask() throws DukeException {
        String firstCommand = this.parser.getFirstCommand();
        String date = this.parser.findDateInCommand();
        String taskDesc = this.parser.findTaskDescription();
        String aOrAn = firstCommand.equals("event") ? "an" : "a";
        if (taskDesc.equals("")) {
            throw new DukeException("☹ OOPS!!! The description of " + aOrAn + " " + firstCommand + " cannot be empty.");
        } else if (date.equals("") && convertToTaskType(firstCommand) != Task.TaskType.TODO) {
            throw new DukeException("☹ OOPS!!! The date of " + aOrAn + " " + firstCommand + " cannot be empty.");
        } else if (convertToTaskType(firstCommand) == Task.TaskType.DEADLINE
                || convertToTaskType(firstCommand) == Task.TaskType.EVENT) {
            if (DATE_PATTERN.matcher(date).matches()) {

                String[] dateSplit = date.split(" ");
                String dateString = dateSplit[0];
                String timeString = dateSplit[1];
                LocalDate ld = LocalDate.parse(dateString);

                this.tasks.addTask(taskDesc, convertToTaskType(firstCommand), ld, timeString);

                this.confirmAdditionOfTask();
            } else {
                throw new DukeException("You need to put the date in yyyy-mm-dd hhmm format!");
            }
        } else {
            this.tasks.addTask(taskDesc);
            this.confirmAdditionOfTask();
        }
        this.writeDataToDuke();
    }

    /**
     * Confirms the addition of a task.
     */
    public void confirmAdditionOfTask() {
        int tasksLength = this.tasks.getTasksLength();
        this.ui.showTaskAddedMessage(tasksLength, this.tasks.getTask(tasksLength).toString());
    }

    /**
     * Method for duke.Duke to mark a task done.
     * @throws DukeException Thrown when user gives an index out of range
     * or not a number after the command done.
     */
    private void markDone() throws DukeException {
        try {
            int taskIndex = parser.findCommandIndex();
            this.tasks.markTaskDone(taskIndex);
            Task task = this.tasks.getTask(taskIndex);
            this.ui.markTaskDone(task);
            this.writeDataToDuke();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The number you gave is out of range!");
        } catch (NumberFormatException e) {
            throw new DukeException("☹ OOPS!!! Put a number after 'done'!");
        }
    }

    /**
     * Method used by Duke to convert task commands
     * into the TaskType enum.
     *
     * @param command The command to be converted.
     * @return The TaskType enum of the task.
     */
    public static Task.TaskType convertToTaskType(String command) {
        if (command.equals("todo")) {
            return Task.TaskType.TODO;
        } else if (command.equals("event")) {
            return Task.TaskType.EVENT;
        } else {
            return Task.TaskType.DEADLINE;
        }
    }

    /**
     * Main method for Duke.
     * @param args Optional arguments for CLI.
     */
    public static void main(String[] args) {
        Duke duke = new Duke(DATABASE_PATH);
        duke.ui.greetUser();
        duke.run();
    }
}
