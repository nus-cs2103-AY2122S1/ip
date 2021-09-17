package duke.command;

import java.util.ArrayList;
import java.util.List;

import duke.exception.DukeException;
import duke.io.AliasStorage;
import duke.task.TaskList;

/**
 * A command keeps track of its string and parses user input using its parse function.
 */
public abstract class Command {
    private String mainCommand;
    private List<String> aliases = new ArrayList<>();

    /**
     * Sets the main command of this action.
     *
     * @param mainCommand Default user input's first word to execute this action.
     */
    protected void setMainCommand(String mainCommand) {
        this.mainCommand = mainCommand;
    }

    protected void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }

    /**
     * Returns whether the provided command is an alias for this action.
     *
     * @param command String to check against the list of aliases.
     */
    public boolean containsCommand(String command) {
        return mainCommand.equals(command) || aliases.contains(command);
    }

    /**
     * Adds the alias as a possible command to be used for this action.
     *
     * @param alias New command that can be used for this action.
     * @return The response to inform the user that the alias has been added
     */
    public String addAlias(String alias) throws DukeException {
        aliases.add(alias);
        AliasStorage.save();

        return alias + " added as an alias for " + mainCommand + "!";
    }

    /**
     * Returns a String representing the aliases of the command to be saved.
     *
     * @return String representing the aliases of the command.
     */
    public String getSaveFormat() {
        StringBuilder sb = new StringBuilder(mainCommand);

        for (String alias : aliases) {
            sb.append(',');
            sb.append(alias);
        }

        return sb.toString();
    }

    /**
     * Returns the default command of this action.
     *
     * @return The default command of this action.
     */
    public String getMainCommand() {
        return mainCommand;
    }

    /**
     * Parses the user input based on the command's possible parameters.
     *
     * @param input Full user input.
     * @param taskList The list of tasks.
     * @return The response.
     * @throws DukeException Any exception handled when executing the command.
     */
    public abstract String parse(String input, TaskList taskList) throws DukeException;
}
