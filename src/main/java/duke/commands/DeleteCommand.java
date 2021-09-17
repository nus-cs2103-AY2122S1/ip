package duke.commands;

import duke.exceptions.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.tasks.Task;

/**
 * Represents command to delete task
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Creates delete command
     * @param userInput userinput
     * @throws DukeException throws if user input is invalid
     */
    public DeleteCommand(String userInput) throws DukeException {
        super(userInput);
        this.index = this.getIndex();
    }

    /**
     * Execute delete command
     * @param taskList The object that holds a list of Task
     * @param ui The object responsible for updating Ui response
     * @param storage The object responsible to save/load list of task to/from hard disk
     * @return string to be outputed to users
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        //TaskList
        Task deletedTask = taskList.deleteTask(this.index);

        //Storage
        storage.save(taskList);

        //Ui
        String output = "";
        output += ui.showDeleteTask(deletedTask) + "\n";
        output += ui.showNumTask(taskList.getNumTask());
        return output;
    }

    /**
     * Check if command is exit command
     * @return true if exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }

    private int getIndex() {
        return Integer.parseInt(this.userInputList.get(1)) - 1;
    }
}

