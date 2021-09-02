package duke.command;


import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;


public class FindCommand extends Command {

    private final String key;

    public FindCommand(Storage storage, TaskList taskList, String key) {
        super(storage, taskList, false);
        this.key = key;
    }

    @Override
    public String exec() throws DukeException {

        String keysFound = taskList.find(key);

        if (keysFound.equals("")){
            return "None Found";
        }
        return keysFound;
    }
}
