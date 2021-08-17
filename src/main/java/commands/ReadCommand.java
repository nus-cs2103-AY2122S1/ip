package commands;

public class ReadCommand extends Command {
    
    public ReadCommand(String desc) {
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
