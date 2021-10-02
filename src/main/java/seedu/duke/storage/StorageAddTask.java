package seedu.duke.storage;

import java.util.ArrayList;

import seedu.duke.commands.Ui;
import seedu.duke.exceptions.action.DukeActionOutOfBoundException;
import seedu.duke.tasks.Task;

public abstract class StorageAddTask {
    public abstract Task execute(String currLine, String[] storageDataArray, String storageIsDone);

    protected boolean getIsDoneFromStorage(String storageIsDone) {
        assert !storageIsDone.equals(null) : "Storage isDone value should not be null";
        if (storageIsDone.contains("1")) {
            return true;
        }
        return false;
    }

    protected String getDescriptions(String[] storageDataArray) {
        assert storageDataArray.length > 1 : "storageDataArray length should be greater than 1";
        try {
            return storageDataArray[2];
        } catch (ArrayIndexOutOfBoundsException err) {
            throw new DukeActionOutOfBoundException(Ui.ERROR_MESSAGE_STORAGE_LOAD_OUT_OF_BOUND);
        }
    }

    protected String getDateTimeLocation(String[] storageDataArray) {
        assert storageDataArray.length > 2 : "storageDataArray length should be greater than 2";
        try {
            return storageDataArray[3];
        } catch (ArrayIndexOutOfBoundsException err) {
            throw new DukeActionOutOfBoundException(Ui.ERROR_MESSAGE_STORAGE_LOAD_OUT_OF_BOUND);
        }
    }

    protected String periodTaskGetFrom(String[] storageDataArray) {
        String periodDate = storageDataArray[3];
        return periodDate.split(" and ")[0];
    }

    protected String periodTaskGetTo(String[] storageDataArray) {
        String periodDate = storageDataArray[3];
        return periodDate.split(" and ")[1];
    }

    protected String scheduledTaskGetDate(String[] storageDataArray) {
        String dateTime = storageDataArray[3];
        return dateTime.split(" ")[0];
    }

    protected int scheduledTaskGetFrom(String[] storageDataArray) {
        String dateTime = storageDataArray[3];
        return Integer.parseInt(dateTime.split(" ")[2]);
    }

    protected int scheduledTaskGetTo(String[] storageDataArray) {
        String dateTime = storageDataArray[3];
        return Integer.parseInt(dateTime.split(" ")[4]);
    }

    protected boolean hasTags(String currLine) {
        return currLine.contains("|#tags");
    }

    protected ArrayList<String> addTags(String currLine) {
        ArrayList<String> tags = new ArrayList<String>();
        if (this.hasTags(currLine)) {
            String[] tagList = currLine.split(" \\|#tags ")[1].split(" ");
            for (int i = 0; i < tagList.length; i++) {
                tags.add(tagList[i]);
            }
        }
        return tags;
    }
}
