package duke;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * This class represents the chat bot, duke.Duke.
 */
public class Duke {
    private final static String DATABASE_PATH = "data/duke.txt";
    private TaskList tasks;
    private final static Pattern DATE_PATTERN = Pattern.compile("^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01]) ([01]?[0-9]|2[0-3])[0-5][0-9]$");
    private final DukeUI ui;
    private Storage storage;
    private Parser parser;

    public Duke(String filePath) throws FileNotFoundException {
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
     * The static method that runs in Main to reply to the user.
     */
    public void run() throws FileNotFoundException {
        String command;
        boolean stillRunning = true;
        while (stillRunning) {
            Scanner scanner = new Scanner(System.in);
            command = scanner.nextLine();
            parser.intepretCommand(command);
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
     * @throws DukeException
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

    private void writeDataToDuke() {
        this.storage.writeData(this.tasks);
    }

    /**
     * Method to add task to duke.Duke.
     *
     * @return String array of the command keywords.
     * @throws DukeException
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
        } else if (convertToTaskType(firstCommand) == Task.TaskType.DEADLINE || convertToTaskType(firstCommand) == Task.TaskType.EVENT) {
//                To duke.Parser
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
            //        ENd of To duke.Parser
        } else {
            this.tasks.addTask(taskDesc, convertToTaskType(firstCommand), date);
            this.confirmAdditionOfTask();
        }
        this.writeDataToDuke();
    }

    public void confirmAdditionOfTask() {
        int tasksLength = this.tasks.getTasksLength();
        this.ui.showTaskAddedMessage(tasksLength, this.tasks.getTask(tasksLength).toString());
    }

    /**
     * Method for duke.Duke to mark a task done.
     *
     * @throws DukeException
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
     * Checks if the string is an integer.
     *
     * @param input String to check.
     * @return Whether string is an integer.
     */

    private static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static Task.TaskType convertToTaskType(String command) {
        if (command.equals("todo")) {
            return Task.TaskType.TODO;
        } else if (command.equals("event")) {
            return Task.TaskType.EVENT;
        } else {
            return Task.TaskType.DEADLINE;
        }
    }

    public static void main(String[] args) {
        try {
            Duke duke = new Duke(DATABASE_PATH);
            duke.ui.greetUser();
            duke.run();
        } catch (FileNotFoundException e) {
            e.getMessage();
        }
    }
}
