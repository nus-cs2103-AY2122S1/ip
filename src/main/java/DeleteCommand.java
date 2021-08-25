package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;


public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.printDelete(tasks, index);
        tasks.delete(index);
    }
    
}