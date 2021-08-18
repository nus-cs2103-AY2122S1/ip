package commands;

public class ReadCommand extends NonExecutableCommand {
    
    public ReadCommand(String desc) {
        super(desc);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }

    @Override
    public String toString() {
        return this.command_description;
    }
}
