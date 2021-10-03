package seedu.duke.storage;

import seedu.duke.tasks.PeriodTask;

public class StorageAddPeriodTask extends StorageAddTask {

    @Override
    public PeriodTask execute(String currLine, String[] storageDataArray, String storageIsDone) {
        PeriodTask periodTask = new PeriodTask(getDescriptions(storageDataArray), periodTaskGetFrom(storageDataArray),
                periodTaskGetTo(storageDataArray));
        periodTask = (PeriodTask) checkAfterTask(currLine, periodTask);

        return periodTask;
    }

}
