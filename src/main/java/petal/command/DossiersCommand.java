package petal.command;

import petal.components.Storage;
import petal.components.TaskList;

/**
 * The DossiersCommand class implements Command
 * and handles the user input to view the archived tasks
 */
public class DossiersCommand implements Command {


    @Override
    public String execute(TaskList taskList, Storage storage) {
        return taskList.printArchive();
    }
}
