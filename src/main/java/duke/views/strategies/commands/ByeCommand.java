package duke.views.strategies.commands;

/**
 * Encapsulates a command to exit the program.
 */
public class ByeCommand extends Command {
    private static Command singleInstance;

    public static Command getInstance() {
        if (singleInstance == null) {
            singleInstance = new ByeCommand();
        }
        return singleInstance;
    }

    @Override
    public String produce(String _query) {
        return "See you again" + System.lineSeparator();
    }
}
