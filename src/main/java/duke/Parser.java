package duke;

import java.util.Locale;
import java.util.stream.Stream;

import duke.command.Command;
import duke.command.CommandsTypes;
import duke.command.HelpCommand;
import duke.task.Task;



/**
 * Class to parse the input by user.
 */
public class Parser {

    /**
     * Parses input by user.
     *
     * @param input the input by user.
     * @return the command to be executed.
     * @throws DukeException if input is invalid.
     */
    public static Command parse(String input) throws DukeException {
        if (input.replaceAll("\\s+", "").toLowerCase().equals("bye")) {
            return Command.makeCommand(CommandsTypes.EXIT);
        }
        if (input.replaceAll("\\s+", "").toLowerCase().equals("list")) {
            return Command.makeCommand(CommandsTypes.LIST);
        }
        String[] splitBySpace = input.split(" ");
        if (splitBySpace[0].toLowerCase().equals("help")) {
            return getCommand(CommandsTypes.HELP, splitBySpace);
        }
        if (splitBySpace.length < 2) {
            throw new DukeException("Invalid input.");
        }
        if (splitBySpace[0].toLowerCase().equals("done")) {
            return getCommand(splitBySpace[1], CommandsTypes.MARK_DONE, "Invalid index inputted after done. Please enter a positive integer");
        }
        if (splitBySpace[0].toLowerCase().equals("delete")) {
            return getCommand(splitBySpace[1], CommandsTypes.DELETE, "Invalid index inputted after delete. Please enter a positive integer");
        }

        if (splitBySpace[0].toLowerCase().equals("find")) {
            return getCommand(splitBySpace, CommandsTypes.FIND);
        }

        if (splitBySpace[0].toLowerCase().equals("tag")) {
            return getCommand(splitBySpace, CommandsTypes.TAG);
        }

        return getAddCommand(input, splitBySpace[0]);
    }

    private static Command getAddCommand(String input, String s) throws DukeException {
        String type = s.toLowerCase();
        String taskDescription = Stream.of(input.split(" "))
                .skip(1).reduce("", (x, y) -> x + " " + y);
        Task newTask = Task.makeTask(type, taskDescription);
        return Command.makeCommand(CommandsTypes.ADD, newTask);
    }

    private static Command getCommand(String[] splitBySpace, CommandsTypes commandType) throws DukeException {
        String[] keywords = new String[splitBySpace.length - 1];
        for (int i = 1; i < splitBySpace.length; i++) {
            keywords[i - 1] = splitBySpace[i];
        }
        return Command.makeCommand(commandType, keywords);
    }

    private static Command getCommand(String s, CommandsTypes commandType, String s2) throws DukeException {
        try {
            int index = Integer.parseInt(s);
            if (index < 1) {
                throw new NumberFormatException();
            }
            return Command.makeCommand(commandType, index);
        } catch (NumberFormatException e) {
            throw new DukeException(s2);
        }
    }

    private static Command getCommand(CommandsTypes commandsTypes, String[] splitBySpace) throws DukeException {
        if (splitBySpace.length < 2) {
            return new HelpCommand();
        }
        return new HelpCommand(splitBySpace[1].toLowerCase());
    }
}
