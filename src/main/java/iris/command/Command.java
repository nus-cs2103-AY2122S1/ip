package iris.command;

import iris.IrisException;
import iris.Storage;
import iris.TaskList;

public abstract class Command {
    public void store(TaskList tasks, Storage storage) throws IrisException {

    }

    public abstract String run(TaskList tasks) throws IrisException;

    /**
     * Run the command and store the tasks in the data file if tasks have changed
     * @param tasks   TaskList representing list of tasks
     * @param storage Storage object encapsulating storage functionality
     * @return        String representing Iris's response to the user
     * @throws IrisException for invalid commands
     */
    public String runAndStore(TaskList tasks, Storage storage) throws IrisException {
        String response = run(tasks);
        store(tasks, storage);
        return response;
    };
}
