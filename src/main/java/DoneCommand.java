package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class DoneCommand extends Command {
    
    private int toComplete;

    public DoneCommand(int toComplete) {
        this.toComplete = toComplete;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printDone(tasks, toComplete);
        tasks.get(toComplete).complete();
    }

}