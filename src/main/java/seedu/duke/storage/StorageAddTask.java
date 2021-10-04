package seedu.duke.storage;

import java.util.ArrayList;

import seedu.duke.commands.Ui;
import seedu.duke.exceptions.action.DukeActionOutOfBoundException;
import seedu.duke.tasks.AfterTask;
import seedu.duke.tasks.Task;

public abstract class StorageAddTask {
    /**
     * Executes the function, allows different type of {@code Tasks} to be added
     * respectively.
     * 
     * @param currLine         is the current line which this is reading from
     *                         {@code Storage}.
     * @param storageDataArray is the array of {@code String} which contains
     *                         information about the {@code Task}.
     * @param storageIsDone    is a boolean which tells us if the {@code Task} is
     *                         completed.
     * @return a {@code String} which will be shown in the UI.
     */
    public abstract Task execute(String currLine, String[] storageDataArray, String storageIsDone);

    /**
     * Checks if the pointed {@code Task} is completed or not.
     * 
     * @param storageIsDone is the sign given from the {@code Storage}. 1 is done
     *                      and 0 is not.
     * @return a boolean if the {@code Task} is completed or not.
     */
    protected boolean getIsDoneFromStorage(String storageIsDone) {
        assert !storageIsDone.equals(null) : "Storage isDone value should not be null";
        if (storageIsDone.contains("1")) {
            return true;
        }
        return false;
    }

    /**
     * Gets the description of the {@code Task} from {@code Storage} line.
     * 
     * @param storageDataArray is the array of {@code String} which contains
     *                         information about the {@code Task}.
     * @return the descriptions of the {@code Task}.
     */
    protected String getDescriptions(String[] storageDataArray) {
        assert storageDataArray.length > 1 : "storageDataArray length should be greater than 1";
        try {
            return storageDataArray[2];
        } catch (ArrayIndexOutOfBoundsException err) {
            throw new DukeActionOutOfBoundException(Ui.ERROR_MESSAGE_STORAGE_LOAD_OUT_OF_BOUND);
        }
    }

    /**
     * Gets the date, time and location from the given description array.
     * 
     * @param storageDataArray is the array of {@code String} which contains
     *                         information about the {@code Task}.
     * @return the date, time and location of the {@code Task}.
     */
    protected String getDateTimeLocation(String[] storageDataArray) {
        assert storageDataArray.length > 2 : "storageDataArray length should be greater than 2";
        try {
            return storageDataArray[3];
        } catch (ArrayIndexOutOfBoundsException err) {
            throw new DukeActionOutOfBoundException(Ui.ERROR_MESSAGE_STORAGE_LOAD_OUT_OF_BOUND);
        }
    }

    private boolean hasTags(String currLine) {
        return currLine.contains(" #tags ");
    }

    /**
     * Adds tags into an {@code ArrayList<String>} which will be used to be added to
     * the respective {@code Task}.
     * 
     * @param currLine is the current {@code Task}.
     * @return an {@code ArrayList<String>} containing all the taggings in it.
     */
    protected ArrayList<String> addTags(String currLine) {
        ArrayList<String> tags = new ArrayList<String>();
        if (this.hasTags(currLine)) {
            String[] tagList = (currLine.split(" \\| #tags ")[1]).split(" ");
            for (int i = 0; i < tagList.length; i++) {
                tags.add(tagList[i]);
            }
        }
        return tags;
    }

    /**
     * Checks if {@code AfterTask} is needed to be added. Add if needed.
     * 
     * @param currLine is the information for the current {@code Task}.
     * @param task     is the current {@code Task}.
     * @return a {@code Task} which have a proper {@code AfterTask} if need to be
     *         added.
     */
    protected Task checkAfterTask(String currLine, Task task) {
        Task taskCopy = task;
        if (currLine.contains(" | after") && currLine.contains(" | #tags")) {
            String afterTaskDescription = currLine.split(" \\| after ")[1].split(" \\| #tags")[0];
            taskCopy.setAfterTask(new AfterTask(afterTaskDescription));
        } else if (currLine.contains(" | after")) {
            String afterTaskDescription = currLine.split(" \\| after ")[1];
            taskCopy.setAfterTask(new AfterTask(afterTaskDescription));
        }
        return taskCopy;
    }
}
