package duke.command;

public class Command {
    /** The command type */
    public CommandType type;
    /** The payload */
    public Object payload;
    
    public Command(CommandType type, Object payload) {
        this.type = type;
        this.payload = payload;
    }
}
