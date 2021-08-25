package duke;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.DoneTaskCommand;
import duke.command.ExitCommand;
import duke.command.PrintListCommand;

public class Parser {
    private final static String[] SUPPORTED_COMMANDS = {"bye", "list", "done", "deadline", "event","todo", "delete"};

    private static boolean isCommandSupported(String userInput) {
        boolean isSupported = false;
        for(int i = 0; i < SUPPORTED_COMMANDS.length; i++ ) {
            isSupported = isSupported || userInput.startsWith(SUPPORTED_COMMANDS[i]);
        }
        return isSupported;
    }

    public static Command parseCommand(String userInput) throws DukeException {
        if (!isCommandSupported(userInput)) {
            throw new DukeException("unknown duke.command");
        }

        if (userInput.equals("bye")) {
            return new ExitCommand(userInput);
        }

        if (userInput.equals("list")) {
            return new PrintListCommand(userInput);
        }

        if (userInput.startsWith("done")) {
            return new DoneTaskCommand(userInput);
        }

        if (userInput.startsWith("deadline")) {
            return new AddCommand(userInput, "deadline");
        }

        if (userInput.startsWith("event")) {
            return new AddCommand(userInput, "event");
        }

        if (userInput.startsWith(("todo"))) {
            return new AddCommand(userInput, "todo");
        }

        if (userInput.startsWith("delete")) {
            return new DeleteCommand(userInput);
        }

        return null;
    }







}
