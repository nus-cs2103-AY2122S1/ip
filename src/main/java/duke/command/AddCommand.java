package duke.command;

public abstract class AddCommand implements Commandable {
    @Override
    public boolean isExit() {
        return false;
    }
}
