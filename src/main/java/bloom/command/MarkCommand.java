package bloom.command;

import bloom.app.TaskList;
import bloom.constant.Message;
import bloom.task.Task;

/**
 * Represents a mark command which
 * marks a specified task as done.
 */

public class MarkCommand extends Command {

    /** The index of the task to be marked as done. */
    private final int index;

    /**
     * Constructor for a MarkCommand.
     *
     * @param index the index of the task to be marked as done
     */

    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the specified task as done.
     */

    @Override
    public void run() {
        Task task = TaskList.get(this.index);
        task.markAsDone();
        System.out.println(Message.COMMAND_MARK.getMessage());
        System.out.println("\t   " + task);
    }

    public void run(int ... indexes) {
        for (int i : indexes){
            new MarkCommand(i).run();
        }
    }
}
