package duke;

import java.time.LocalDate;
import java.util.*;
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
    private final DukeUi ui;
    private final Storage storage;
    private final Parser parser;
    private boolean isRunning;

    /**
     * Create a <code>Duke</code> object that can reply to commands.
     */
    public Duke() {
        ui = new DukeUi();
        storage = new Storage(DATABASE_PATH);
        parser = new Parser();
        isRunning = false;
        tasks = new TaskList();
    }

    /**
     * Runs Duke, to respond to commands. The functional
     * commands are: bye, done, deadline, todo, event
     * delete, list. Other commands are ignored.
     */
    public void run() {
        String command;
        storage.loadTasksFromFile(tasks);
        isRunning = true;
        Scanner scanner = new Scanner(System.in);
        while (isRunning) {
            command = scanner.nextLine();
            assert command != null;
            parser.interpretCommand(command);
            String firstCommand = parser.getFirstCommand();
            assert firstCommand != null;
            try {
                ui.respondToUser(respondToFirstCommand(firstCommand));
            } catch (DukeException e) {
                System.out.println(ui.showError(e));
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
            assert firstCommand != null;
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
            String keyword = parser.findKeyword();
            ArrayList<Task> tasksWithKeyword = tasks.findTasksUsingKeyword(keyword);
            response = ui.showTasksWithKeyword(tasksWithKeyword);
            break;
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        assert !Objects.equals(response, "");
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
            tasks.deleteTask(parser.findCommandIndex());
            writeDataToDuke();
            return ui.showDeleteTaskMessage(tasks.getTasksLength());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! Index out of range!");
        } catch (NumberFormatException e) {
            throw new DukeException("☹ OOPS!!! Put a number after 'delete'!");
        }
    }

    private void writeDataToDuke() {
        storage.writeData(tasks);
    }

    /**
     * Method to add task to duke.Duke.
     *
     * @throws DukeException Thrown when the task is not given a description
     * or when the user does not give a date for an event or deadline task,
     * or when the user formats the date wrongly.
     */
    public String addTask() throws DukeException {
        String firstCommand = parser.getFirstCommand();
        Task.TaskType taskType = convertToTaskType(firstCommand);
        String date = parser.findDateInCommand();
        String taskDesc = parser.findTaskDescription();
        if (taskDesc.equals("")) {
            throw new DukeException("☹ OOPS!!! The description cannot be empty.");
        } else if (date.equals("") && taskType != Task.TaskType.TODO) {
            throw new DukeException("☹ OOPS!!! The date cannot be empty.");
        } else if (taskType == Task.TaskType.DEADLINE
                || taskType == Task.TaskType.EVENT) {
            if (DATE_PATTERN.matcher(date).matches()) {
                String[] dateSplit = date.split(" ");
                String dateString = dateSplit[0];
                String timeString = dateSplit[1];
                LocalDate ld = LocalDate.parse(dateString);
                assert !dateString.equals("");
                assert !timeString.equals("");
                tasks.addTask(taskDesc, convertToTaskType(firstCommand), ld, timeString);
                writeDataToDuke();
                return confirmAdditionOfTask();
            } else {
                throw new DukeException("You need to put the date in yyyy-mm-dd hhmm format!");
            }
        } else {
            tasks.addTask(taskDesc);
            writeDataToDuke();
            return confirmAdditionOfTask();
        }

    }

    /**
     * Confirms the addition of a task.
     */
    public String confirmAdditionOfTask() {
        int tasksLength = tasks.getTasksLength();
        assert tasksLength != 0;
        return ui.showTaskAddedMessage(tasksLength, tasks.getTask(tasksLength).toString());
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
            assert taskIndex >= 0;
            tasks.markTaskDone(taskIndex);
            Task task = tasks.getTask(taskIndex);
            writeDataToDuke();
            return ui.markTaskDone(task);
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
