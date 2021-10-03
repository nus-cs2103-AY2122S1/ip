package seedu.duke.storage;

import java.util.ArrayList;

import seedu.duke.tasks.Events;

public class StorageAddEvents extends StorageAddTask {

    @Override
    public Events execute(String currLine, String[] storageDataArray, String storageIsDone) {
        ArrayList<String> tags = addTags(currLine);
        Events event = new Events(getDescriptions(storageDataArray), getDateTimeLocation(storageDataArray),
                getIsDoneFromStorage(storageIsDone), tags);
        event = (Events) checkAfterTask(currLine, event);
        return event;
    }

}
