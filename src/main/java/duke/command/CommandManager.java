package duke.command;

import java.util.HashMap;
import java.util.Map;

import duke.exception.InvalidCommandException;

/**
 * Manager of all Duke's commands. All commands have to be registered/added to
 * its registry to take effect.
 */
public class CommandManager {
    private final Map<String, Command> registry;

    /**
     * Creates a command manager that manages a collection of predeclared commands.
     * All registered commands must have a unique label for it to be a
     * distinguishable callable.
     */
    public CommandManager() {
        this.registry = new HashMap<>();
    }

    /**
     * Puts commands under its registry, mapped to its label. All registered command
     * must have a unique label for it to be a distinguishable callable.
     *
     * @param commands to be registered
     */
    public void registerCommands(Command... commands) {
        for (Command command : commands) {
            this.registry.put(command.getLabel(), command);
        }
    }

    /**
     * Processes the given input (user's input) and search for its corresponding
     * command to be executed with the given argument.
     *
     * @param input straight out from the command line
     */
    public void parseInput(String input) {
        String[] arr = input.split(" ", 2);
        Command command = registry.get(arr[0]);
        if (command != null) {
            command.exec(arr.length <= 1 ? null : arr[1]);
        } else {
            this.defaultExec();
        }
    }

    /**
     * Executes the default behaviour with the input if there are no commands
     * called.
     */
    private void defaultExec() {
        throw new InvalidCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

}
