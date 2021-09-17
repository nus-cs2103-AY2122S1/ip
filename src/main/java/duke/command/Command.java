package duke.command;

import java.util.Objects;

/**
 * Blueprint for a user command. Consists of a keyword and the rest of the command.
 */
public class Command {
    private CommandKeyword keyword;
    private String restOfCommand;

    /**
     * Constructs a user command.
     *
     * @param keyword The user command.
     * @param restOfCommand The rest of the command, if any.
     */
    public Command(CommandKeyword keyword, String restOfCommand) {
        this.keyword = keyword;
        this.restOfCommand = restOfCommand;
    }

    /**
     * Returns the command keyword of a command.
     *
     * @return The command keyword of a command.
     */
    public CommandKeyword getKeyword() {
        return this.keyword;
    }

    /**
     * Returns the rest of the command.
     *
     * @return The rest of the command.
     */
    public String getRestOfCommand() {
        return this.restOfCommand;
    }

    /**
     * Indicates whether some other object is "equal to" this Command instance.
     * @param o The reference object with which to compare.
     * @return True if the obj is an instance of None; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Command command = (Command) o;
        return keyword == command.keyword && Objects.equals(restOfCommand, command.restOfCommand);
    }
}
