package duke.utils;

import duke.commands.Command;
import duke.exceptions.EmptyTaskDescriptionException;
import duke.exceptions.UnknownInputException;

public class Parser {

    public static Command parse(String fullCommand) throws UnknownInputException, EmptyTaskDescriptionException {
        int taskNum;
        if (true == fullCommand.startsWith("bye")) {
            return new Command.ExitCommand();
        } else if (true == fullCommand.startsWith("list")) {
            return new Command.ListAllCommand();
        } else if (true == fullCommand.startsWith("done")) {
            taskNum = Integer.parseInt(fullCommand.substring(5)) - 1;
            return new Command.DoneCommand(taskNum);
        } else if (true == fullCommand.startsWith("todo")) {
            try {
                String description = fullCommand.substring(5);
                return new Command.ToDoCommand(description);
            } catch (StringIndexOutOfBoundsException strE) {
                throw new EmptyTaskDescriptionException("todo");
            }
        } else if (true == fullCommand.startsWith("deadline")) {
            try {
                int sep = fullCommand.indexOf('/', 9);
                String descPart = fullCommand.substring(9, sep - 1);
                String byPart = fullCommand.substring(sep + 4);
                return new Command.DeadlineCommand(descPart, byPart);
            } catch (StringIndexOutOfBoundsException strE) {
                throw new EmptyTaskDescriptionException("deadline");
            }
        } else if (true == fullCommand.startsWith("event")) {
            try {
                int sep = fullCommand.indexOf('/', 6);
                String descPart = fullCommand.substring(6, sep - 1);
                String atPart = fullCommand.substring(sep + 1);
                return new Command.EventCommand(descPart, atPart);
            } catch (StringIndexOutOfBoundsException strE) {
                throw new EmptyTaskDescriptionException("event");
            }
        } else if (true == fullCommand.startsWith("delete")) {
            taskNum = Integer.parseInt(fullCommand.substring(7)) - 1;
            return new Command.DeleteCommand(taskNum);
        } else {
            UnknownInputException e = new UnknownInputException();
            throw e;
        }
    }
}
