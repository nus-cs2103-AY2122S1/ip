package duke.commands;

import duke.exceptions.DukeException;
import duke.main.Ui;
import duke.main.Storage;
import duke.tasks.Task;
import duke.main.TaskList;

public class DoneCommand extends Command {
    private int index;

    /**
     * {@inheritDoc}
     */
    public DoneCommand(String userInput) throws DukeException {
        super(userInput);
        this.index = this.getIndex();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        //TaskList
        Task doneTask = taskList.markAsDone(index);

        //Storage
        storage.save(taskList);

        //Ui
        ui.showDoneTask(doneTask);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }

    private int getIndex() {
        return Integer.parseInt(this.userInputList.get(1)) - 1;
    }
}

