package side.util;

import java.util.ArrayList;

import side.exception.DeleteIndexException;
import side.exception.NoIndexException;
import side.exception.TaskIndexException;
import side.exception.TooManyIndexesException;
import side.exception.WrongFormatException;
import side.tasks.Deadline;
import side.tasks.Event;
import side.tasks.Task;

/**
 * Encapsulates user interactions, handling user input logic and formats responses to print for user.
 *
 * @author Lua Yi Da
 */

public class Ui {

    private static final String GREETING = "\nI'm Side, your unpaid personal assistant. "
            + "Please do less...\n";
    private static final String GOODBYE = "\nOh, you have to go? What a pity...\n";
    private Parser parser;

    /**
     * Initialises instance of UI with a parser.
     */
    public Ui() {
        this.parser = new Parser();
    }

    /**
     * Gets greeting.
     *
     * @return String representing greeting.
     */
    public String getGreeting() {
        return GREETING;
    }

    /**
     * Prints response specific to adding tasks.
     *
     * @param input String representation of task to add.
     * @param tasks TaskList to be added to.
     * @return String representing response.
     */
    public static String formatMessage(String input, TaskList tasks) {
        String taskQuantifier = tasks.length() == 1 ? "task..." : "tasks...";
        return "Fine, I'll add: " + input + "\nYou now have " + tasks.length() + " " + taskQuantifier;
    }

    /**
     * Handles the logic to add a deadline to TaskList.
     *
     * @param input String representation of user input.
     * @param taskList TaskList to be added to.
     * @return String representing response.
     * @throws WrongFormatException Catches incorrectly formatted input.
     */
    public String addDeadline(String input, TaskList taskList) throws WrongFormatException {
        assert input.length() > 0;

        if (input.contains("/by") && (this.parser.findDeadlineDatetime(input) != null)) {
            String datetime = this.parser.findDeadlineDatetime(input);
            String description = this.parser.findDescription(input);
            taskList.addDeadline(description, datetime);
            return Ui.formatMessage(new Deadline(description, datetime).toString(), taskList);
        } else {
            throw new WrongFormatException("deadline [task name] /by [YYYY-MM-DD], [HHMM]\n"
                    + "deadline [task name] /by [YYYY-MM-DD]");
        }
    }

    /**
     * Handles the logic to add an Event to TaskList.
     *
     * @param input String representation of user input.
     * @param taskList TaskList to be added to.
     * @return String representing response.
     * @throws WrongFormatException Catches incorrectly formatted input.
     */
    public String addEvent(String input, TaskList taskList) throws WrongFormatException {
        assert input.length() > 0;

        if (input.contains("/at") && (this.parser.findEventDatetime(input) != null)) {
            String[] datetimeArr = this.parser.findEventDatetime(input);
            String description = this.parser.findDescription(input);
            if (datetimeArr.length < 2) {
                throw new WrongFormatException("event [task name] /at [YYYY-MM-DD], [HHMM] /to [YYYY-MM-DD], [HHMM]\n"
                        + "event [task name] /at [YYYY-MM-DD] /to [YYYY-MM-DD]");
            }
            if (datetimeArr != null && datetimeArr[0] != null && datetimeArr[1] != null) {
                taskList.addEvent(description, datetimeArr[0], datetimeArr[1]);
                return Ui.formatMessage(new Event(description, datetimeArr[0], datetimeArr[1]).toString(), taskList);
            }
        } else {
            throw new WrongFormatException("event [task name] /at [YYYY-MM-DD], [HHMM] /to [YYYY-MM-DD], [HHMM]\n"
                    + "event [task name] /at [YYYY-MM-DD] /to [YYYY-MM-DD]");
        }
        return "";
    }

    /**
     * Handles the logic to add a task to TaskList.
     *
     * @param input String representation of user input.
     * @param taskList TaskList to be added to.
     * @return String representing response.
     * @throws WrongFormatException Catches incorrectly formatted input.
     */
    public String addTask(String input, TaskList taskList) throws WrongFormatException {
        assert input.length() > 0;

        int numTasksToAdd = input.replace("todo", "").replaceAll(" ", "").length();
        if (numTasksToAdd > 0) {
            taskList.addTask(input);
            return Ui.formatMessage(new Task(input).toString(), taskList);
        } else {
            throw new WrongFormatException("todo [task name]");
        }
    }

    /**
     * Handles the logic of marking a task as done.
     *
     * @param input String representation of user input.
     * @param taskList TaskList in which task is to be marked.
     * @return String representing response.
     * @throws TaskIndexException Catches out of bounds task indexes.
     * @throws NoIndexException Catches no index input from user.
     * @throws TooManyIndexesException Catches too many index input from user.
     */
    public String handleDone(String input, TaskList taskList) throws TaskIndexException, NoIndexException,
            TooManyIndexesException {
        assert input.length() > 0;

        int commandSize = input.split("\\s+").length;
        if (commandSize == 2) {
            int taskNum = this.parser.tryIntParsing(input.split("\\s+")[1]);
            if (taskNum > taskList.length() || taskNum <= 0) {
                throw new TaskIndexException();
            } else {
                return taskList.markTaskDone(taskNum - 1);
            }
        } else if (commandSize == 1) {
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
     * @return String representing response.
     * @throws DeleteIndexException Catches out of bounds task indexes.
     * @throws NoIndexException Catches no index input from user.
     * @throws TooManyIndexesException Catches too many index input from user.
     */
    public String handleDelete(String input, TaskList taskList) throws DeleteIndexException, NoIndexException,
            TooManyIndexesException {
        assert input.length() > 0;

        if (input.split("\\s+").length == 2) {
            int taskNum = this.parser.tryIntParsing(input.split("\\s+")[1]);
            boolean isInvalidIndex = taskNum > taskList.length() || taskNum <= 0;
            if (isInvalidIndex) {
                throw new DeleteIndexException();
            } else {
                return taskList.delete(taskNum - 1);
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
     * @return String representing response.
     * @throws NoIndexException Catches no index input from user.
     */
    public String handleFind(String input, TaskList taskList) throws NoIndexException {
        assert input.length() > 0;

        boolean hasNoIndex = input.split("\\s+").length == 1;
        if (hasNoIndex) {
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

            return listString.toString();
        }
    }

    public String handleClose() {
        return GOODBYE;
    }
}
