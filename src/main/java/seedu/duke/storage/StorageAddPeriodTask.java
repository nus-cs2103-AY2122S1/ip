package seedu.duke.storage;

import seedu.duke.tasks.AfterTask;
import seedu.duke.tasks.PeriodTask;

public class StorageAddPeriodTask extends StorageAddTask {

    @Override
    public PeriodTask execute(String currLine, String[] storageDataArray, String storageIsDone) {
        PeriodTask periodTask = new PeriodTask(getDescriptions(storageDataArray), periodTaskGetFrom(storageDataArray),
                periodTaskGetTo(storageDataArray));
        if (currLine.contains(" | after")) {
            String afterTaskDescription = currLine.split(" \\| after ")[1];
            periodTask.setAfterTask(new AfterTask(afterTaskDescription));
        }
        return periodTask;
    }

}
