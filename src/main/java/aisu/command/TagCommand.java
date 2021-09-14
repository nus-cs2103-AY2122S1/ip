package aisu.command;

import aisu.exception.AisuException;
import aisu.storage.Storage;
import aisu.task.Task;
import aisu.tasklist.TaskList;
import aisu.ui.Ui;

/**
 * Command to tag a task with a personalised tag name.
 *
 * @author Liaw Xin Yan
 */
public class TagCommand extends Command {
    private final int parseInt;
    private final String tagName;

    /**
     * Constructor to initialise the Tag command.
     * @param parseInt The index of the task to be added.
     * @param tagName The name of the tag to be added.
     */
    public TagCommand(int parseInt, String tagName) {
        this.parseInt = parseInt;
        this.tagName = tagName;
    }

    /**
     * Tags a task in the tasklist with a tag name.
     * @param tasklist TaskList used in Aisu.
     * @param storage Storage used in Aisu.
     * @param ui User interface used in Aisu.
     * @throws AisuException If command fails to be executed.
     */
    @Override
    public void execute(TaskList tasklist, Storage storage, Ui ui) throws AisuException {
        Task taggedTask = tasklist.tagTask(this.parseInt, this.tagName);
        storage.save(tasklist);
        this.uiText = Ui.formatText(" Noted~ I've added the tag " + this.tagName + " to this task:", " - "
                + taggedTask);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
