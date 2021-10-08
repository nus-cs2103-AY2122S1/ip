package duke.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * Represents a command parser that can parse a string input to a command.
 *
 * @param <T> The type of the command the command parser returns.
 */
public abstract class CommandParser<T extends Command> {
    /* The command this parser accepts. */
    private final String cmd;
    /* The array of arguments that this parser accepts. */
    private final String [] acceptArguments;
    /* The list of parsers that are registered with this parser. */
    private final List<CommandParser<?>> registeredParsers;

    private static class Argument implements Comparable<Argument> {
        private final int position;
        private final String arg;

        public Argument(int position, String arg) {
            this.position = position;
            this.arg = arg;
        }

        public String getArg() {
            return this.arg;
        }

        public String getValue(String input, Argument nextArg) {
            if (nextArg != null) {
                return input.substring(position + arg.length(), nextArg.position).trim();
            } else {
                return input.substring(position + arg.length()).trim();
            }
        }

        @Override
        public int compareTo(Argument o) {
            return this.position - o.position;
        }
    }

    /**
     * Creates a Command Parser with a command and accepted arguments.
     *
     * @param cmd The command this command parser accepts.
     * @param acceptArguments The list of arguments that this command parser accepts
     */
    public CommandParser(String cmd, String... acceptArguments) {
        this.cmd = cmd;
        this.acceptArguments = acceptArguments;
        this.registeredParsers = new ArrayList<>();
    }

    /**
     * Converts the provided argumentMap to the appropriate command.
     *
     * @param argumentMap The map mapping the respective arguments to their values.
     * @return A Command instance after parsing the input.
     * @throws DukeException If there was an error when parsing the argumentMap.
     */
    protected abstract T convertToCommand(Map<String, String> argumentMap) throws DukeException;

    /**
     * Returns true if this parser is a matching parser for the provided input by checking the command.
     *
     * @param input A string input to check with this parser.
     * @return true if this parser matches the command in the input or false otherwise.
     */
    private boolean isMatchingParserFor(String input) {
        if (this.cmd == null) {
            return false;
        } else {
            return input.matches(String.format("^%s(\\s.*)?$", this.cmd));
        }
    }

    /**
     * Finds a matching parser for the provided input. If this parser matches the input,
     * it returns itself. Otherwise, searches the list of registered parsers recursively
     * to find the matching parser. Returns null if no matching parsers were found.
     *
     * @param input The input to find the matching parser for.
     * @return A matching parser for the input or null if none were found.
     */
    private CommandParser<?> findMatchingParser(String input) {
        if (isMatchingParserFor(input)) {
            return this;
        } else {
            for (CommandParser<?> parser : registeredParsers) {
                CommandParser<?> matchedParser = parser.findMatchingParser(input);
                if (matchedParser != null) {
                    return matchedParser;
                }
            }
            return null;
        }
    }

    /**
     * Adds parsers to the current list of registered parsers.
     *
     * @param parsers The list of parsers to add to the list of registered parsers.
     */
    public final void registerParsers(CommandParser<?>... parsers) {
        registeredParsers.addAll(Arrays.asList(parsers));
    }

    /**
     * Converts the input to an argument map. This method uses the array of accepted arguments
     * in this parser to populate a hashmap with the arguments and their respective values as
     * given in the input. Returns the argument map.
     *
     * @param input The string input to convert into argument map.
     * @return The argument map after converting the input.
     */
    private Map<String, String> convertToArgumentMap(String input) {
        Map<String, String> argumentMap = new HashMap<>();
        List<Argument> arguments = new ArrayList<>();
        for (String arg : acceptArguments) {
            int position = input.indexOf(arg);
            if (position >= 0) {
                arguments.add(new Argument(position, arg));
            }
        }
        Collections.sort(arguments);
        for (int i = 0; i < arguments.size(); i++) {
            Argument currentArg = arguments.get(i);
            int next = i + 1;
            if (next < arguments.size()) {
                argumentMap.put(currentArg.getArg(), currentArg.getValue(input, arguments.get(next)));
            } else {
                argumentMap.put(currentArg.getArg(), currentArg.getValue(input, null));
            }
        }
        return argumentMap;
    }

    /**
     * Parses the given input to a command using only this parser.
     *
     * @param input The string input to parse.
     * @return The command after parsing the provided input.
     * @throws DukeException in the event there was an error wile parsing the command.
     */
    private Command parseBySelf(String input) throws DukeException {
        return convertToCommand(convertToArgumentMap(input));
    }

    /**
     * Parses the given input to a command. The parser parsing the input
     * may be this parser itself if this parser matches the input or from
     * the list of registered parsers.
     *
     * @param input The string input to parse.
     * @return The command after parsing the input.
     * @throws DukeException in the event no matching parser was found for the input.
     */
    public final Command parse(String input) throws DukeException {
        CommandParser<?> parser = findMatchingParser(input);
        if (parser != null) {
            return parser.parseBySelf(input);
        } else {
            throw new DukeException("Sorry Boss, I cannot understand the command");
        }
    }
}
