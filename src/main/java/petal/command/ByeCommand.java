package petal.command;

import petal.components.Storage;
import petal.components.TaskList;
import petal.components.Ui;

import java.io.IOException;

/**
 * ByeCommand class that implements Command and handles
 * the termination of the Petal instance.
 */
public class ByeCommand implements Command {

    /**
     * Overwritten Execute method. It terminates the Petal instance.
     *
     * @param taskList TaskList instance
     * @param ui Ui instance
     * @param storage Storage instance
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            storage.saveTasks();
        } catch (IOException e) {
            ui.output("Sorry, the task couldn't be saved :/");
        } finally {
            ui.goodBye();
        }
    }

}
