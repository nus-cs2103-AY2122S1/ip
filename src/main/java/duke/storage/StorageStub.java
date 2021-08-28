package duke.storage;

import java.util.ArrayList;

import duke.task.Task;

/**
 * StorageStub is in-charge of loading the saved taskList, and updating it (for testing).
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S1
 */
public class StorageStub implements Storage {

    private final ArrayList<String> fileContent = new ArrayList<>();

    /**
     * Gives an empty ArrayList for testing.
     *
     * @return empty ArrayList for testing
     */
    @Override
    public ArrayList<Task> load() {
        return new ArrayList<>();
    }

    /**
     * Marks the task with the given index as done.
     *
     * @param index the position of the item
     */
    @Override
    public void setDone(int index) {
        String str = fileContent.get(index);
        fileContent.set(index, str.replace("|0|", "|1|"));
    }

    /**
     * Adds the task with the given type, description and date.
     *
     * @param type        the type of the task
     * @param description the description of the task
     * @param date        the date of the task (if deadline or event)
     */
    @Override
    public void add(String type, String description, String date) {
        fileContent.add(type + "|0|" + description + "|" + date);
    }

    /**
     * Deletes the task with the given index.
     *
     * @param index the position of the task in the list
     */
    @Override
    public void delete(int index) {
        fileContent.remove(index);
    }

    /**
     * Returns the string representation of the task at the given index for testing.
     *
     * @param index the position of the task in the list
     * @return the string representation of the task at the given index
     */
    public String getString(int index) {
        return fileContent.get(index);
    }
}
