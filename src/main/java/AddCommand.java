public class AddCommand extends Command {
    private String input;

    public AddCommand(String input) {
        this.input = input;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(input);
    }

    public boolean isExit() {
        return false;
    }
}
