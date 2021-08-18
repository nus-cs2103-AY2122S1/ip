import java.util.HashMap;
import java.util.Map;

public enum CommandType {

  BYE("bye", new ByeCommand()), LIST("list", new ListCommand()),
  DONE("done", new DoneCommand()), DEADLINE("deadline", new DeadlineCommand()),
  TODO("todo", new TodoCommand()), EVENT("event", new EventCommand()),
  DELETE("delete", new DeleteCommand()),;

  private final Command command;
  private final String name;
  private static final Map<String, CommandType> hash = new HashMap<>();

  static {
    for (CommandType c: CommandType.values()) {
      hash.put(c.getName(), c);
    }
  }

  private CommandType(String name, Command cmd) {
    this.name = name;
    this.command = cmd;
  }

  public String getName() {
    return this.name;
  }

  public Command getCommand() {
    return this.command;
  }

  public static Command getCommandFromName(String name) {
    CommandType cmdType = hash.get(name.toLowerCase());
    return cmdType == null ? new EmptyCommand() : cmdType.getCommand();
  } 

}
