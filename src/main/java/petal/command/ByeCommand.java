package petal.command;

import java.io.IOException;

import petal.components.Responses;
import petal.components.Storage;
import petal.components.TaskList;

/**
 * The ByeCommand class implements Command
 * and handles the termination of the Petal bot
 */
public class ByeCommand implements Command {

    /**
     * Terminates the running Petal instance
     *
     * @param taskList TaskList instance
     * @param storage Storage instance
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        try {
            storage.saveTasks();
            return Responses.GOODBYE.toString();
        } catch (IOException e) {
            return Responses.GOODBYE.toString();
        }
    }

}
