package taubot;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;


/**
 * Represents the Taubot chatbot to store different types of tasks,
 * todos, deadlines and events.
 */
public class Taubot {
    private static final String DATABASE_PATH = "data/taubot.txt";
    private static final Pattern DATE_PATTERN = Pattern.compile("^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-"
            + "(0[1-9]|[12][0-9]|3[01]) ([01]?[0-9]|2[0-3])[0-5][0-9]$");
    private final TaskList tasks;
    private final TaubotUi ui;
    private final Storage storage;
    private final Parser parser;
    private boolean isRunning;

    /**
     * Creates a Taubot object that can reply commands.
     */
    public Taubot() {
        ui = new TaubotUi();
        storage = new Storage(DATABASE_PATH);
        parser = new Parser();
        isRunning = false;
        tasks = new TaskList();
    }

    /**
     * Runs Taubot, to respond to commands. The functional
     * commands are: bye, done, deadline, todo, event
     * delete, list, schedule. Other commands are ignored.
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
            } catch (TaubotException e) {
                System.out.println(ui.showError(e));
            }
        }
    }

    /**
     * Runs Taubot when launched from the GUI
     *
     * @param command The command for Taubot to handle.
     * @return Response from Taubot.
     */
    public String runGui(String command) {
        try {
            storage.loadTasksFromFile(tasks);
            parser.interpretCommand(command);
            String firstCommand = parser.getFirstCommand();
            assert firstCommand != null;
            return respondToFirstCommand(firstCommand);
        } catch (TaubotException e) {
            return e.getMessage();
        }
    }

    private void stopRunningTaubot() {
        isRunning = false;
    }

    private String respondToFirstCommand(String firstCommand) {
        switch (firstCommand) {
        case "bye":
            stopRunningTaubot();
            return ui.goodBye();
        case "done":
            return markDone();
        case "deadline":
        case "todo":
        case "event":
            return addTask();
        case "delete":
            return deleteTask();
        case "list":
            return tasks.listTasks();
        case "find":
            String keyword = parser.findKeyword();
            ArrayList<Task> tasksWithKeyword = tasks.findTasksUsingKeyword(keyword);
            return ui.showTasksWithKeyword(tasksWithKeyword);
        case "schedule":
            String date = parser.findDateInCommand();
            ArrayList<Task> tasksWithDate;
            if (!date.equals("")) {
                try {
                    tasksWithDate = tasks.findTasksUsingDate(LocalDate.parse(date));
                } catch (DateTimeParseException e) {
                    throw new TaubotException("sorry, wrong format for date, use yyyy-mm-dd format");
                }
                return ui.showSchedule(tasksWithDate, date);
            }
            tasksWithDate = tasks.findTasksUsingDate();
            return ui.showSchedule(tasksWithDate, date);
        default:
            throw new TaubotException("sorry, i don't know what that means");
        }
    }

    /**
     * Deletes task from Taubot.
     *
     * @throws TaubotException Thrown whenever user requests delete of a
     * task out of range or not a number. e.g. delete hi
     */
    private String deleteTask() throws TaubotException {
        try {
            tasks.deleteTask(parser.findCommandIndex());
            writeDataToTaubot();
            return ui.showDeleteTaskMessage(tasks.getTasksLength());
        } catch (IndexOutOfBoundsException e) {
            throw new TaubotException("sorry, index out of range");
        } catch (NumberFormatException e) {
            throw new TaubotException("sorry, put a number after delete");
        }
    }

    private void writeDataToTaubot() {
        storage.writeData(tasks);
    }

    /**
     * Adds a task to Taubot when user enters a task command.
     *
     * @throws TaubotException Thrown when the task is not given a description
     * or when the user does not give a date for an event or deadline task,
     * or when the user formats the date wrongly.
     */
    private String addTask() throws TaubotException {
        String firstCommand = parser.getFirstCommand();
        Task.TaskType taskType = convertStringToTaskType(firstCommand);
        String date = parser.findDateInCommand();
        String taskDesc = parser.findTaskDescription();
        if (taskDesc.equals("")) {
            throw new TaubotException("sorry, description cannot be empty");
        } else if (date.equals("") && taskType != Task.TaskType.TODO) {
            throw new TaubotException("sorry, date cannot be empty");
        } else if (taskType == Task.TaskType.DEADLINE
                || taskType == Task.TaskType.EVENT) {
            if (isInDateFormat(date)) {
                String[] dateSplit = date.split(" ");
                String dateString = dateSplit[0];
                String timeString = dateSplit[1];
                LocalDate ld = LocalDate.parse(dateString);
                assert !dateString.equals("");
                assert !timeString.equals("");
                tasks.addTask(taskDesc, convertStringToTaskType(firstCommand), ld, timeString);
                writeDataToTaubot();
                return confirmAdditionOfTask();
            } else {
                throw new TaubotException("sorry, you need to put the date in yyyy-mm-dd hhmm format");
            }
        } else {
            tasks.addTask(taskDesc);
            writeDataToTaubot();
            return confirmAdditionOfTask();
        }

    }

    /**
     * Confirms the addition of a task.
     */
    private String confirmAdditionOfTask() {
        int tasksLength = tasks.getTasksLength();
        assert tasksLength != 0;
        return ui.showTaskAddedMessage(tasksLength, tasks.getTask(tasksLength).toString());
    }

    /**
     * Method for Taubot to mark a task done.
     *
     * @throws TaubotException Thrown when user gives an index out of range
     * or not a number after the command done.
     */
    private String markDone() throws TaubotException {
        try {
            int taskIndex = parser.findCommandIndex();
            assert taskIndex >= 0;
            tasks.markTaskDone(taskIndex);
            Task task = tasks.getTask(taskIndex);
            writeDataToTaubot();
            return ui.markTaskDone(task);
        } catch (IndexOutOfBoundsException e) {
            throw new TaubotException("sorry, the number you gave is out of range");
        } catch (NumberFormatException e) {
            throw new TaubotException("sorry, put a number after 'done'");
        }
    }

    /**
     * Method used by Taubot to convert task commands
     * into the TaskType enum.
     *
     * @param command The command to be converted.
     * @return The TaskType enum of the task.
     */
    public static Task.TaskType convertStringToTaskType(String command) {
        switch (command) {
        case "todo":
            return Task.TaskType.TODO;
        case "event":
            return Task.TaskType.EVENT;
        case "deadline":
            return Task.TaskType.DEADLINE;
        default:
            return Task.TaskType.NULL;
        }
    }

    private boolean isInDateFormat(String date) {
        return DATE_PATTERN.matcher(date).matches();
    }

    /**
     * Main method for Taubot.
     *
     * @param args Optional arguments for CLI.
     */
    public static void main(String[] args) {
        Taubot tauBot = new Taubot();
        tauBot.ui.greetUser();
        tauBot.run();
    }
}
