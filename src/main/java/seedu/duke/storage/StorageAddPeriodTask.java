package seedu.duke.storage;

import java.util.ArrayList;

import seedu.duke.tasks.PeriodTask;

public class StorageAddPeriodTask extends StorageAddTask {

    @Override
    public PeriodTask execute(String currLine, String[] storageDataArray, String storageIsDone) {
        ArrayList<String> tags = addTags(currLine);
        PeriodTask periodTask = new PeriodTask(getDescriptions(storageDataArray), periodTaskGetFrom(storageDataArray),
                periodTaskGetTo(storageDataArray), tags);
        periodTask = (PeriodTask) checkAfterTask(currLine, periodTask);

        return periodTask;
    }

}
