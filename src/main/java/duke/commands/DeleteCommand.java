package duke.commands;

import duke.exceptions.NoSuchTaskException;
import duke.utils.TaskList;
import duke.utils.Ui;

public class DeleteCommand extends Command {
    private int index;

    private DeleteCommand(int index) {
        this.index = index;
    }

    public static DeleteCommand of(String index) {
        return new DeleteCommand(Integer.parseInt(index));
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws NoSuchTaskException {
        ui.print("Noted. I've removed this task:",
                tasks.deleteTask(index).toString(),
                String.format("Now you have %d %s in the list.", tasks.size(), tasks.size() == 1 ? "task" : "tasks"));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
