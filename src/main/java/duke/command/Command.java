package duke.command;

/**
 * Represents a command with the type and payload of the command.
 */
public class Command {
    public CommandType type;
    public Object payload;

    /**
     * Constructs a command class with the type and the payload.
     * 
     * @param type The type of the command.
     * @param payload The payload of the command.
     */
    public Command(CommandType type, Object payload) {
        this.type = type;
        this.payload = payload;
    }
}
