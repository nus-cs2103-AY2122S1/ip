package duke.result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.util.Pair;

import duke.util.DukeException;
import duke.util.Parser;
import duke.util.Ui;

/**
 * A class that contains the base functionality for handling aliases,
 * including adding, deleting and listing them.
 */
public class AliasHandler {
    // Stores aliases in the form of: alias(key):command(value)
    private final HashMap<String, String> aliasHashMap;

    /**
     * Creates an alias handler with no aliases.
     */
    public AliasHandler() {
        this.aliasHashMap = new HashMap<>();
    }

    /**
     * Creates an alias handler with aliases.
     *
     * @param aliasHashMap A hashmap that matches aliases to corresponding commands.
     */
    public AliasHandler(HashMap<String, String> aliasHashMap) {
        this.aliasHashMap = aliasHashMap;
    }

    /**
     * Returns the command corresponding to the given alias.
     * If the alias given does not correspond to any command, the input alias itself
     * will be returned.
     *
     * @param input A string representing the command for which the aliases are being checked.
     * @return The command string that the alias corresponds to, or the input alias
     * if there is no such association.
     */
    public String convertAlias(String input) {
        boolean isAlias = aliasHashMap.containsKey(input);
        if (!isAlias) {
            return input;
        }
        return aliasHashMap.get(input);
    }

    /**
     * Adds an alias which corresponds to a given command.
     * This will work if the user chooses to add an alias to an alias.
     * E.g. exit is added as an alias for bye. Then quit can be added as an alias for exit,
     * while having the same functionality of bye.
     * Aliases are not case sensitive. (e.g. quit is treated the same as QUIT)
     *
     * @param input String containing user input.
     * @return A pair that contains an AliasHandler instance with the new alias,
     * and a string that represents an output message.
     * @throws DukeException If input contains |, or is in an invalid format.
     */
    public Pair<AliasHandler, String> addAlias(Ui ui, String input) throws DukeException {
        HashMap<String, String> newAliasHashMap = new HashMap<>(this.aliasHashMap);
        if (input.contains("|")) {
            throw new DukeException("Input contains |, which is an invalid/reserved character.");
        }
        String[] splitInputs = input.split(" ");
        if (splitInputs.length != 3) {
            throw new DukeException("Input should be of the format: addalias [command] [alias]");
        }
        String alias = splitInputs[2].toLowerCase();
        if (isCommandKeyword(alias)) {
            throw new DukeException("This alias is already reserved for an actual command.");
        }
        if (aliasHashMap.containsKey(alias)) {
            String errorMessage = String.format("Alias %s already corresponds to command %s.",
                    alias, aliasHashMap.get(alias));
            throw new DukeException(errorMessage);
        }
        String command = convertAlias(splitInputs[1]);
        // If the command given is invalid, this throws a DukeException
        Parser.parseCommandFromInput(command);

        newAliasHashMap.put(alias, command);
        AliasHandler newAliasHandler = new AliasHandler(newAliasHashMap);
        String message = ui.showAddAliasMessage(alias, command);
        return new Pair<>(newAliasHandler, message);
    }

    /**
     * Checks if an alias conflicts with an actual command word.
     *
     * @param alias An input string representing an alias.
     * @return True if the alias clashes with an actual command, false otherwise.
     */
    private boolean isCommandKeyword(String alias) {
        switch (alias) {
        case "addalias":
        case "bye":
        case "list":
        case "listalias":
        case "done":
        case "todo":
        case "deadline":
        case "event":
        case "delete":
        case "deletealias":
        case "ondate":
        case "due":
        case "find":
            return true;
        default:
            return false;
        }
    }

    /**
     * Deletes an alias which corresponds to a given command.
     * Aliases are not case sensitive. (e.g. quit is treated the same as QUIT)
     *
     * @param input String containing user input.
     * @return A pair that contains an AliasHandler instance with the new alias,
     * and a string that represents an output message.
     * @throws DukeException If input contains |, or is in an invalid format.
     */
    public Pair<AliasHandler, String> deleteAlias(Ui ui, String input) throws DukeException {
        HashMap<String, String> newAliasHashMap = new HashMap<>(this.aliasHashMap);
        String[] splitInputs = input.split(" ");
        if (splitInputs.length != 2) {
            throw new DukeException("Input should be of the format: deletealias [alias]");
        }
        String alias = splitInputs[1].toLowerCase();
        if (!aliasHashMap.containsKey(alias)) {
            String errorMessage = String.format("Alias %s does not correspond to any command.",
                    alias);
            throw new DukeException(errorMessage);
        }
        String command = newAliasHashMap.remove(alias);
        AliasHandler newAliasHandler = new AliasHandler(newAliasHashMap);
        String message = ui.showDeleteAliasMessage(alias, command);
        return new Pair<>(newAliasHandler, message);
    }

    /**
     * Converts the alias data into its corresponding config file data format.
     *
     * @return A string to represent the data of all aliases for the config file.
     */
    public String toConfigData() {
        String[] aliases = aliasHashMap.keySet().toArray(new String[0]);
        StringBuilder dataString = new StringBuilder();
        for (String alias : aliases) {
            String command = aliasHashMap.get(alias).toLowerCase();
            dataString.append(alias)
                    .append("|")
                    .append(command)
                    .append(System.lineSeparator());
        }
        return dataString.toString().trim();
    }

    /**
     * Converts the AliasHandler data into a string format.
     * The aliases are listed in the following format:
     * commandKeyword1:
     * 1. alias1
     * 2. alias2
     * commandKeyword2:
     * 1. alias1
     * 2. alias2
     * ...and so on and so forth.
     *
     * @return A string representation of the AliasHandler instance.
     */
    @Override
    public String toString() {
        String[] aliases = aliasHashMap.keySet().toArray(new String[0]);
        if (aliases.length == 0) {
            return "No aliases found.";
        }
        StringBuilder dataString = new StringBuilder();
        // Group up all aliases by the command they correspond to
        HashMap<String, List<String>> commandHashMap = new HashMap<>();
        for (String alias : aliases) {
            String command = aliasHashMap.get(alias).toLowerCase();
            // If this alias is the first one that corresponds to this command,
            // initialize the arraylist
            List<String> aliasList = new ArrayList<>();
            // Else, get the existing list of aliases for this command
            if (commandHashMap.containsKey(command)) {
                aliasList = commandHashMap.get(command);
            }
            aliasList.add(alias);
            commandHashMap.put(command, aliasList);
        }

        String[] commands = commandHashMap.keySet().toArray(new String[0]);
        for (String command : commands) {
            dataString.append(command)
                    .append(":\n");
            List<String> aliasList = commandHashMap.get(command);
            for (int i = 0; i < aliasList.size(); i++) {
                String aliasString = String.format("\t%d. %s\n", i + 1, aliasList.get(i));
                dataString.append(aliasString);
            }
        }
        return dataString.toString().trim();
    }
}
