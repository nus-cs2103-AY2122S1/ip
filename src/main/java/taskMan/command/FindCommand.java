package taskMan.command;


import taskMan.exception.DukeException;
import taskMan.util.Storage;
import taskMan.util.TaskList;

/**
 * Command that looks through tasklist to find task that contains a keyword
 */
public class FindCommand extends Command {

    private final String key;
    private final String successFindMessage = "Here are the tasks with the keyword : ";

    /**
     * Basic constructor of find command instance
     *
     * @param storage
     * @param taskList
     * @param key
     */
    public FindCommand(Storage storage, TaskList taskList, String key) {
        super(storage, taskList, false);
        this.key = key;
    }

    /**
     * Executes a set of instructions to find a task based on keyword
     *
     * @return String message
     * @throws DukeException
     */
    @Override
    public String exec() throws DukeException {

        String keysFound = taskList.find(key);

        if (keysFound.equals("")){
            return "Sorry, there is no task with keyword: " + key ;
        }
        return successFindMessage + key +"\n" + keysFound;
    }
}
