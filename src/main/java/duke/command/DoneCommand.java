package duke.command;

import duke.exception.BadInputFormatException;
import duke.exception.NoSuchTaskException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class DoneCommand extends Command {
    private int index;

    private DoneCommand(int index) {
        this.index = index;
    }

    public static DoneCommand of(String index) throws BadInputFormatException {
        try {
            return new DoneCommand(Integer.parseInt(index) - 1);
        } catch (NumberFormatException e) {
            throw new BadInputFormatException();
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NoSuchTaskException {
        ui.print("Nice! I've marked this task as done:", tasks.getTask(index).markTaskAsDone().toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
