package eightbit.command;

import java.util.List;

import eightbit.EightBitException;
import eightbit.task.Task;
import eightbit.util.Storage;
import eightbit.util.TaskList;

/**
 * Represents a command to add tags to a task.
 */
public class TagCommand extends Command {

    private final int index;
    private final List<String> tags;

    /**
     * Constructs a command to add tags to a task.
     *
     * @param index Position of task to be tagged.
     * @param tags  List of tags to be added.
     */
    public TagCommand(int index, List<String> tags) {
        this.index = index;
        this.tags = tags;
    }

    /**
     * Adds tags to the task.
     *
     * @param taskList User's list of tasks.
     * @param storage  Storage responsible for reading/writing the file.
     * @return The response after executing the command.
     * @throws EightBitException If the given index exceeds the total number of tasks.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws EightBitException {
        if (index >= taskList.size()) {
            throw new EightBitException("OOPS!!! Task " + (index + 1) + " does not exist.");
        }

        Task task = taskList.get(index);
        task.addTags(tags);
        storage.rewriteFileWithTasks(taskList);
        return "Got it. I've added the tags.\n" + task;
    }
}
