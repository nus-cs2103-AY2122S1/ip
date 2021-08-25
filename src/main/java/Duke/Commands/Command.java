package Duke.Commands;

public abstract class Command {

    private String command;

    public Command(String command) {
        this.command = command;
    }

    public boolean isExitCommand() {
        return (command.equalsIgnoreCase("bye"));
    }

    public abstract void execute();
}
