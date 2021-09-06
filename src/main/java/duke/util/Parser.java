package duke.util;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.exceptions.UnclearInstructionException;
import duke.exceptions.UnknownException;

/**
 * Parser class to handle user commands.
 */
public class Parser {
    /**
     * Returns command objects according to user command.
     *
     * @param command User's command input.
     * @return Respective command object.
     * @throws UnknownException In case of errors.
     */
    public static Command parse(String command) throws UnknownException {
        if (command.startsWith("todo")) {
            return new AddCommand(command, "todo");
        } else if (command.startsWith("deadline")) {
            return new AddCommand(command, "deadline");
        } else if (command.startsWith("event")) {
            return new AddCommand(command, "event");
        } else if (command.startsWith("done")) {
            return new DoneCommand(command);
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (command.startsWith("delete")) {
            return new DeleteCommand(command);
        } else if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.startsWith("find")) {
            return new FindCommand(command);
        } else {
            throw new UnknownException();
        }
    }

    /**
     * Returns task description of a full command.
     *
     * @param text  Full command given by user.
     * @return Task description.
     * @throws UnclearInstructionException  If the given command cannot be properly extracted or has empty description.
     */
    public static String extractTaskDescription(String text) throws UnclearInstructionException {
        String[] contents = text.split(" ", 3);
        String task_type = contents[0];
        String description = "";

        if (contents.length != 3) {
            throw new UnclearInstructionException(
                    "OOPS!!! The description of " + task_type + " cannot be extracted properly.");
        }

        int istart = text.indexOf(" ");
        int iend = text.indexOf("/");

        description = text.substring(istart + 1, iend - 1);

        if (description.equals("")) {
            throw new UnclearInstructionException(
                    "OOPS!!! The description of " + task_type + " cannot be empty.");
        }
        return description;
    }

    /**
     * Returns event time of a full command.
     *
     * @param text  Full command given by user.
     * @return Task time.
     * @throws UnclearInstructionException If the given command cannot be properly extracted or has empty time.
     */
    public static String extractTaskTime(String text) throws UnclearInstructionException {
        String[] contents = text.split(" ", 3);
        String task_type = contents[0];
        if (contents.length != 3) {
            throw new UnclearInstructionException(
                    "OOPS!!! The description of " + task_type + " cannot be extracted properly.");
        }
        
        int iend = text.indexOf("/");
        String time = text.substring(iend + 4);

        if (time.equals("")) {
            throw new UnclearInstructionException("OOPS!!! The time of " + task_type + " cannot be empty.");
        }
        return time;
    }
}
