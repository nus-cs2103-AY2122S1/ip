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

public class Parser {

    /**
     * Parses the command entered by the user and returns a executable
     * command object.
     *
     * @param command The command entered by the user.
     * @return A command object that can be executed.
     * @throws DukeException If the command is invalid
     */
    public static Command parse(String command) throws DukeException {
        if (command.equals("list")) {
            return new ListCommand();

        } else if (command.startsWith("done")) {
            int taskNumber = parseInt(command.split(" ")[1]);
            return new DoneCommand(taskNumber);

        } else if (command.startsWith("delete")) {
            int taskNumber = parseInt(command.split(" ")[1]);
            return new DeleteCommand(taskNumber);

        } else if (command.startsWith("todo")) {
            String[] splitCommand = command.split(" ", 2);
            if (splitCommand.length == 1) {
                throw new DukeException("Please fill in a description for todo.");
            }
            String description = splitCommand[1];
            return new TodoCommand(description);

        } else if (command.startsWith("deadline")) {
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

        } else if (command.startsWith(("event"))) {
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

        } else if (command.startsWith(("date"))) {
            String[] splitCommand = command.split(" ", 2);
            if (splitCommand.length == 1) {
                throw new DukeException("Please fill in a date");
            }
            String date = splitCommand[1];
            return new DateCommand(date);

        } else if (command.equals("bye")) {
            return new ByeCommand();

        } else if (command.startsWith("find")) {
            String[] splitCommand = command.split(" ");
            if (splitCommand.length == 1) {
                throw new DukeException("Please fill in a keyword");
            }

            if (splitCommand.length > 2) {
                throw new DukeException("Please only fill in one keyword");
            }

            String keyword = splitCommand[1];
            return new FindCommand(keyword);

        } else {
            throw new DukeException("I do not understand what that means :(");
        }
    }
}
