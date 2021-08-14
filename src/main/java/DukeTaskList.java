import java.util.ArrayList;

/**
 * Encapsulates a task list storing the users input tasks.
 */
public class DukeTaskList {
    private ArrayList<DukeTask> list = new ArrayList<>();

    /**
     * Adds task to a list.
     *
     * @param task is the task be added to the list
     */
    public void addTaskToList(DukeTask task) {
        this.list.add(task);
    }

    /**
     * Delete task from a list.
     *
     * @param taskNumber is the number of the task to be removed from the list
     */
    public void deleteTaskFromList(int taskNumber) throws NonExistentTaskNumberException {
        validateTaskNumberExists(taskNumber);
        this.list.remove(taskNumber - 1);
    }

    /**
     * Formats tasks in a numbered list form, starting from 1.
     *
     * @return the numbered list
     */
    public String getList() {
        StringBuilder stringBuilderList = new StringBuilder();

        for (int i = 0; i < this.list.size(); i++) {
            DukeTask task = this.list.get(i);
            String listItem = String.format("%d. %s\n\t", i + 1, task.toString());
            stringBuilderList.append(listItem);
        }

        return stringBuilderList.toString();
    }

    /**
     * Takes in a task number and returns true if the task number exists in the list,
     * otherwise it returns false.
     *
     * @param taskNumber is the number that the task is listed by, starting from 1
     * @return true if the task number exists in the list, otherwise false
     */
    private boolean contains(int taskNumber) {
        return taskNumber > 0 && taskNumber <= this.list.size();
    }

    /**
     * Retrieves the task by the number it is listed by.
     *
     * @param taskNumber is the number that the task is listed by, starting from 1
     * @return a `DukeTask`
     */
    public DukeTask getTaskByTaskNumber(int taskNumber) throws NonExistentTaskNumberException {
        validateTaskNumberExists(taskNumber);
        return this.list.get(taskNumber - 1);
    }

    /**
     * Gets the number of tasks in the list
     *
     * @return the number of tasks in the list
     */
    public int getNumberOfTasks() {
        return this.list.size();
    }

    private void validateTaskNumberExists(int taskNumber) throws NonExistentTaskNumberException {
        if (!this.contains(taskNumber)) {
            throw new NonExistentTaskNumberException(taskNumber);
        }
    }
}
