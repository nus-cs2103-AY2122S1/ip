package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.TodoCommand;
import duke.commands.UpdateCommand;
import duke.exceptions.IllegalFormatException;
import duke.exceptions.UnknownTagException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;


/**
 * Encapsulates the information for a Parser objects that helps to check an input and return a command for
 * the chat bot to execute accordingly.
 */
public class Parser {
    private final boolean IS_DEFAULT_STATUS = false;

    // Command Tags for the chat bot
    private final String LIST_TAG = "list";
    private final String DONE_TAG = "done";
    private final String TODO_TAG = "todo";
    private final String DEADLINE_TAG = "deadline";
    private final String EVENT_TAG = "event";
    private final String DELETE_TAG = "delete";
    private final String FIND_TAG = "find";
    private final String EXIT_TAG = "bye";
    private final String UPDATE_TAG = "update";

    /**
     * Returns the command tag of the input.
     *
     * @param input User's input
     * @return A String representing the command tag.
     */
    private String getCommandTag(String input) {
        if (!input.contains(" ")) {
            return input;
        } else {
            String key = " ";
            String[] details = spiltInputByKey(input, key);
            return details[0];
        }
    }

    private String[] spiltInputByKey(String input, String key) {
        return input.split(key);
    }

    private String[] spiltInputByKey(String input, String key, int limit) {
        return input.split(key, limit);
    }

    /**
     * Returns a command that allows the chat bot to know the next course of action to take
     * based on the user input.
     *
     * @param input User's input
     * @return A Command.
     * @throws IllegalFormatException Wrong format used by user.
     * @throws UnknownTagException Unrecognised keyword entered by user.
     */
    public Command checkCommandTag(String input) throws IllegalFormatException, UnknownTagException {
        String commandTag = getCommandTag(input).toLowerCase();

        switch (commandTag) {
        case EXIT_TAG:
            return new ExitCommand();
        case LIST_TAG:
            return new ListCommand();
        case TODO_TAG:
            Todo todoTask = new Todo(getTodoDesc(input), IS_DEFAULT_STATUS);
            return new TodoCommand(todoTask);
        case DEADLINE_TAG:
            Deadline deadlineTask = new Deadline(getTaskDesc("deadline", input),
                    getDeadlineDates(input), IS_DEFAULT_STATUS);
            return new DeadlineCommand(deadlineTask);
        case EVENT_TAG:
            Event eventTask = new Event(getTaskDesc("event", input), getEventDates(input), IS_DEFAULT_STATUS);
            return new EventCommand(eventTask);
        case DONE_TAG:
            int doneId = getTaskId(input);
            return new DoneCommand(doneId);
        case DELETE_TAG:
            int deleteId = getTaskId(input);
            return new DeleteCommand(deleteId);
        case FIND_TAG:
            String keyword = getKeyword(input);
            return new FindCommand(keyword);
        case UPDATE_TAG:
            int taskId = getUpdateId(input);
            String tag = getUpdateTag(input);
            String information = getUpdateInformation(input);
            return new UpdateCommand(taskId, tag, information);
        default:
            throw new UnknownTagException();
        }
    }

    /**
     * Returns the description of the Todo task.
     *
     * @param input User's input
     * @return A String representing the description.
     * @throws IllegalFormatException Wrong format used by user.
     */
    private String getTodoDesc(String input) throws IllegalFormatException {
        String key = "todo ";
        String[] details = spiltInputByKey(input, key);

        if (details.length != 2) {
            String errorMsg = "Please ensure that you have typed todo keyword.";
            throw new IllegalFormatException(errorMsg);
        }

        return details[1].trim();
    }

    /**
     * Returns the description of the Deadline/Event task.
     *
     * @param input User's input
     * @return A String representing the description.
     * @throws IllegalFormatException Wrong format used by user.
     */
    private String getTaskDesc(String type, String input) throws IllegalFormatException {
        String key = type + " ";
        String[] initialSplit = spiltInputByKey(input, key);

        if (initialSplit.length != 2) {
            String errorMsg = "Please follow the format:\n type description /xx yyyy-mm-dd\n"
                    + " Use /by for deadline, /at for event.";
            throw new IllegalFormatException(errorMsg);
        }

        String nextKey = "/";
        String[] details = spiltInputByKey(initialSplit[1], nextKey);

        if (details.length != 2) {
            String errorMsg = "Please follow the format:\n type description /xx yyyy-mm-dd\n"
                    + " Use /by for deadline, /at for event.";
            throw new IllegalFormatException(errorMsg);
        }

        return details[0].trim();
    }

    /**
     * Returns the date of the Deadline task.
     *
     * @param input User's input
     * @return A LocalDate representing the date.
     * @throws IllegalFormatException Wrong format used by user.
     */
    private LocalDate getDeadlineDates(String input) throws IllegalFormatException {
        String key = "/by ";
        return getTaskDate(key, input);
    }

    /**
     * Returns the date of the Event task.
     *
     * @param input User's input
     * @return A LocalDate representing the date.
     * @throws IllegalFormatException Wrong format used by user.
     */
    private LocalDate getEventDates(String input) throws IllegalFormatException {
        String key = "/at ";
        return getTaskDate(key, input);
    }

    private LocalDate getTaskDate(String key, String input) throws IllegalFormatException {
        String[] details = spiltInputByKey(input, key);

        if (details.length != 2) {
            String errorMsg = String.format("Please follow the format:\n type title %s yyyy-mm-dd.", key);
            throw new IllegalFormatException(errorMsg);
        }

        String dateString = details[1];
        return getLocalDate(dateString, key);
    }

    private LocalDate getLocalDate(String detail, String key) throws IllegalFormatException {
        try {
            return LocalDate.parse(detail);
        } catch (DateTimeParseException e) {
            String errorMsg = String.format("Please follow the format:\n type title %s yyyy-mm-dd.", key);
            throw new IllegalFormatException(errorMsg);
        }
    }

    /**
     * Returns the index of the task in the TaskList.
     *
     * @param input User's input
     * @return An int representing the index.
     * @throws IllegalFormatException Wrong format used by user.
     */
    private int getTaskId(String input) throws IllegalFormatException {
        String key = " ";
        String[] details = spiltInputByKey(input, key);

        if (details.length != 2) {
            String errorMsg = "Please follow the format:\n 'command' 'id'.";
            throw new IllegalFormatException(errorMsg);
        }

        if (!isInteger(details[1])) {
            String errorMsg = "Please enter a valid id.";
            throw new IllegalFormatException(errorMsg);
        }

        return Integer.parseInt(details[1]) - 1;
    }

    /**
     * Returns true is the input is an integer.
     * Otherwise, returns false.
     *
     * @param input The string to be checked.
     * @return A boolean indicating if the input is an integer.
     */
    private boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Returns the keyword that will be used to search the list of task.
     *
     * @param input User's input
     * @return A String representing the keyword to be used for the search.
     * @throws IllegalFormatException Wrong format used by user.
     */
    private String getKeyword(String input) throws IllegalFormatException {
        String key = "find ";
        String[] details = spiltInputByKey(input, key);

        if (details.length != 2) {
            String errorMsg = "Please follow the format:\n find 'keyword'.";
            throw new IllegalFormatException(errorMsg);
        }

        String keyword = details[1];
        if (keyword.isBlank()) {
            String errorMsg = "Did you forget to enter a keyword?";
            throw new IllegalFormatException(errorMsg);
        }

        return keyword;
    }

    private int getUpdateId(String input) throws IllegalFormatException {
        String[] details = getDetails(input);

        String id = details[1];
        if (!isInteger(id)) {
            String errorMsg = "Please enter a valid id.";
            throw new IllegalFormatException(errorMsg);
        }

        return Integer.parseInt(id) - 1;
    }

    /**
     * Returns the information that needs to be updated.
     *
     * @param input User's input
     * @return A String representing the information that needs to be updated.
     */
    private String getUpdateTag(String input) throws IllegalFormatException {
        String[] details = getDetails(input);
        return details[2];
    }

    private String getUpdateInformation(String input) throws IllegalFormatException {
        String[] details = getDetails(input);
        return details[3];
    }

    private String[] getDetails(String input) throws IllegalFormatException {
        String key = " ";
        String[] details = spiltInputByKey(input, key, 4);

        if (details.length != 4) {
            String errorMsg = "Please follow the format:\n update 'id' 'tag' 'info'";
            throw new IllegalFormatException(errorMsg);
        }
        return details;
    }
}
