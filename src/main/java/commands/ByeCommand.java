package commands;

public class ByeCommand extends Command {

    public ByeCommand(String desc) {
        super(desc, false);
    }

    @Override
    public boolean isExitCommand() {
        return true;
    }

    @Override
    public String toString() {
        return "Bye. Hope to see you again soon!";
    }
}
