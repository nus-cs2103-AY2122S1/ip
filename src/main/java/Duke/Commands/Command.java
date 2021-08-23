package Duke.Commands;

public abstract class Command {

    private String command;

    public Command(String command) {
        this.command = command;
    }

    public boolean isExitCommand() {
        return (command.equals("bye") || command.equals("Bye") || command.equals("BYE"));
    }

    public abstract void execute();
}
