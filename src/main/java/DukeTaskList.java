/**
 * Encapsulates a task list storing the users input tasks.
 */
public class DukeTaskList {
    private int maxCapacity = 100;
    private DukeTask[] list = new DukeTask[maxCapacity];
    private int indexOfNextNewItem = 0;

    /**
     * Adds tasks to a list that can take up to 100 items.
     *
     * @param task is the task be added to the list
     */
    public void addTaskToList(DukeTask task) throws FullTaskListException {
        if (this.indexOfNextNewItem == this.maxCapacity) {
            throw new FullTaskListException(this.maxCapacity);
        }

        this.list[this.indexOfNextNewItem] = task;
        this.indexOfNextNewItem += 1;
    }

    /**
     * Formats tasks in a numbered list form, starting from 1.
     *
     * @return the numbered list
     */
    public String getList() {
        StringBuilder stringBuilderList = new StringBuilder();

        for (int i = 0; i < this.indexOfNextNewItem; i++) {
            DukeTask task = this.list[i];
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
        return taskNumber > 0 && taskNumber <= this.indexOfNextNewItem;
    }

    /**
     * Retrieves the task by the number it is listed by.
     *
     * @param taskNumber is the number that the task is listed by, starting from 1
     * @return a `DukeTask`
     */
    public DukeTask getTaskByTaskNumber(int taskNumber) throws NonExistentTaskNumberException {
        if (!this.contains(taskNumber)) {
            throw new NonExistentTaskNumberException(taskNumber);
        }

        return this.list[taskNumber - 1];
    }

    /**
     * Gets the number of tasks in the list
     *
     * @return the number of tasks in the list
     */
    public int getNumberOfTasks() {
        return this.indexOfNextNewItem;
    }
}
