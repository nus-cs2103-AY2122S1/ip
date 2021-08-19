public class ExitCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.dismiss();
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }

}
