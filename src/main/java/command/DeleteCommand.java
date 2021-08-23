package command;

import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {
    private final int index;
    
    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        taskList.deleteTask(index);
    }
}
