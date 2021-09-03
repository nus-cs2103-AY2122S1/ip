package tasklist;

import java.io.IOException;
import java.util.ArrayList;

import exception.ErrorAccessingFileException;
import exception.NonExistentTaskNumberException;
import storage.StorageFile;

/**
 * Encapsulates a task list storing the users input tasks.
 */
public class TaskList {
    private ArrayList<Task> list = new ArrayList<>();
    // An active list is the latest list that the user is viewing, it could be filtered by keywords
    private ArrayList<Task> activeList = this.list;
    private StorageFile listFile;

    public TaskList(StorageFile listFile) {
        this.listFile = listFile;
    }

    /**
     * Scans tasks from storage to list.
     *
     * @param task Task to be scanned into list.
     */
    public void scanExistingTaskToList(Task task) {
        this.list.add(task);
    }

    /**
     * Adds task to a list.
     *
     * @param task Task be added to the list
     */
    public void addTaskToList(Task task) throws ErrorAccessingFileException {
        try {
            this.listFile.add(task.toString());
            this.list.add(task);
        } catch (IOException e) {
            throw new ErrorAccessingFileException("add");
        }
    }

    /**
     * Deletes task from a list.
     *
     * @param taskNumber Number of the task to be removed from the list.
     */
    public Task deleteTaskFromList(int taskNumber) throws NonExistentTaskNumberException, ErrorAccessingFileException {
        try {
            validateTaskNumberExists(taskNumber);

            Task task = this.getTaskByTaskNumber(taskNumber);
            this.list.remove(task);
            this.listFile.rewriteFile(this.list);

            return task;
        } catch (IOException e) {
            throw new ErrorAccessingFileException("delete");
        }
    }

    /**
     * Marks task in the list as done, finding the task by its task number.
     *
     * @param taskNumber Task number of the task in the list, starting from 1.
     * @return Task.
     * @throws NonExistentTaskNumberException If the task number does not exist in the list.
     * @throws ErrorAccessingFileException If there is an error accessing the storage file.
     */
    public Task markTaskAsDone(int taskNumber) throws NonExistentTaskNumberException, ErrorAccessingFileException {
        try {
            validateTaskNumberExists(taskNumber);

            Task task = this.getTaskByTaskNumber(taskNumber);
            task.markAsDone();
            this.listFile.rewriteFile(this.list);

            return task;
        } catch (IOException e) {
            throw new ErrorAccessingFileException("mark as done");
        }

    }

    /**
     * Gets a new list of only tasks whose description contains the keyword.
     *
     * @param keyword Keyword to search for.
     * @return `TaskList`.
     */
    public TaskList getListContainingKeyword(String keyword) {
        this.activeList = new ArrayList<>(this.list);
        this.activeList.removeIf(task -> !task.contains(keyword));
        return this;
    }

    /**
     * Gets the full list of tasks.
     *
     * @return `TaskList`.
     */
    public TaskList getFullList() {
        this.activeList = this.list;
        return this;
    }

    /**
     * Formats tasks in a numbered list form, starting from 1.
     *
     * @return Numbered list.
     */
    @Override
    public String toString() {
        StringBuilder stringBuilderList = new StringBuilder();

        for (int i = 0; i < this.activeList.size(); i++) {
            Task task = this.activeList.get(i);
            String listItem = String.format("%d. %s\n", i + 1, task.toString());
            stringBuilderList.append(listItem);
        }

        return stringBuilderList.toString();
    }

    private boolean contains(int taskNumber) {
        return taskNumber > 0 && taskNumber <= this.activeList.size();
    }

    private Task getTaskByTaskNumber(int taskNumber) {
        return this.activeList.get(taskNumber - 1);
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return Number of tasks in the list.
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
