package duke.commands;

import duke.main.Ui;
import duke.main.Storage;
import duke.tasks.Task;
import duke.main.TaskList;

public class DoneCommand extends Command {
    private int index;

    public DoneCommand(String userInput) {
        super(userInput);
        this.index = this.getIndex();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        //TaskList
        Task doneTask = taskList.markAsDone(index);

        //Storage
        storage.save(taskList);

        //Ui
        ui.showDoneTask(doneTask);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private int getIndex() {
        return Integer.parseInt(this.userInputList.get(1)) - 1;
    }
}

