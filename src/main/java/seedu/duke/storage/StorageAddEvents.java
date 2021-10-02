package seedu.duke.storage;

import java.util.ArrayList;

import seedu.duke.tasks.AfterTask;
import seedu.duke.tasks.Events;

public class StorageAddEvents extends StorageAddTask {

    @Override
    public Events execute(String currLine, String[] storageDataArray, String storageIsDone) {
        ArrayList<String> tags = addTags(currLine);
        Events event = new Events(getDescriptions(storageDataArray), getDateTimeLocation(storageDataArray),
                getIsDoneFromStorage(storageIsDone), tags);
        if (currLine.contains(" | after")) {
            String afterTaskDescription = currLine.split(" \\| after ")[1];
            event.setAfterTask(new AfterTask(afterTaskDescription));
        }
        return event;
    }

}
