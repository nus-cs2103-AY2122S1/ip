package seedu.duke.storage;

import java.util.ArrayList;

import seedu.duke.tasks.AfterTask;
import seedu.duke.tasks.Deadline;

public class StorageAddDeadline extends StorageAddTask {

    @Override
    public Deadline execute(String currLine, String[] storageDataArray, String storageIsDone) {
        ArrayList<String> tags = addTags(currLine);
        Deadline deadline = new Deadline(getDescriptions(storageDataArray), getDateTimeLocation(storageDataArray),
                getIsDoneFromStorage(storageIsDone), tags);
        if (currLine.contains(" | after")) {
            String afterTaskDescription = currLine.split(" \\| after ")[1];
            deadline.setAfterTask(new AfterTask(afterTaskDescription));
        }
        return deadline;
    }
}
