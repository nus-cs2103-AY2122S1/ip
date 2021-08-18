package commands;

public abstract class NonExecutableCommand extends Command {

    public NonExecutableCommand(String desc) {
        super(desc);
    }

    @Override
    public boolean isExecutable() {
        return false;
    }

    public abstract boolean isExitCommand();

    
}
