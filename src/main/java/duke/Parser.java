package duke;

import java.util.ArrayList;

import duke.commands.*;

/**
 * Encapsulates a parser class which parses user input
 * and creates a different command object depending on the input.
 */
public class Parser {
    private final Command[] COMMAND_LIST = {
        new ListCommand(""),
        new ByeCommand(""),
        new TodoCommand(""),
        new FormatsCommand(""),
        new DoneCommand(""),
        new DeleteCommand(""),
        new DeadlineCommand(""),
        new EventCommand(""),
        new FindCommand(""),
        new TagCommand("")
    };

    /**
     * Takes in a string input from the user and returns the invoked command object.
     *
     * @param input The string input by the user.
     * @return The created command object.
     */
    public Command parse(String input) {
        for (Command c : COMMAND_LIST) {
            if (input.startsWith(c.getCommandPrefix())) {
                if (input.equals(c.getCommandPrefix())) {
                    return c.of("");
                }
                return c.of(input.substring(c.getCommandPrefix().length() + 1));
            }
        }
        return null;
    }

    /**
     * Parses the command input by the user and splits it based on the given splitters
     * into distinct arguments.
     *
     * @param command The command input by the user.
     * @param splitters The String array of given splitters.
     * @return The ArrayList of arguments.
     */
    public static ArrayList<String> parseCommandArguments(String command, String ... splitters) {
        ArrayList<String> result = new ArrayList<>();
        int firstSplitterIndex = command.indexOf("/");

        if (firstSplitterIndex == -1) {
            result.add(command);
            return result;
        }

        result.add(command.substring(0, firstSplitterIndex));

        for (String s : splitters) {
            String[] argument = command.split(" /" + s + " ");
            int nextSplitterIndex = argument[1].indexOf("/");
            if (nextSplitterIndex == -1) {
                result.add(argument[1]);
            } else {
                result.add(argument[1].substring(0, nextSplitterIndex - 1));
            }
        }
        return result;
    }

    /**
     * Parses the Strings that are read from the output file and splits it based on the given splitters
     * into distinct arguments.
     *
     * @param outputString The line read from the output file.
     * @param splitters The given splitters.
     * @return The ArrayList of arguments.
     */
    public static ArrayList<String> parseFileOutputArguments(String outputString, String ... splitters) {
        ArrayList<String> result = new ArrayList<>();
        int firstSplitterIndex = outputString.indexOf("(");

        if (firstSplitterIndex == -1) {
            result.add(outputString);
            return result;
        }

        result.add(outputString.substring(0, firstSplitterIndex - 1));
        for (String s : splitters) {
            String[] argument = outputString.split(" \\(" + s + ": ");
            int closeParenIndex = argument[1].indexOf(")");
            result.add(argument[1].substring(0, closeParenIndex));
        }
        return result;

    }

}
