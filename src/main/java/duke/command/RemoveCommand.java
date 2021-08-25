package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

public class RemoveCommand extends Command {
    private final int indexToRemove;

    public RemoveCommand(int indexToRemove){
        this.indexToRemove = indexToRemove;
    }
    @Override
    public boolean isExit() {
        return false;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task toRemove = taskList.remove(this.indexToRemove);
        String message = "Noted. I've removed this task:\n" + toRemove + "\nNow you have "
                + taskList.getSize() + " tasks in the list";
        ui.print(message);
    }


}
