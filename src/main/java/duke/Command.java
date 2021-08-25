package duke;

/**
 * Represents a function that will be called in the duke.Duke running loop.
 */
public class Command {
    private TriConsumer func;
    private boolean isExit;

    /**
     * @param func function that has to be called with taskList, ui, storage
     * @param isExit true iff func is an exit function
     */
    public Command(TriConsumer func, boolean isExit) {
        this.func = func;
        this.isExit = isExit;
    }

    /**
     * runs the function stored.
     * @param taskList contains the task list
     * @param ui handles interactions with the user
     * @param storage handles loading tasks from the file and saving tasks in the file
     * @throws DukeException
     * @throws Exception
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, Exception {
        func.execute(taskList, ui, storage);
    }
    public boolean isExit() {
        return isExit;
    }
}
