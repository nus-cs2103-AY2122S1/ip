package seedu.duke.commands;

import seedu.duke.storage.Storage;
import seedu.duke.storage.TaskList;
import seedu.duke.timetable.Timetable;

public class DeleteTagCommand extends Command {
    private final String taskId;

    public DeleteTagCommand(String taskId) {
        this.taskId = taskId;
    }

    @Override
    public String execute(TaskList taskList, Timetable timetable, Storage storage) {
        int index = Integer.parseInt(this.taskId) - 1;
        taskList.deleteTags(index);
        storage.deleteTags(index);
        return "Tags deleted";
    }

    @Override
    public boolean isExit() {
        return false;
    }
    
}
