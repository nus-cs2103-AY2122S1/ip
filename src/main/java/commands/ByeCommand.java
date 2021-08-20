package commands;

public class ByeCommand extends Command {

    public ByeCommand(String desc) {
        super(desc);
    }

    @Override
    public boolean isForStorage() {
        return false;
    }

    @Override
    public boolean isExecutable() {
        return false;
    }
    
    @Override
    public String toString() {
        return "Bye. Hope to see you again soon!";
    }
}
