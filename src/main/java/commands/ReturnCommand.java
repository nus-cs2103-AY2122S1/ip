package commands;

public class ReturnCommand extends Command {
    
    public ReturnCommand(String desc) {
        super(desc, true);
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
