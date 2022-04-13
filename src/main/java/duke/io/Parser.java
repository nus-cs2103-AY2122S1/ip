package duke.io;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.commands.Command;
import duke.commands.CommandTypes;
import duke.exceptions.AuguryException;
import duke.exceptions.InvalidActionException;
import duke.exceptions.UnknownCommandException;
import duke.tasks.TaskStatuses;
import duke.tasks.TaskTypes;
import duke.util.Query;

/**
 * The {@code Parser} class handles command parsing.
 */
public class Parser {

    public Parser() { }

    /**
     * Converts the provided user {@code String input} into a {@code Command}.
     *
     * @param input {@code String} of user input.
     * @return {@code String} which corresponds to the command that the user entered.
     */
    public Command parse(String input) throws AuguryException {
        assert input.equals(input.toLowerCase());

        // search through valid CommandTypes and their aliases
        // gleaned from https://stackoverflow.com/questions/4197988/java-enum-valueof-with-multiple-values/4198066
        for (CommandTypes commandType : CommandTypes.values()) {
            for (String alias : commandType.getAliases()) {
                if (input.startsWith(alias)) {
                    String args = cleanCommandArguments(input, alias, commandType);
                    if (args != null && args.equals("")) {
                        throw new UnknownCommandException("Please enter an argument!");
                    }
                    return Command.of(commandType, args);
                }
            }
        }
        throw new UnknownCommandException("Unknown command entered");
    }

    /**
     * Creates an {@code ArrayList} of {@code Query} objects representing the items that the user wants to find.
     *
     * @param query {@code String} containing search items. Example: {@code [X] school 2021-01-31}
     * @return {@code ArrayList} of {@code Query} objects
     */
    public static ArrayList<Query> parseFindQueries(String query) {
        ArrayList<Query> res = new ArrayList<>();
        String[] queries = query.split(" ");
        for (String q : queries) {
            switch (q) {
            case "[x]":
                res.add(new Query("status", TaskStatuses.ISDONE));
                break;
            case "[]":
                res.add(new Query("status", TaskStatuses.ISNOTDONE));
                break;
            case "[t]":
                res.add(new Query("type", TaskTypes.TODO));
                break;
            case "[e]":
                res.add(new Query("type", TaskTypes.EVENT));
                break;
            case "[d]":
                res.add(new Query("type", TaskTypes.DEADLINE));
                break;
            default:
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDateTime d = LocalDate.parse(q, formatter).atStartOfDay();
                    res.add(new Query("datetime", d));
                } catch (DateTimeParseException e) {
                    res.add(new Query("string", q));
                }

            }
        }
        return res;
    }

    private String cleanCommandArguments(String input,
                                         String alias,
                                         CommandTypes commandType) throws InvalidActionException {
        if (commandType == CommandTypes.QUIT
                || commandType == CommandTypes.LIST) {
            return null;
        } else if (commandType == CommandTypes.DELETE
                || commandType == CommandTypes.MARKDONE) {
            String userInput = input.replace(alias, "").trim();
            checkIndexesAreValid(userInput);
            return userInput;
        } else if (commandType == CommandTypes.FIND) {
            String userInput = input.replace(alias, "").trim();
            return userInput;
        } else if (commandType == CommandTypes.MAKE) {
            return input;
        } else {
            return input;
        }
    }

    private void checkIndexesAreValid(String indexes) throws InvalidActionException {
        if (indexes.length() == 0) {
            throw new InvalidActionException("Please enter a valid task number.");
        }

        String[] xs = indexes.split(",");
        for (String s : xs) {
            if (!isPositiveInteger(s.trim())) {
                throw new InvalidActionException("Please enter a valid task number.");
            }
        }
    }

    private boolean isPositiveInteger(String s) {
        try {
            return Integer.parseInt(s) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
