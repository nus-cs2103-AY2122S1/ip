package seedu.duke.storage;

import seedu.duke.tasks.ScheduledTask;

public class StorageAddScheduledTask extends StorageAddTask {

    @Override
    public ScheduledTask execute(String currLine, String[] storageDataArray, String storageIsDone) {
        ScheduledTask scheduledTask = new ScheduledTask(getDescriptions(storageDataArray),
                scheduledTaskGetDate(storageDataArray), scheduledTaskGetFrom(storageDataArray),
                scheduledTaskGetTo(storageDataArray));
        scheduledTask = (ScheduledTask) checkAfterTask(currLine, scheduledTask);

        return scheduledTask;
    }

}
