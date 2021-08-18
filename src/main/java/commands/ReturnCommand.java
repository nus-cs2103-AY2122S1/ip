package commands;

public class ReturnCommand extends NonExecutableCommand {
    
    public ReturnCommand(String desc) {
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
