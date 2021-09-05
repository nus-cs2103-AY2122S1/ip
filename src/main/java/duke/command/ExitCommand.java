package duke.command;

public class ExitCommand implements Command {
    private static final String EXIT_MESSAGE = "Ok bye, see you later.";

    @Override
    public String run() {
        return EXIT_MESSAGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
