package duke.command;

public abstract class AddTaskCommand implements Command {
    @Override
    public boolean isExit() {
        return false;
    }
}
