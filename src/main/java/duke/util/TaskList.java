package duke.util;

import java.io.IOException;
import java.util.ArrayList;

import duke.task.Task;

/**
 * Represents the list of tasks that Duke keeps track of for its user.
 *
 * @author Javier Phon Zhee Kai.
 * @version CS2103T AY21/22 Sem 1.
 */
public class TaskList {
    /**
     * An ArrayList to store user's tasks.
     */
    private final ArrayList<Task> taskList;

    /**
     * Constructor of the TaskList class.
     *
     * @param taskList An ArrayList to store user's tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Second Constructor of the TaskList class.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds a task into the taskList and returns the added task as a string.
     *
     * @param taskToAdd The task to be added.
     * @param storage   An instance of a the Storage class that saves and loads Duke's data.
     * @return A string representing the task that has been added.
     */
    public String add(Task taskToAdd, Ui ui, Storage storage) throws IOException {
        String duplicatedTaskString = checkDuplicates(taskToAdd);
        if (!duplicatedTaskString.equals("")) {
            return ui.showDuplicateTaskError(duplicatedTaskString);
        }
        this.taskList.add(taskToAdd);
        storage.save(this);
        return ui.showAddTask(taskToAdd, this);
    }

    /**
     * Returns the size of the current taskList.
     *
     * @return An int representing the size of the taskList.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Marks a task as completed and returns the completed task to the screen as a string.
     *
     * @param index   The index of the task to mark as completed in the taskList.
     * @param storage An instance of a the Storage class that saves and loads Duke's data.
     * @return A string representing the task that has been marked as completed.
     */
    public String markAsDone(int index, Ui ui, Storage storage) throws IOException {
        Task taskToComplete = get(index - 1);
        if (taskToComplete.getIsDone()) {
            return ui.showAlreadyMarkedAsDone();
        } else {
            taskToComplete.setIsDone(true);
            storage.save(this);
            return ui.showMarkedAsDone(taskToComplete);
        }
    }

    /**
     * Deletes a task and returns the deleted task as a string.
     *
     * @param index   The index of the task to delete in the taskList.
     * @param storage An instance of a the Storage class that saves and loads Duke's data.
     * @return A string representing the task that has been deleted.
     */
    public String delete(int index, Ui ui, Storage storage) throws IOException {
        Task taskToDelete = this.taskList.remove(index - 1);
        storage.save(this);
        return ui.showDelete(taskToDelete, this);
    }

    /**
     * Searches for all tasks whose description contains the specified keyword returns them as a string.
     *
     * @param keyword The keyword to search for.
     * @return A string representing all tasks that match the keyword.
     */
    public String search(String keyword, Ui ui) {
        ArrayList<Task> temp = new ArrayList<>();
        for (int i = 0; i < size(); i++) {
            String taskString = get(i).toString();
            if (taskString.contains(keyword)) {
                temp.add(get(i));
            }
        }
        if (temp.size() == 0) {
            return ui.showFailedSearch();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(ui.showSearchHeader());
        for (int i = 0; i < temp.size(); i++) {
            int currNum = i + 1;
            Task currTask = temp.get(i);
            String taskString = generateTaskString(currTask, currNum);
            sb.append(taskString);
        }
        return sb.toString();
    }

    /**
     * Returns the task at a specified index in the taskList.
     *
     * @param index The index of the task to retrieve in the taskList.
     * @return The task at the index-th position in the taskList.
     */
    public Task get(int index) {
        return this.taskList.get(index);
    }

    /**
     * Searches for all tasks whose description contains the specified keyword returns them as a string.
     *
     * @return A string representing all tasks in the task list.
     */
    public String getList(Ui ui) {
        if (size() == 0) {
            return ui.showEmptyList();
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(ui.showListHeader());
            for (int i = 0; i < size(); i++) {
                int currNum = i + 1;
                Task currTask = get(i);
                String taskString = generateTaskString(currTask, currNum);
                sb.append(taskString);
            }
            return sb.toString();
        }
    }

    /**
     * Returns the first task in the taskList that matches the task to be added.
     *
     * @param taskToAdd The task to be added.
     * @return A string representing the first task in the taskList that matches the task to be added.
     */
    private String checkDuplicates(Task taskToAdd) {
        String result = "";
        for (int i = 0; i < size(); i++) {
            Task currTask = get(i);
            int currNum = i + 1;
            if (currTask.equals(taskToAdd)) {
                result = generateTaskString(currTask, currNum);
                break;
            }
        }
        return result;
    }

    /**
     * Returns a string containing an index and a task.
     *
     * @param task The task to be returned.
     * @param index The number of the task.
     * @return A string containing an index and a task.
     */
    private String generateTaskString(Task task, int index) {
        return "  " + index + ". " + task.toString() + "\n";
    }
}
