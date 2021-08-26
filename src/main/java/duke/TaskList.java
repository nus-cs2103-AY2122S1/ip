package duke;

import task.Task;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private final Storage storage;
    private final ArrayList<Task> list;
    private final Ui ui;
    
    public TaskList(Storage storage) {
        this.storage = storage;
        list = storage.load();
        ui = new Ui();
    }

    /**
     * Checks if the given index is a valid task index.
     * 
     * @param index The task index to be checked for validity.
     * @return True if the given index is invalid.
     */
    public boolean isInvalidIndex(int index) {
        return index > list.size() || index < 1;
    }

    /**
     * Instructs the Ui to display the current task list.
     */
    public void showList() {
        ui.showList(list);
    }

    /**
     * Sets the task with the given index to done.
     * 
     * @param index The index of the task to be set to done.
     */
    public void setTaskDone(int index) {
        Task toSetDone = list.get(index);
        storage.setDbEntryDone(toSetDone.databaseEntry());
        toSetDone.setDone();
        ui.showDone(toSetDone);
    }

    /**
     * Deletes the task with the given index.
     * 
     * @param index The index of the task to be deleted.
     */
    public void deleteTask(int index) {
        Task deleted = list.remove(index);
        storage.deleteDbEntry(deleted.databaseEntry());
        ui.showDelete(deleted, list.size());
    }

    /**
     * Instructs the Ui to display a list of tasks that take place on the given date.
     * 
     * @param desiredDate The date used to find tasks.
     */
    public void findTasksByDate(LocalDate desiredDate) {
        ui.showDateFind(list, list.size(), desiredDate);
    }

    /**
     * Instructs the Ui to display a list of tasks that contain the given keyword.
     *
     * @param keyword The keyword to look for in task names.
     */
    public void findTasksByKeyword(String keyword) {
        ui.showKeywordFind(list, list.size(), keyword);
    }

    /**
     * Adds the given task to the task list.
     * 
     * @param newTask The task to be added to the task list.
     */
    public void addTask(Task newTask) {
        list.add(newTask);
        storage.addDbEntry(newTask.databaseEntry());
        ui.showAdd(newTask, list.size());
    }
}
