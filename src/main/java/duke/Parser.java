package duke;

import duke.command.*;

public class Parser {
    private static String[] SUPPORTED_COMMANDS = {"bye", "list", "done", "deadline", "event","todo", "delete", "find"};

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
        
        if (userInput.startsWith("find")) {
            return new FindCommand(userInput);
        }

        return null;
    }







}
