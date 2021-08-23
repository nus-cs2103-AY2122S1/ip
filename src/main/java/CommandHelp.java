public class CommandHelp extends Command {
    private static final String HELP_MESSAGE = "List of Commands:\n" +
            "List,\n" +
            "Done,\n" +
            "Todo,\n" +
            "Deadline,\n" +
            "Event,\n" +
            "Delete,\n" +
            "Check,\n" +
            "Bye";

    public static final String KEYWORD = "help";


    @Override
    public void execute(TaskList tl, Storage st, Ui ui) {
        ui.printout(HELP_MESSAGE);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
