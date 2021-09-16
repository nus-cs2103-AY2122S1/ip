package duke;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Encapsulates a command by the user to tag a task in Duke's to-do-list.
 * The tag will be overwritten each time the user writes a new tag.
 */
public class TagCommand extends Command {
    private int index;
    private String tag;

    /**
     * Constructor for a tag command.
     *
     * @param description The description of the tag command,
     *                    which is in the format of "(index) (tag)",
     *                    where "index" is the index of the task to tag,
     *                    and "tag" is the tag to attach to the task.
     */
    public TagCommand(String description) throws DukeException {
        super();
        try {
            this.index = Integer.parseInt(description.split(" ")[0]);
            this.tag = description.split(" ")[1];
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("OOPS!!! Please enter a tag in the format \"tag (index) (tag)\"");
        }
    }

    /**
     * Executes the tag command.
     *
     * @param tasks The list of tasks in the to-do-list.
     * @param ui The user interface that deals with interactions with the user.
     * @param storage The storage that Duke uses to deal with loading tasks from and saving tasks to a file.
     * @return A message describing the result of the execution.
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        // Tag task, and persist to text file
        Task t = tasks.tag(this.index, this.tag);
        try {
            storage.save(tasks);
        } catch (IOException e) {
            ui.showSavingError(e);
        }

        // Return a description of the execution result
        return "Got it. I've tagged this task: \n"
                + t;
    }
}
