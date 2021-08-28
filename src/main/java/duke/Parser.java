package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.TodoCommand;
import duke.exceptions.IllegalFormatException;
import duke.exceptions.UnknownTagException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;



public class Parser {
    private final boolean DEFAULT_STATUS = false;

    // duke.commands.Command Tags for the chat bot
    private final String LIST_TAG = "list";
    private final String DONE_TAG = "done";
    private final String TODO_TAG = "todo";
    private final String DEADLINE_TAG = "deadline";
    private final String EVENT_TAG = "event";
    private final String DELETE_TAG = "delete";
    private final String FIND_TAG = "find";

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
            String[] details = input.split(" ");
            return details[0];
        }
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
        case LIST_TAG:
            return new ListCommand();
        case TODO_TAG:
            Todo todoTask = new Todo(getTodoDesc(input), DEFAULT_STATUS);
            return new TodoCommand(todoTask);
        case DEADLINE_TAG:
            Deadline deadlineTask = new Deadline(getTaskDesc(input), getDeadlineDates(input), DEFAULT_STATUS);
            return new DeadlineCommand(deadlineTask);
        case EVENT_TAG:
            Event eventTask = new Event(getTaskDesc(input), getEventDates(input), DEFAULT_STATUS);
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
        String[] details = input.split("todo ");

        if (details.length != 2) {
            throw new IllegalFormatException("Please ensure that you have typed todo keyword.");
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
    private String getTaskDesc(String input) throws IllegalFormatException {
        int startPosition = input.indexOf(" ");
        int endPosition = input.indexOf("/");

        if (startPosition < 0 || startPosition >= input.length()
                || endPosition < 0 || endPosition >= input.length()) {
            throw new IllegalFormatException("Please follow the format: type description /xx yyyy-mm-dd\n\t "
                    + "Use /by for deadline, /at for event.");
        }

        return input.substring(startPosition, endPosition).trim();
    }

    /**
     * Returns the date of the Deadline task.
     *
     * @param input User's input
     * @return A LocalDate representing the date.
     * @throws IllegalFormatException Wrong format used by user.
     */
    private LocalDate getDeadlineDates(String input) throws IllegalFormatException {
        String[] details = input.split("/by ");

        if (details.length != 2) {
            throw new IllegalFormatException("Please follow the format: type description /by yyyy-mm-dd.");
        }

        try {
            return LocalDate.parse(details[1]);
        } catch (DateTimeParseException e) {
            throw new IllegalFormatException("Please follow the format: type description /by yyyy-mm-dd.");
        }
    }

    /**
     * Returns the date of the Event task.
     *
     * @param input User's input
     * @return A LocalDate representing the date.
     * @throws IllegalFormatException Wrong format used by user.
     */
    private LocalDate getEventDates(String input) throws IllegalFormatException {
        String[] details = input.split("/at ");

        if (details.length != 2) {
            throw new IllegalFormatException("Please follow the format: type description /at yyyy-mm-dd.");
        }

        try {
            return LocalDate.parse(details[1]);
        } catch (DateTimeParseException e) {
            throw new IllegalFormatException("Please follow the format: type description /at yyyy-mm-dd.");
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
        String[] details = input.split(" ");

        if (details.length != 2) {
            throw new IllegalFormatException("Please follow the format: command 0.");
        }

        try {
            return Integer.parseInt(details[1]) - 1;
        } catch (NumberFormatException e) {
            throw new IllegalFormatException("Please enter a valid id.");
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
        String[] details = input.split("find ");

        if (details.length < 2) {
            throw new IllegalFormatException("Please follow the format: find keyword.");
        }

        return details[1];
    }

}
