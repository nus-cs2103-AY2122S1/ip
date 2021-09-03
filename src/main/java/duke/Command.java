package duke;

public abstract class Command {
    private boolean hasMoreCommands;
    public abstract void execute(TaskList taskList, UserInterface ui);

    public Command() {
        hasMoreCommands = true;
    }

    public Command(boolean hasMoreCommands) {
        this.hasMoreCommands = hasMoreCommands;
    }

    public boolean shouldExecuteNext() {
        return hasMoreCommands;
    }
}
