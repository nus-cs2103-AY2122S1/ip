package seedu.duke.storage;

import java.util.ArrayList;

import seedu.duke.tasks.AfterTask;

import seedu.duke.tasks.TimedTask;

public class StorageAddTimedTask extends StorageAddTask {

    @Override
    public TimedTask execute(String currLine, String[] storageDataArray, String storageIsDone) {
        ArrayList<String> tags = addTags(currLine);
        TimedTask timedTask = new TimedTask(getDescriptions(storageDataArray), getDateTimeLocation(storageDataArray),
                getIsDoneFromStorage(storageIsDone), tags);
        if (currLine.contains(" | after")) {
            String afterTaskDescription = currLine.split(" \\| after ")[1];
            timedTask.setAfterTask(new AfterTask(afterTaskDescription));
        }
        return timedTask;
    }

}
