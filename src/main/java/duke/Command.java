package duke;

/**
 * Represents a function that will be called in the duke.Duke running loop.
 */
public class Command {
    private QuadConsumer func;
    private boolean isExit;

    /**
     * @param func function that has to be called with taskList, ui, storage, history.
     * @param isExit true iff func is an exit function.
     */
    public Command(QuadConsumer func, boolean isExit) {
        this.func = func;
        this.isExit = isExit;
    }

    /**
     * Constructs a command that does not exit.
     *
     * @param func function that has to be called with taskList, ui, storage, history.
     */
    public Command(QuadConsumer func) {
        this(func, false);
    }


    /**
     * runs the function stored.
     *
     * @param taskList contains the task list.
     * @param ui handles interactions with the user.
     * @param storage handles loading tasks from the file and saving tasks in the file.
     * @param history handles the history of taskList.
     * @throws DukeException If exceute throws DukeException.
     * @throws Exception If exceute throws Exception.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage, HistoryManager history)
            throws DukeException, Exception {
        func.execute(taskList, ui, storage, history);
    }
    public boolean isExit() {
        return isExit;
    }
}
