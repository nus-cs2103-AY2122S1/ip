package seedu.duke.storage;

import seedu.duke.tasks.AfterTask;
import seedu.duke.tasks.Deadline;

public class StorageAddDeadline extends StorageAddTask {

    @Override
    public Deadline execute(String currLine, String[] storageDataArray, String storageIsDone) {
        Deadline deadline = new Deadline(getDescriptions(storageDataArray), getDateTimeLocation(storageDataArray),
                getIsDoneFromStorage(storageIsDone));
        if (currLine.contains(" | after")) {
            String afterTaskDescription = currLine.split(" \\| after ")[1];
            deadline.setAfterTask(new AfterTask(afterTaskDescription));
        }
        return deadline;
    }

}
