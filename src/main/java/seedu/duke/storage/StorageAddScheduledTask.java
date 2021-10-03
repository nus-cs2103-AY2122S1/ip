package seedu.duke.storage;

import java.util.ArrayList;

import seedu.duke.tasks.ScheduledTask;

public class StorageAddScheduledTask extends StorageAddTask {

    @Override
    public ScheduledTask execute(String currLine, String[] storageDataArray, String storageIsDone) {
        ArrayList<String> tags = addTags(currLine);
        ScheduledTask scheduledTask = new ScheduledTask(getDescriptions(storageDataArray),
                scheduledTaskGetDate(storageDataArray), scheduledTaskGetFrom(storageDataArray),
                scheduledTaskGetTo(storageDataArray), tags);
        scheduledTask = (ScheduledTask) checkAfterTask(currLine, scheduledTask);

        return scheduledTask;
    }

}
