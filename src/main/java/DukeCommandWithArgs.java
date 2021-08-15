import java.util.Map;

public class DukeCommandWithArgs {
    private final DukeCommand baseCommand;
    private final String positionalArg;
    private final Map<String, String> namedArgs;

    public DukeCommandWithArgs(DukeCommand baseCommand, String positionalArg, Map<String, String> namedArgs) {
        this.baseCommand = baseCommand;
        this.positionalArg = positionalArg;
        this.namedArgs = namedArgs;
    }

    public boolean runWith(TaskList taskList, Ui ui, Storage storage) throws InvalidCommandException {
        return baseCommand.apply(taskList, ui, storage, positionalArg, namedArgs);
    }

    public DukeCommand getBaseCommand() {
        return baseCommand;
    }
}
