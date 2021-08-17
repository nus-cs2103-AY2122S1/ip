package commands;

public abstract class Command {
    private final boolean isForStorage;
    protected final String command_description;

    Command(String command_description, boolean isForStorage) {
        this.command_description = command_description;
        this.isForStorage = isForStorage;
    }
    
    public boolean isForStoring() {
        return this.isForStorage;
    }

    public abstract boolean isExitCommand();

}
