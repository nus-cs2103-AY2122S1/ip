package duke;

import static java.lang.Integer.parseInt;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DateCommand;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;

/** The class takes in a command, parses it and returns an executable Command object. */
public class Parser {

    /**
     * Returns a ListCommand object based on the user's input.
     *
     * @param command The command entered by the user.
     * @return A ListCommand object.
     */
    private static ListCommand getListCommand(String command) {
        return new ListCommand();
    }

    /**
     * Returns a DoneCommand object based on the user's input.
     *
     * @param command The command entered by the user.
     * @return A DoneCommand object.
     */
    private static DoneCommand getDoneCommand(String command) {
        int taskNumber = parseInt(command.split(" ")[1]);
        return new DoneCommand(taskNumber);
    }

    /**
     * Returns a DeleteCommand object based on the user's input.
     *
     * @param command The command entered by the user.
     * @return A DeleteCommand object.
     */
    private static DeleteCommand getDeleteCommand(String command) {
        int taskNumber = parseInt(command.split(" ")[1]);
        return new DeleteCommand(taskNumber);
    }

    /**
     * Returns a TodoCommand object based on the user's input.
     *
     * @param command The command entered by the user.
     * @return A TodoCommand object.
     * @throws DukeException If there is no description for the task.
     */
    private static TodoCommand getTodoCommand(String command) throws DukeException {
        String[] splitCommand = command.split(" ", 2);
        if (splitCommand.length == 1) {
            throw new DukeException("Please fill in a description for todo.");
        }
        String description = splitCommand[1];
        return new TodoCommand(description);
    }

    /**
     * Returns a DeadlineCommand object based on the user's input.
     *
     * @param command The command entered by the user.
     * @return A DeadlineCommand object.
     * @throws DukeException If there is no description for the task, or the task is missing a deadline.
     */
    private static DeadlineCommand getDeadlineCommand(String command) throws DukeException {
        String[] splitCommand = command.split(" ", 2);
        if (splitCommand.length == 1) {
            throw new DukeException("Please fill in a description for deadline.");
        }

        String description = splitCommand[1];
        String[] splitDescription = description.split(" /by ");
        if (splitDescription.length == 1) {
            throw new DukeException("Please add in /by, following by a dateline.");
        }

        description = splitDescription[0];
        String deadline = splitDescription[1];
        return new DeadlineCommand(description, deadline);
    }

    /**
     * Returns a EventCommand object based on the user's input.
     *
     * @param command The command entered by the user.
     * @return A EventCommand object.
     * @throws DukeException If there is no description for the task, or the task is missing a time.
     */
    private static EventCommand getEventCommand(String command) throws DukeException {
        String[] splitCommand = command.split(" ", 2);
        if (splitCommand.length == 1) {
            throw new DukeException("Please fill in a description for event.");
        }

        String description = splitCommand[1];
        String[] splitDescription = description.split(" /at ");
        if (splitDescription.length == 1) {
            throw new DukeException("Please add in /at, followed by the event's time.");
        }

        description = splitDescription[0];
        String time = splitDescription[1];
        return new EventCommand(description, time);
    }

    /**
     * Returns a DateCommand object based on the user's input.
     *
     * @param command The command entered by the user.
     * @return A DateCommand object.
     * @throws DukeException If there is no valid date in the user's input.
     */
    private static DateCommand getDateCommand(String command) throws DukeException {
        String[] splitCommand = command.split(" ", 2);
        if (splitCommand.length == 1) {
            throw new DukeException("Please fill in a date");
        }
        String date = splitCommand[1];
        return new DateCommand(date);
    }

    /**
     * Returns a ByeCommand object based on the user's input.
     *
     * @param command The command entered by the user.
     * @return A ByeCommand object.
     */
    private static ByeCommand getByeCommand(String command) {
        return new ByeCommand();
    }

    /**
     * Returns a FindCommand object based on the user's input.
     *
     * @param command The command entered by the user.
     * @return A FindCommand object.
     * @throws DukeException If there is no valid keyword, or there is more than one keyword.
     */
    private static FindCommand getFindCommand(String command) throws DukeException {
        String[] splitCommand = command.split(" ");
        if (splitCommand.length == 1) {
            throw new DukeException("Please fill in a keyword");
        }

        if (splitCommand.length > 2) {
            throw new DukeException("Please only fill in one keyword");
        }

        String keyword = splitCommand[1];
        return new FindCommand(keyword);
    }

    /**
     * Parses the command entered by the user and returns a executable
     * command object.
     *
     * @param command The command entered by the user.
     * @return A command object that can be executed.
     * @throws DukeException If the command is invalid
     */
    public static Command parse(String command) throws DukeException {
        if (command.equals("ls")) {
            return getListCommand(command);
        } else if (command.startsWith("dt")) {
            return getDateCommand(command);
        } else if (command.startsWith("rm")) {
            return getDeleteCommand(command);
        } else if (command.startsWith("td")) {
            return getTodoCommand(command);
        } else if (command.startsWith("dl")) {
            return getDeadlineCommand(command);
        } else if (command.startsWith(("ev"))) {
            return getEventCommand(command);
        } else if (command.startsWith(("d"))) {
            return getDoneCommand(command);
        } else if (command.equals("q")) {
            return getByeCommand(command);
        } else if (command.startsWith("ff")) {
            return getFindCommand(command);
        } else {
            throw new DukeException("I do not understand what that means :(");
        }
    }
}
