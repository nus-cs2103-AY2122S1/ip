package duke;

import java.time.LocalDate;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.EditCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.OnCommand;

/**
 * Parser class deals with making sense of the user command.
 */
public class Parser {

    /**
     * Returns the command to be executed based on the user's command message.
     *
     * @param commandMessage Command message from user's input.
     * @return Command to be executed.
     * @throws DukeException  If correct command cannot be created.
     */
    public static Command parse(String commandMessage) throws DukeException {
        String[] message = commandMessage.split(" ", 2);
        String command = message[0];
        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "done": {
            int taskNumber = getTaskNumber(message[1]);
            return new EditCommand(EditCommand.EditType.DONE, taskNumber);
        }
        case "todo":
            return new AddCommand(AddCommand.TaskType.TODO, new String[] {checkToDoDescription(message[1])});
        case "deadline": {
            String description = checkDeadlineDescription(message[1]);
            String[] parameters = description.split(" /by ");
            return new AddCommand(AddCommand.TaskType.DEADLINE, parameters);
        }
        case "event": {
            String description = checkEventDescription(message[1]);
            String[] parameters = description.split(" /at ");
            return new AddCommand(AddCommand.TaskType.EVENT, parameters);
        }
        case "delete": {
            int taskNumber = getTaskNumber(message[1]);
            return new EditCommand(EditCommand.EditType.DELETE, taskNumber);
        }
        case "on": {
            String dateString = message[1]; // need to check string
            LocalDate date = LocalDate.parse(dateString.trim());
            return new OnCommand(date);
        }
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(\n");
        }
    }

    /**
     * Checks that the Deadline description is valid.
     *
     * @param des User's command message excluding the command word.
     * @return Deadline description string.
     * @throws DukeException If description is empty or does not contain a date.
     */
    public static String checkDeadlineDescription(String des) throws DukeException {
        String description = des.trim();
        if (description.isEmpty()) {
            throw new DukeException("The description of a deadline cannot be empty!\n");
        } else if (!description.contains(" /by ")) {
            throw new DukeException("The new deadline is missing a date!\n");
        } else {
            return description;
        }
    }

    /**
     * Checks that the Event description is valid.
     *
     * @param des User's command message excluding the command word.
     * @return Event description string.
     * @throws DukeException If description is empty or does not contain a date.
     */
    public static String checkEventDescription(String des) throws DukeException {
        String description = des.trim();
        if (description.isEmpty()) {
            throw new DukeException("The description of an event cannot be empty!\n");
        } else if (!description.contains(" /at ")) {
            throw new DukeException("The new event is missing a date!\n");
        } else {
            return description;
        }
    }

    /**
     * Checks that the ToDo description is valid.
     *
     * @param des User's command message excluding the command word.
     * @return ToDo description string.
     * @throws DukeException If description is empty.
     */
    public static String checkToDoDescription(String des) throws DukeException {
        String description = des.trim();
        if (description.isEmpty()) {
            throw new DukeException("The description of a todo task cannot be empty!\n");
        } else {
            return description;
        }
    }

    /**
     * Retrieves the task number from the user's command message.
     *
     * @param des User's command message excluding the command word.
     * @return Task number of task to be edited.
     * @throws DukeException If no task number is provided.
     */
    public static int getTaskNumber(String des) throws DukeException {
        String description = des.trim();
        if (description.isEmpty()) {
            throw new DukeException("I do not know which task to change!\n");
        } else {
            int taskNumber = Integer.parseInt(description);
            return taskNumber;
        }
    }
}
