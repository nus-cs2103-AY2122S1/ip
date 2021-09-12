package seedu.duke.commands;

import seedu.duke.storage.Storage;
import seedu.duke.storage.TaskList;
import seedu.duke.timetable.Timetable;

public class ViewScheduleCommand extends Command {

    @Override
    public String execute(TaskList taskList, Timetable timetable, Storage storage) {
        return timetable.viewAllScheduledTasks();
    }

    @Override
    public boolean isExit() {
        return false;
    }
    
}
