package duke;

import duke.command.*;

public class Parser {

    public static Command parse(String command) throws DukeException {
        if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.startsWith("delete ")) {
            String taskNo = command.substring(7);
            return new DeleteCommand(taskNo);
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (command.startsWith("done ")) {
            String taskNo = command.substring(5);
            return new DoneCommand(taskNo);
        } else if (command.startsWith("occurring on ")) {
            String date = command.substring(13);
            return new OccurringOnCommand(date);
        } else if (command.startsWith("todo ") || command.startsWith("deadline ") || command.startsWith("event ")) {
            return new AddCommand(command);
        } else if (command.startsWith("find ")) {
            return new FindCommand(command.substring(5));
        } else if (command.equals("help")) {
            return new HelpCommand();
        } else {
            return new UnknownCommand();
        }
    }

}
