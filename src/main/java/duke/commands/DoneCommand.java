package duke.commands;

import duke.exceptions.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.tasks.Task;

/**
 * Represents command to mark task as done
 */
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
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        //TaskList
        Task doneTask = taskList.markAsDone(index);

        //Storage
        storage.save(taskList);

        //Ui
        String output = ui.showDoneTask(doneTask) + "\n";
        output += "You have done " + taskList.getNumTaskDone() + " tasks so far. Good job!!!";
        return output;
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

