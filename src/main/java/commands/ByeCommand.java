package commands;

public class ByeCommand extends NonExecutableCommand {

    public ByeCommand(String desc) {
        super(desc);
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
