package seedu.duke.commands;

import seedu.duke.storage.Storage;
import seedu.duke.storage.TaskList;
import seedu.duke.timetable.Timetable;

public class TagCommand extends Command {

    @Override
    public String execute(TaskList taskList, Timetable timetable, Storage storage) {
        return null;
    }

    @Override
    public boolean isExit() {
        return false;
    }
    
}
