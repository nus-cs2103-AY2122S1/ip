package petal.command;

import java.io.IOException;

import petal.components.Storage;
import petal.components.TaskList;
import petal.components.Ui;

/**
 * The ByeCommand class implements Command
 * and handles the termination of the Petal bot
 */
public class ByeCommand implements Command {

    /**
     * Terminates the running Petal instance
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
            ui.sayGoodbye();
        }
    }

}
