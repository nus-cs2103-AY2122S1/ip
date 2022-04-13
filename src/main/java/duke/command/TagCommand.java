package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.List;

public class TagCommand extends Command {
    private int index;
    private ArrayList<String> tags;

    /**
     * Constructor for a tag command
     *
     * @param index index to be tagged
     * @param tags tags to be added
     */
    public TagCommand(int index, String ...tags) {
        this.index = index;
        this.tags = new ArrayList<>(List.of(tags));
        this.tags.remove(0);
    }

    /**
     * Adds the tags to the task.
     *
     * @param tasks task list
     * @param storage storage
     * @param ui ui
     * @return output
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        String output = tasks.addTags(index, tags);
        String saveFileString = tasks.save();
        storage.save(saveFileString);
        return output;
    }

}
