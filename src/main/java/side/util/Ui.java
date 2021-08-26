package side.util;

import side.exception.*;
import side.tasks.Deadline;
import side.tasks.Event;
import side.tasks.Task;

import java.util.ArrayList;

/**
 * Encapsulates user interactions, handling user input logic and formats responses to print for user.
 *
 * @author Lua Yi Da
 */

public class Ui {

    private static final String LINEBREAK = "---------------------------------------------------------------------";
    private static final String GREETING = LINEBREAK + "" + "\nI'm Side, your unpaid personal assistant. " +
            "Please do less...\n" + LINEBREAK;
    private static final String GOODBYE = LINEBREAK + "\nOh, you have to go? What a pity...\n"
            + LINEBREAK;
    private Parser parser;

    /**
     * Initialises instance of UI with a parser.
     */
    public Ui() {
        this.parser = new Parser();
    }

    private void printLogo() {
        String logo = " ___  _____  _____   _____  \n"
                + "|  _|  | |  | ___ \\  | |__\n"
                + " \\ \\   | |  | |_| |  | |  \n"
                + "|___| _|_|_ |____/   |_|__\n";
        System.out.println(logo);
    }

    /**
     * Prints logo and greeting for user on launch.
     */
    public void greet() {
        this.printLogo();
        System.out.println(GREETING);
    }

    /**
     * Prints closing message for user on end.
     */
    public void close() {
        System.out.println(GOODBYE);
    }

    /**
     * Print response specific to adding tasks.
     *
     * @param input String representation of task to add.
     * @param tasks TaskList to be added to.
     */
    public static void echo(String input, TaskList tasks) {
        System.out.println(LINEBREAK);
        String taskQuantifier = tasks.length() == 1 ? "task..." : "tasks...";
        System.out.println("Fine, I'll add: " + input + "\nYou now have " + tasks.length() + " " + taskQuantifier);
        System.out.println(LINEBREAK);
    }

    /**
     * Prints response with linebreaks.
     *
     * @param input String representation of String to format.
     */
    public void printResponse(String input) {
        System.out.println(LINEBREAK);
        System.out.println(input);
        System.out.println(LINEBREAK);
    }

    /**
     * Handles the logic to add a deadline to TaskList.
     *
     * @param input String representation of user input.
     * @param taskList TaskList to be added to.
     * @throws WrongFormatException Catches incorrectly formatted input and returns error.
     */
    public void addDeadline(String input, TaskList taskList) throws WrongFormatException {
        if (input.contains("/by") && (this.parser.findDeadlineDatetime(input) != null)) {
            String datetime = this.parser.findDeadlineDatetime(input);
            String description = this.parser.findDescription(input);
            taskList.addDeadline(description, datetime);
            Ui.echo(new Deadline(description, datetime).toString(), taskList);
        } else {
            throw new WrongFormatException("deadline [task name] /by [YYYY-MM-DD], [HHMM]\n" +
                    "deadline [task name] /by [YYYY-MM-DD]");
        }
    }

    /**
     * Handles the logic to add an Event to TaskList.
     *
     * @param input String representation of user input.
     * @param taskList TaskList to be added to.
     * @throws WrongFormatException Catches incorrectly formatted input and returns error.
     */
    public void addEvent(String input, TaskList taskList) throws WrongFormatException {
        if (input.contains("/at") && (this.parser.findEventDatetime(input) != null)) {
            String[] datetimeArr = this.parser.findEventDatetime(input);
            String description = this.parser.findDescription(input);
            if (datetimeArr.length < 2) {
                throw new WrongFormatException("event [task name] /at [YYYY-MM-DD], [HHMM] /to [YYYY-MM-DD], [HHMM]\n" +
                        "event [task name] /at [YYYY-MM-DD] /to [YYYY-MM-DD]");
            }
            if (datetimeArr != null && datetimeArr[0] != null && datetimeArr[1] != null) {
                taskList.addEvent(description, datetimeArr[0], datetimeArr[1]);
                Ui.echo(new Event(description, datetimeArr[0], datetimeArr[1]).toString(), taskList);
            }
        } else {
            throw new WrongFormatException("event [task name] /at [YYYY-MM-DD], [HHMM] /to [YYYY-MM-DD], [HHMM]\n" +
                    "event [task name] /at [YYYY-MM-DD] /to [YYYY-MM-DD]");
        }
    }

    /**
     * Handles the logic to add a task to TaskList.
     *
     * @param input String representation of user input.
     * @param taskList TaskList to be added to.
     * @throws WrongFormatException Catches incorrectly formatted input and returns error.
     */
    public void addTask(String input, TaskList taskList) throws WrongFormatException {
        if (input.replace("todo", "").replaceAll(" ", "").length() > 0) {
            taskList.addTask(input);
            Ui.echo(new Task(input).toString(), taskList);
        } else {
            throw new WrongFormatException("todo [task name]");
        }
    }

    /**
     * Handles the logic of marking a task as done.
     *
     * @param input String representation of user input.
     * @param taskList TaskList in which task is to be marked.
     * @throws TaskIndexException Catches out of bounds task indexes and returns error.
     * @throws NoIndexException Catches no index input from user and returns error.
     * @throws TooManyIndexesException Catches too many index input from user and returns error.
     */
    public void handleDone(String input, TaskList taskList) throws TaskIndexException, NoIndexException,
            TooManyIndexesException {
        if (input.split("\\s+").length == 2) {
            int taskNum = this.parser.tryIntParsing(input.split("\\s+")[1]);
            if (taskNum > taskList.length() || taskNum <= 0) {
                throw new TaskIndexException();
            } else {
                printResponse(taskList.markTaskDone(taskNum - 1));
            }
        } else if (input.split("\\s+").length == 1) {
            throw new NoIndexException();
        } else {
            throw new TooManyIndexesException("mark");
        }
    }

    /**
     * Handles the logic of deleting a task.
     *
     * @param input String representation of user input.
     * @param taskList TaskList in which task is to be marked.
     * @throws DeleteIndexException Catches out of bounds task indexes and returns error.
     * @throws NoIndexException Catches no index input from user and returns error.
     * @throws TooManyIndexesException Catches too many index input from user and returns error.
     */
    public void handleDelete(String input, TaskList taskList) throws DeleteIndexException, NoIndexException,
            TooManyIndexesException {
        if (input.split("\\s+").length == 2) {
            int taskNum = this.parser.tryIntParsing(input.split("\\s+")[1]);
            if (taskNum > taskList.length() || taskNum <= 0) {
                throw new DeleteIndexException();
            } else {
                printResponse(taskList.delete(taskNum - 1));
            }
        } else if (input.split("\\s+").length == 1) {
            throw new NoIndexException();
        } else {
            throw new TooManyIndexesException("delete");
        }
    }

    private String convertFindList(ArrayList<Task> list) {
        StringBuilder tasksList = new StringBuilder();

        for (int i = 0; i < list.size(); i++) {
            String fullTaskLine = (i + 1) + ". " + list.get(i).toString() + "\n";
            tasksList.append(fullTaskLine);
        }

        return tasksList.toString();
    }

    /**
     * Handles logic for find command.
     *
     * @param input String representing user input.
     * @param taskList List to check against.
     */
    public void handleFind(String input, TaskList taskList) {
        if (input.split("\\s+").length == 1) {
            throw new NoIndexException();
        } else {
            String matching = input.replaceFirst("find", "").trim();
            StringBuilder listString = new StringBuilder();
            listString.append("I found...\n");

            if (taskList.findTasks(matching).size() == 0) {
                listString.append("Nothing.");
            } else {
                listString.append(convertFindList(taskList.findTasks(matching)));
            }

            printResponse(listString.toString());
        }
    }
}
