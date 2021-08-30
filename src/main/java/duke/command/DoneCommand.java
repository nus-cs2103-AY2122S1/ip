package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * The DoneCommand is given when a Task needs to be marked as done.
 */
public class DoneCommand extends Command {

    public static final String COMMAND_WORD = "done";
    private int taskNum;

    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Marks the specified Task corresponding to the given index in the TaskList as done.
     * Also saves the updated TaskList to taskList.txt
     *
     * @param tasks the given TaskList.
     * @param ui the given Ui.
     * @param storage the given Storage.
     * @return the string for the Ui to print.
     * @throws DukeException when the index given is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskNum >= tasks.numTasks()) {
            throw new DukeException("you typed an invalid number: " + (taskNum + 1));
        }
        tasks.markTask(taskNum);
        storage.save(tasks);
        return "Nice! I've marked this task as done:\n  " + tasks.getTask(taskNum);
    }
}
