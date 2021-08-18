package commands;
import java.util.List;

public abstract class ExecutableCommand extends Command {

    public ExecutableCommand(String desc) {
        super(desc);
    }

    @Override
    public boolean isExecutable() {
        return true;
    }

    public abstract void execute(List<Command> commandList, List<Boolean> completedList);

}

