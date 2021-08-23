package duke;

import duke.command.*;

import static java.lang.Integer.parseInt;

public class Parser {

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

        } else {
            throw new DukeException("I do not understand what that means :(");
        }
    }
}
