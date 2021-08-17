package commands;

public class ListCommand extends Command {

    public ListCommand(String desc) {
        super(desc, false);
    };

    @Override
    public boolean isExitCommand() {
        return false;
    }
}