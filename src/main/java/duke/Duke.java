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
    private final TaskList tasks;
    private final DukeUI ui;
    private final Storage storage;
    private final Parser parser;
    private boolean isRunning;

    /**
     * Returns a <code>Duke</code> object that can reply to command.
     */
    public Duke() {
        ui = new DukeUI();
        storage = new Storage(DATABASE_PATH);
        parser = new Parser();
        isRunning = false;
        tasks = new TaskList();
    }

    /**
     * The main method run by Duke, to respond to commands. The functional
     * commands are: bye, done, deadline, todo, event
     * delete, list. Other commands are ignored.
     */
    public void run() {
        storage.loadTasksFromFile(tasks);
        String command;
        isRunning = true;
        Scanner scanner = new Scanner(System.in);
        while (isRunning) {
            command = scanner.nextLine();
            parser.interpretCommand(command);
            String firstCommand = this.parser.getFirstCommand();
            try {
                ui.respondToUser(respondToFirstCommand(firstCommand));
            } catch (DukeException e) {
                this.ui.showError(e);
            }
        }
    }

    /**
     * Runs Duke when launched from the GUI
     *
     * @param command The command for Duke to handle.
     * @return Response from Duke.
     */
    public String runGui(String command) {
        try {
            storage.loadTasksFromFile(tasks);
            parser.interpretCommand(command);
            String firstCommand = parser.getFirstCommand();
            return respondToFirstCommand(firstCommand);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    private String respondToFirstCommand(String firstCommand) {
        String response;
        switch (firstCommand) {
        case "bye":
            response = ui.goodBye();
            isRunning = false;
            break;
        case "done":
            response = markDone();
            break;
        case "deadline":
        case "todo":
        case "event":
            response = addTask();
            break;
        case "delete":
            response = deleteTask();
            break;
        case "list":
            response = tasks.listTasks();
            break;
        case "find":
            String keyword = this.parser.findKeyword();
            ArrayList<Task> tasksWithKeyword = this.tasks.findTasksUsingKeyword(keyword);
            response = ui.showTasksWithKeyword(tasksWithKeyword);
            break;
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return response;
    }

    /**
     * Delete task from Duke.
     *
     * @throws DukeException Thrown whenever user requests delete of a
     * task out of range or not a number. e.g. <code>delete hi</code>
     */
    public String deleteTask() throws DukeException {
        try {
            this.tasks.deleteTask(parser.findCommandIndex());
            this.writeDataToDuke();
            return this.ui.showDeleteTaskMessage(this.tasks.getTasksLength());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! Index out of range!");
        } catch (NumberFormatException e) {
            throw new DukeException("☹ OOPS!!! Put a number after 'delete'!");
        }
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
    public String addTask() throws DukeException {
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
                this.writeDataToDuke();
                return this.confirmAdditionOfTask();
            } else {
                throw new DukeException("You need to put the date in yyyy-mm-dd hhmm format!");
            }
        } else {
            this.tasks.addTask(taskDesc);
            this.writeDataToDuke();
            return this.confirmAdditionOfTask();
        }

    }

    /**
     * Confirms the addition of a task.
     */
    public String confirmAdditionOfTask() {
        int tasksLength = this.tasks.getTasksLength();
        return this.ui.showTaskAddedMessage(tasksLength, this.tasks.getTask(tasksLength).toString());
    }

    /**
     * Method for duke.Duke to mark a task done.
     *
     * @throws DukeException Thrown when user gives an index out of range
     * or not a number after the command done.
     */
    private String markDone() throws DukeException {
        try {
            int taskIndex = parser.findCommandIndex();
            this.tasks.markTaskDone(taskIndex);
            Task task = this.tasks.getTask(taskIndex);
            this.writeDataToDuke();
            return this.ui.markTaskDone(task);
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
     *
     * @param args Optional arguments for CLI.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.ui.greetUser();
        duke.run();
    }
}
