package duke;

public class ByeCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Bye! Hope to see you again soon!");
    }

    @Override
    public boolean isBye() {
        return true;
    }
}
