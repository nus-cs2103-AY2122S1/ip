public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public HelpCommand() {

    }

    @Override
    public void execute(Tasklist list, Ui ui, Store storage) throws DukeException{
        ui.printHelpMessage();

    }

}
