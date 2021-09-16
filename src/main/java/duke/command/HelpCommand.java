package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;


/**
 * Command that contains details when user inputs Bye
 */
public class HelpCommand extends Command{
    private final String divider = "----------------------------------------------\n";
    private final String helpMessage = "Here is a quick guide on the Command line Commands: \n"
            + divider + "Viewing help : help \n"
            + divider + "List all task : list \n"
            + divider + "Add task: todo [task details] \n"
            + divider + "Add deadline : deadline [details] /by [YYYY-MM-DD] [24-hour] \n"
            + divider + "Adding a event : event [task details] /at [YYYY-MM-DD] [24-hour] \n"
            + divider + "Completing a task: done [task list position]\n"
            + divider + "Search task by keyword: find [keyword] \n"
            + divider + "Deleting a person : delete [task list position] \n"
            + divider + "Exiting the program : bye";

    /**
     * Basic Constructor
     *
     * @param storage Storage object to save
     * @param taskList Tasklist to add task to
     */
    public HelpCommand(Storage storage, TaskList taskList){
        super(storage, taskList, false);
    }

    /**
     * Executes a set of instructions to get a guide on to how to use the application
     *
     * @return String help message
     * @throws DukeException
     */
    @Override
    public String exec() throws DukeException {
        return helpMessage;
    }
}
