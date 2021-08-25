package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public static Command parse(String input) throws DukeException{
        String[] x = input.split(" ");
        String cmd = x[0];
        if (x.length == 1) {
            if ("bye".equals(cmd)) {
                return new ExitCommand();
            } else if ("list".equals(cmd)) {
                return new ListCommand();
            } else if ("todo".equals(cmd) || "deadline".equals(cmd) || "event".equals(cmd)) {
                missingTaskName(cmd);
            } else if ("done".equals(cmd) || "delete".equals(cmd)) {
                throw new DukeException("Indicate a task number beside the command ☻");
            } else {
                throw new DukeException("☹︎wut☁︎☻ unknown command");
            }
        } else {
            if (cmd.equals("done")) {
                if (x.length > 2) {
                    throw new DukeException("Too many arguments for this command.");
                } else {
                    return new DoneCommand(getTaskNumber(x));
                }
            } else if (cmd.equals("delete")) {
                if (x.length > 2) {
                    throw new DukeException("Too many arguments for this command.");
                } else {
                    return new DeleteCommand(getTaskNumber(x));
                }
            } else if (cmd.equals("todo")) {
                return addToDo(input);
            } else if (cmd.equals("deadline")) {
                try {
                    return addDeadline(input);
                } catch (DateTimeParseException e) {
                    throw new DukeException("Invalid date and time input, indicate date in yyyy-MM-dd HH:mm format.");
                }
            } else if (cmd.equals("event")) {
                try {
                    return addEvent(input);
                } catch (DateTimeParseException e) {
                    throw new DukeException("Invalid date and time input, indicate date in yyyy-MM-dd HH:mm format.");
                }
            } else {
                // unknown command received
                throw new DukeException("☹︎wut☁︎☻ unknown command");
            }
        }
        throw new DukeException("");
    }

    public static void missingTaskName(String cmd) throws DukeException{
        String str = String.format("☹ OOPS!!! The description of a %s cannot be empty.", cmd);
        throw new DukeException(str);
    }

    public static Command addToDo(String input) {
        String name = input.substring(input.indexOf(" ") + 1).strip();
        ToDo t = new ToDo(name);
        return new AddCommand(t);
    }

    public static Command addDeadline(String input) throws DateTimeParseException {
        String name = input.substring(input.indexOf(" ") + 1, input.lastIndexOf("/by") - 1).strip();
        String by = input.substring(input.lastIndexOf("/by") + 4);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Deadline d = new Deadline(name, LocalDateTime.parse(by, formatter));
        return new AddCommand(d);
    }

    public static Command addEvent(String input) throws DateTimeParseException {
        String name = input.substring(input.indexOf(" ") + 1, input.lastIndexOf("/at") - 1).strip();
        String at = input.substring(input.lastIndexOf("/at") + 4);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Event e = new Event(name, LocalDateTime.parse(at, formatter));
        return new AddCommand(e);
    }

    public static int getTaskNumber(String[] inputArr) {
        return Integer.parseInt(inputArr[1]) - 1;
    }
}
