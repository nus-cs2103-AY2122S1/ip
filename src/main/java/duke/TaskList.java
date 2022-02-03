package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Locale;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Deletes a task from the list.
     *
     * @param index ArrayList index of the task to be deleted.
     * @return String representation of the deleted task.
     */
    public String deleteTask(int index) {
        String taskString = taskList.get(index).toString();
        this.taskList.remove(index);
        return taskString;
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to add to the list.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Returns a string representing the duplicate task in the list, if it exists.
     *
     * @param task New task to be duplicate-checked before adding.
     * @return A string representing the duplicate task in the list, or null if no duplicate.
     */
    public String getDuplicate(Task task) {
        String newTaskString = task.toString();
        for (Task nextTask : taskList) {
            String nextTaskUndoneString = nextTask.toUndoneString();
            if (nextTaskUndoneString.equals(newTaskString)) {
                return nextTask.toString();
            }
        }
        return null;
    }

    /**
     * Marks a task in the list as done.
     *
     * @param index ArrayList index of the task to mark as done.
     * @return String representation of the task marked as done.
     */
    public String markAsDone(int index) {
        Task task = taskList.get(index);
        task.markAsDone();
        return task.toString();
    }

    /**
     * Returns the list represented in the format used in the storage file.
     *
     * @return String representation of the list, given in the storage format.
     */
    public String toStorageString() {
        String result = "";
        for (int i = 0; i < taskList.size(); i++) {
            Task nextTask = taskList.get(i);
            String nextString = nextTask.toStorageString() + "\n";
            result += nextString;
        }
        return result.trim();
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Number of tasks in the list.
     */
    public int getSize() {
        return this.taskList.size();
    }

    public String searchTask(String searchWord) {
        String result = "";
        int counter = 1;
        for (int i = 0; i < taskList.size(); i++) {
            Task nextTask = taskList.get(i);
            String nextDescriptionLower = nextTask.getDescription().toLowerCase();
            String searchWordLower = searchWord.toLowerCase();
            if (nextDescriptionLower.contains(searchWordLower)) {
                result += counter + "." + nextTask.toString() + "\n";
                counter++;
            }
        }
        result = result.trim();
        return result;
    }

    /**
     * Returns the list represented in the format shown to the user.
     *
     * @return String representation of the list.
     */
    @Override
    public String toString() {
        String result = "";

        for (int i = 0; i < taskList.size(); i++) {
            Task nextTask = taskList.get(i);
            String nextTaskString = (i + 1) + "." + nextTask.toString() + "\n";
            result += nextTaskString;
        }

        result = result.trim();
        return result;
    }
}
