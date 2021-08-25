public class ListCommand extends Command {
    ListCommand(String input) {
        super(input);
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui) {
        ui.printTasks(tasks);
        return false;
    }
}
