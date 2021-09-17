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
     * Creates done command
     * @param userInput userinput
     * @throws DukeException throws if userinput is invalid
     */
    public DoneCommand(String userInput) throws DukeException {
        super(userInput);
        this.index = this.getIndex();
    }

    /**
     * Execute done command
     * @param taskList The object that holds a list of Task
     * @param ui The object responsible for updating Ui response
     * @param storage The object responsible to save/load list of task to/from hard disk
     * @return string to be outputed to users
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
     * Check if this is exit command
     * @return return true if is exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }

    private int getIndex() {
        return Integer.parseInt(this.userInputList.get(1)) - 1;
    }
}

