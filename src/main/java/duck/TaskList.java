package duck;

import duck.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Contains the task list and handles logic related to the task list, eg. adding, deleting, setting tasks to done.
 */
public class TaskList {
    private final Storage storage;
    private final ArrayList<Task> list;
    private final Ui ui;

    /**
     * Constructor for a task list.
     *
     * @param storage Storage object used to store the task list.
     */
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
     *
     * @return String representing the displayed list.
     */
    public String showList() {
        return ui.displayList(list);
    }

    /**
     * Sets the task with the given index to done.
     *
     * @param index The index of the task to be set to done.
     * @return String representing the setting of the task to done.
     */
    public String setTaskDone(int index) {
        Task toSetDone = list.get(index);
        storage.setDbEntryDone(toSetDone.databaseEntry());
        toSetDone.setDone();
        return ui.showDone(toSetDone);
    }

    /**
     * Deletes the task with the given index.
     *
     * @param index The index of the task to be deleted.
     * @return String representing the task being deleted.
     */
    public String deleteTask(int index) {
        int initialSize = list.size();
        Task deleted = list.remove(index);
        storage.deleteDbEntry(deleted.databaseEntry());
        assert initialSize - 1 == list.size() : "deleteTask failed";
        return ui.showDelete(deleted, list.size());
    }

    /**
     * Instructs the Ui to display a list of tasks that take place on the given date.
     *
     * @param desiredDate The date used to find tasks.
     * @return String representing the displayed list.
     */
    public String showSchedule(LocalDate desiredDate) {
        return ui.displaySchedule(list, desiredDate);
    }

    /**
     * Instructs the Ui to display a list of tasks that contain the given keyword.
     *
     * @param keyword The keyword to look for in task names.
     * @return String representing the displayed list.
     */
    public String findTasksByKeyword(String keyword) {
        return ui.showKeywordFind(list, keyword);
    }

    /**
     * Adds the given task to the task list.
     *
     * @param newTask The task to be added to the task list.
     * @return String representing the task being added to the list.
     */
    public String addTask(Task newTask) {
        int initialSize = list.size();
        list.add(newTask);
        storage.addDbEntry(newTask.databaseEntry());
        assert initialSize + 1 == list.size() : "addTask failed";
        return ui.showAdd(newTask, list.size());
    }
}
