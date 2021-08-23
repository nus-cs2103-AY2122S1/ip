public class CommandBye extends Command {
    public static final String KEYWORD = "bye";
    public static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";

    public void execute(TaskList tl, Storage st, Ui ui) {
        ui.printout(GOODBYE_MESSAGE);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
