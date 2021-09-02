package duke.command;


import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * Command that looks through tasklist to find task that contains a keyword
 */
public class FindCommand extends Command {

    private final String key;

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
     * Executes a set of instructions
     *
     * @return boolean To relay whether to continue the project
     * @throws DukeException
     */
    @Override
    public String exec() throws DukeException {

        String keysFound = taskList.find(key);

        if (keysFound.equals("")){
            return "None Found";
        }
        return keysFound;
    }
}
