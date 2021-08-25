package duke;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.DoneTaskCommand;
import duke.command.ExitCommand;
import duke.command.PrintListCommand;
import duke.command.FindCommand;

public class Parser {

    /**
     * Checks if the user input contains a supported command.
     *
     * @param userInput String that user enters into chat bot
     * @return boolean, true if user input contains a supported command, otherwise false
     */
    private static String[] SUPPORTED_COMMANDS = {"bye", "list", "done", "deadline", "event", "todo", "delete", "find"};

    private static boolean isCommandSupported(String userInput) {
        boolean isSupported = false;
        for(int i = 0; i < SUPPORTED_COMMANDS.length; i++ ) {
            isSupported = isSupported || userInput.startsWith(SUPPORTED_COMMANDS[i]);
        }
        return isSupported;
    }

    /**
     * Returns corresponding Command after parsing user input.
     *
     * @param userInput String that user enters into chat bot
     * @return Command Command object depending on the user input
     * @throws DukeException when user enters an unknown command
     */
    public static Command parseCommand(String userInput) throws DukeException {
        if (!isCommandSupported(userInput)) {
            throw new DukeException("unknown duke command");
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
