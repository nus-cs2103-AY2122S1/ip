package duke.ui;

import duke.command.Command;
import duke.command.CommandType;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDate;

public class Parser {
    public Command parse(String cmd) throws DukeException {
        if (cmd.equals("bye")) {
            return new Command(CommandType.BYE, null);
        } else if (cmd.equals("list")) {
            return new Command(CommandType.LIST, null);
        } else if (cmd.startsWith("done ")) {
            int serialNum = Integer.parseInt(cmd.substring(5).trim());
            return new Command(CommandType.DONE, serialNum);
        } else if (cmd.startsWith("deadline ")) {
            String[] splitText = cmd.substring(9).split(" /by ");
            Task task = new Deadline(splitText[0].trim(), LocalDate.parse(splitText[1].trim()), false);
            return new Command(CommandType.ADD, task);
        } else if (cmd.startsWith("event ")) {
            String[] splitText = cmd.substring(6).split(" /at ");
            Task task = new Event(splitText[0].trim(), LocalDate.parse(splitText[1].trim()), false);
            return new Command(CommandType.ADD, task);
        } else if (cmd.startsWith("todo ") || cmd.equals("todo")) {
            String trimmed = cmd.trim();
            if (trimmed.length() == 4) {
                throw new DukeException("Sorry Sir, todo must be followed with a description");
            }
            Task task = new ToDo(trimmed.substring(5).trim(), false);
            return new Command(CommandType.ADD, task);
        } else if (cmd.startsWith("delete ")) {
            int serialNum = Integer.parseInt(cmd.split(" ")[1]);
            return new Command(CommandType.DELETE, serialNum);
        } else if (cmd.startsWith("find ")) {
            return new Command(CommandType.FIND, cmd.split(" ")[1]);
        } else {
            throw new DukeException("Sorry Sir, I cannot understand the command");
        }
    }
}
