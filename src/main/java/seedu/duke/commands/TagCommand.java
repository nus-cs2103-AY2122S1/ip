package seedu.duke.commands;

import seedu.duke.storage.Storage;
import seedu.duke.storage.TaskList;
import seedu.duke.timetable.Timetable;

public class TagCommand extends Command {
    private final String taskId;
    private final String tags;

    public TagCommand(String taskId, String tags) {
        this.taskId = taskId;
        this.tags = tags;
    }

    @Override
    public String execute(TaskList taskList, Timetable timetable, Storage storage) {
        int index = Integer.parseInt(this.taskId) - 1;
        taskList.addTags(index, this.tags);
        storage.updateTags(index, tags);
        return "Tags added";
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
