package duke.parser;

import java.time.format.DateTimeParseException;

import duke.command.Command;
import duke.command.CommandAdd;
import duke.command.CommandDelete;
import duke.command.CommandDone;
import duke.command.CommandExit;
import duke.command.CommandFind;
import duke.command.CommandList;
import duke.command.CommandUnknown;
import duke.exception.DukeException;

/**
 * Deals with the input given by the user and making sense of it.
 */
public class Parser {
    /**
     * Processes the input given by the user.
     * Returns the corresponding Command afterwards.
     *
     * @param input String representation of the input given by user.
     * @return The corresponding Command.
     * @throws DukeException Throw DukeException.
     */
    public static Command parse(String input) throws DukeException {
        input = input.strip();
        String[] inputs = input.split(" ");
        assert !inputs[0].equals("") : "input should not be empty!";
        String command = inputs[0];

        switch (command.toLowerCase()) {
        case "bye":
            return new CommandExit();
        case "list":
            return new CommandList();
        case "done":
            if (inputs.length == 1) {
                throw new DukeException("Insufficient input received! "
                        + "Please indicate the task number of the completed task.");
            } else {
                try {
                    int index = Integer.parseInt(inputs[1]);

                    return new CommandDone(index);
                } catch (NumberFormatException e) {
                    throw new DukeException("Invalid input! Please enter the task number after 'done'.");
                }
            }
        case "delete":
            if (inputs.length == 1) {
                throw new DukeException("Insufficient input received! "
                        + "Please indicate the task number of the task you wish to delete.");
            }
            try {
                int index = Integer.parseInt(inputs[1]);

                return new CommandDelete(index);
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid input! Please enter the task number after 'delete'.");
            }
        case "find":
            try {
                String keyword = input.substring(5);
                return new CommandFind(keyword);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("Insufficient input received! Please add in keyword after 'find'.");
            }
        case "todo":
            if (inputs.length == 1) {
                throw new DukeException("Insufficient input received! "
                        + "Please add in description of the `Todo` task.");
            }

            int tFirst = input.indexOf(" ");
            String tDescription = input.substring(tFirst + 1);

            return new CommandAdd(0, tDescription);
        case "deadline":
            if (inputs.length == 1) {
                throw new DukeException("Insufficient input received! "
                        + "Please add in description of the `Deadline` task.");
            }

            if (!input.contains("/by")) {
                throw new DukeException("Invalid input! Please add in the deadline for the task.");
            }

            int dFirst = input.indexOf(" ");
            int dSecond = input.indexOf("/");

            try {
                String dDescription = input.substring(dFirst + 1, dSecond - 1);
                String by = input.substring(dSecond + 4);

                return new CommandAdd(1, dDescription, by);
            } catch (DateTimeParseException e) {
                throw new DukeException("Please follow this format when entering date and time:\n"
                        + "DD/MM/YYYY 24-Hour Time Format" + " e.g. (01/01/2020 2359)");
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("Please enter the Deadline task in this format:\n"
                        + "Deadline <task_description> /by <date_and_time>");
            }
        case "event":
            if (inputs.length == 1) {
                throw new DukeException("Insufficient input received! "
                        + "Please add in description of the `Event` task.");
            }

            if (!input.contains("/at")) {
                throw new DukeException("Invalid input! Please add in information about the event.");
            }

            int eFirst = input.indexOf(" ");
            int eSecond = input.indexOf("/");

            try {
                String eDescription = input.substring(eFirst + 1, eSecond - 1);
                String at = input.substring(eSecond + 4);

                return new CommandAdd(2, eDescription, at);
            } catch (DateTimeParseException e) {
                throw new DukeException("Please follow this format when entering date and time:\n"
                        + "DD/MM/YYYY 24-Hour Time Format" + " e.g. (01/01/2020 2359)");
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("Please enter the Event task in this format:\n"
                        + "Event <task_description> /by <date_and_time>");
            }
        default:
            return new CommandUnknown();
        }
    }
}
