public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "BYE";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + "Terminates Duke";

    public ByeCommand(TaskHandler th, Storage str) {
        super(th, str);
    }

    @Override
    public void execute(String cmd) {
        Ui.bye();
        System.exit(0);
    }
}

