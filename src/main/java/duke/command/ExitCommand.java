package duke.command;

public class ExitCommand implements Command {
    @Override
    public String run() {
        return "Ok bye, see you later.";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
