package duke.command;

import duke.task.Task;
import duke.task.TaskList;

import duke.DukeException;
import duke.Storage;
import duke.Ui;

/**
 * Represents a command to mark task as done
 */
public class DoneCommand extends Command {

    public static final String COMMAND_WORD = "done";
    public static final String USAGE_TEXT = "Usage: done [task number]";

    /** Index of task to mark as done */
    private int index;

    /**
     * DoneCommand constructor.
     *
     * @param index Index of task to mark as done.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Marks task at index in given TaskList as done and
     * displays the relevant message.
     *
     * @param taskList List of tasks.
     * @param ui User interface.
     * @param storage Storage of tasks.
     * @throws DukeException If index is not valid.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = taskList.getTask(index);
            task.setIsDone(true);
            return ui.reply("Noice! Pepper Jack marked this task as done:\n\t" + task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task number does not exist!\n\t" + DoneCommand.USAGE_TEXT);
        }
    }
}
