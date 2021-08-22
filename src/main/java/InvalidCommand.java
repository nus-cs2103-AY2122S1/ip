public class InvalidCommand extends Command {

    public static final String COMMAND_WORD = "error";

    public InvalidCommand() {

    }

    @Override
    public void execute(Tasklist list, Ui ui, Store storage) throws DukeException {
        throw new InvalidCommandException();

    }

}
