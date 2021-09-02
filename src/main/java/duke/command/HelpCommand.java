package duke.command;

import duke.exception.DukeException;

import duke.util.Storage;
import duke.util.TaskList;


/**
 * Command that contains details when user inputs Bye
 */
public class HelpCommand extends Command{
    /**
     * Basic Constructor
     *
     * @param storage Storage object to save
     * @param taskList Tasklist to add task to
     */
    public HelpCommand(Storage storage, TaskList taskList){
        super(storage, taskList, true);
    }

    @Override
    public String exec() throws DukeException {

        String help = "Viewing help : help \n" +
                      "Listing all task : list \n" +
                      "Adding a task: todo [task details] \n" +
                      "Completing a task: done [listNo]\n" +
                      "Search task by keyword: find [keyword] \n" +
                      "Deleting a person : delete  [listNo] \n" +
                      "Exiting the program : bye";

        return help;
    }
}
