public class Command {
    public CommandType type;
    public Object payload;
    
    public Command(CommandType type, Object payload) {
        this.type = type;
        this.payload = payload;
    }
}
