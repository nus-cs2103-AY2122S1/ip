package duke.ui;

import java.time.LocalDate;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Represents the parser that is used to parse the user's commands.
 */
public class Parser {
    /**
     * Returns a command after parsing the given command string.
     *
     * @param cmd The command string to parse.
     * @return The command after parsing the command string.
     * @throws DukeException when the given command string cannot be parsed.
     */
    public Command parse(String cmd) throws DukeException {
        if (cmd.equals("bye")) {
            return new ByeCommand();
        } else if (cmd.equals("list")) {
            return new ListCommand();
        } else if (cmd.startsWith("done ")) {
            int serialNo = Integer.parseInt(cmd.substring(5).trim());
            return new DoneCommand(serialNo);
        } else if (cmd.startsWith("deadline ")) {
            String[] splitText = cmd.substring(9).split(" /by ");
            Task task = new Deadline(splitText[0].trim(), LocalDate.parse(splitText[1].trim()), false);
            return new AddCommand(task);
        } else if (cmd.startsWith("event ")) {
            String[] splitText = cmd.substring(6).split(" /at ");
            Task task = new Event(splitText[0].trim(), LocalDate.parse(splitText[1].trim()), false);
            return new AddCommand(task);
        } else if (cmd.startsWith("todo ") || cmd.equals("todo")) {
            String trimmed = cmd.trim();
            if (trimmed.length() == 4) {
                throw new DukeException("Sorry Sir, todo must be followed with a description");
            }
            Task task = new ToDo(trimmed.substring(5).trim(), false);
            return new AddCommand(task);
        } else if (cmd.startsWith("delete ")) {
            int serialNum = Integer.parseInt(cmd.split(" ")[1]);
            return new DeleteCommand(serialNum);
        } else if (cmd.startsWith("find ")) {
            return new FindCommand(cmd.split(" ")[1]);
        } else {
            throw new DukeException("Sorry Sir, I cannot understand the command");
        }
    }
}
