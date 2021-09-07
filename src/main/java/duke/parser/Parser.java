package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
        try {
            if (cmd.equals("bye")) {
                return new ByeCommand();
            } else if (cmd.equals("list")) {
                return new ListCommand();
            } else if (cmd.matches("^done\\s\\d+$")) {
                int serialNo = Integer.parseInt(cmd.substring(5));
                return new DoneCommand(serialNo);
            } else if (cmd.matches("^deadline\\s.*\\s/by\\s\\d{4}-\\d{2}-\\d{2}$")) {
                String[] splitText = cmd.substring(9).split(" /by ");
                Task task = new Deadline(splitText[0].trim(), LocalDate.parse(splitText[1]), false);
                return new AddCommand(task);
            } else if (cmd.matches("^event\\s.*\\s/at\\s\\d{4}-\\d{2}-\\d{2}$")) {
                String[] splitText = cmd.substring(6).split(" /at ");
                Task task = new Event(splitText[0].trim(), LocalDate.parse(splitText[1]), false);
                return new AddCommand(task);
            } else if (cmd.matches("^todo\\s*$")) {
                throw new DukeException("Sorry Boss, todo must be followed with a description.");
            } else if (cmd.matches("^todo\\s.*$")) {
                Task task = new ToDo(cmd.substring(5).trim(), false);
                return new AddCommand(task);
            } else if (cmd.matches("^delete\\s\\d+$")) {
                int serialNum = Integer.parseInt(cmd.split(" ")[1]);
                return new DeleteCommand(serialNum);
            } else if (cmd.matches("^find\\s.*$")) {
                return new FindCommand(cmd.split(" ")[1]);
            } else {
                throw new DukeException("Sorry Boss, I cannot understand the command.");
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Sorry Boss, please enter your date in the YYYY-MM-DD format.");
        }
    }
}
