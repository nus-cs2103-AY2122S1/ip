package seedu.duke.storage;

import java.util.ArrayList;

import seedu.duke.tasks.Deadline;

public class StorageAddDeadline extends StorageAddTask {

    @Override
    public Deadline execute(String currLine, String[] storageDataArray, String storageIsDone) {
        ArrayList<String> tags = addTags(currLine);
        Deadline deadline = new Deadline(getDescriptions(storageDataArray), getDateTimeLocation(storageDataArray),
                getIsDoneFromStorage(storageIsDone), tags);
        deadline = (Deadline) checkAfterTask(currLine, deadline);
        return deadline;
    }
}
