package duke;

import duke.commands.*;
import duke.exceptions.IllegalFormatException;
import duke.exceptions.UnknownTagException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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

    private String getCommandTag(String input) {
        if (!input.contains(" ")) {
            return input;
        } else {
            String[] details = input.split(" ");
            return details[0];
        }
    }

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

    private String getTodoDesc(String msg) throws IllegalFormatException {
        String[] details = msg.split("todo ");

        if (details.length != 2) {
            throw new IllegalFormatException("Please ensure that you have typed todo keyword.");
        }

        return details[1].trim();
    }

    private String getTaskDesc(String msg) throws IllegalFormatException {
        int startPosition = msg.indexOf(" ");
        int endPosition = msg.indexOf("/");

        if (startPosition < 0 || startPosition >= msg.length() || endPosition < 0 || endPosition >= msg.length()) {
            throw new IllegalFormatException("Please follow the format: type description /xx yyyy-mm-dd\n\t Use /by for deadline, /at for event.");
        }

        return msg.substring(startPosition, endPosition).trim();
    }

    private LocalDate getDeadlineDates(String msg) throws IllegalFormatException{
        String[] details = msg.split("/by ");

        if (details.length != 2) {
            throw new IllegalFormatException("Please follow the format: type description /by yyyy-mm-dd");
        }

        try {
            return LocalDate.parse(details[1]);
        } catch (DateTimeParseException e) {
            throw new IllegalFormatException("Please follow the format: type description /by yyyy-mm-dd");
        }
    }

    private LocalDate getEventDates(String msg) throws IllegalFormatException{
        String[] details = msg.split("/at ");

        if (details.length != 2) {
            throw new IllegalFormatException("Please follow the format: type description /at yyyy-mm-dd");
        }

        try {
            return LocalDate.parse(details[1]);
        } catch (DateTimeParseException e) {
            throw new IllegalFormatException("Please follow the format: type description /at yyyy-mm-dd");
        }
    }

    private int getTaskId(String msg) throws IllegalFormatException {
        String[] details = msg.split(" ");

        if (details.length != 2) {
            throw new IllegalFormatException("Please follow the format: command 0");
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
    private String getKeyword(String input) throws IllegalFormatException{
        String[] details = input.split("find ");

        if (details.length < 2) {
            throw new IllegalFormatException("Please follow the format: find keyword.");
        }

        return details[1];
    }

}
