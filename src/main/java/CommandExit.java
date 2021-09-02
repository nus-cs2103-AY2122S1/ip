public class CommandExit extends Command {

    public CommandExit() {
        super(false);
    }

    @Override
    public void execute(TaskList taskList, UserInterface ui) {
        ui.displayFarewell();
    }
}
