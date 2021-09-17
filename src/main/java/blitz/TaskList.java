package blitz;

import java.util.ArrayList;
import java.util.List;

import blitz.tasks.Task;

public class TaskList {

    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = new ArrayList<>(taskList);
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public Task get(int index) {
        return taskList.get(index);
    }
    public int size() {
        return taskList.size();
    }
    /**
     * Adds given task to the task list.
     *
     * @param taskToAdd
     * @return
     */
    public String addTask(Task taskToAdd) throws BlitzException {
        if (taskList.contains(taskToAdd)) {
            throw new BlitzException("A task with this name already exists in the list! "
                    + "Do you want to consider adding some other task?");
        }
        taskList.add(taskToAdd);
        return "Got it. I've added this task:" + "\n\t" + taskToAdd
                + "\n\nNow you have " + size() + " tasks in the list.";
    }

    /**
     * Deletes task at the given index from the task list.
     *
     * @param taskIndex Index of task to be deleted.
     * @return Message to be displayed upon successful deletion.
     * @throws BlitzException when taskIndex is invalid.
     */

    public String deleteTask(int taskIndex) throws BlitzException {
        if (size() == 0) {
            throw new BlitzException("Cannot perform deletion on empty list!!");
        }
        if (taskIndex < 0 || taskIndex >= size()) {
            throw new BlitzException("You are attempting to delete an invalid task number!");
        }
        Task deletedTask = taskList.remove(taskIndex);
        return "Noted. I've removed this task:" + "\n\t" + deletedTask
                + "\n\nNow you have " + size() + " tasks in the list.";
    }

    /**
     * Marks task at given index as done in the task list.
     *
     * @param taskIndex index of task to be marked as done.
     * @throws BlitzException if taskIndex is invalid.
     */
    public String markTaskAsDone(int taskIndex) throws BlitzException {
        if (taskIndex < 0 || taskIndex >= size()) {
            throw new BlitzException("You are attempting to mark an invalid task number!");
        }
        taskList.get(taskIndex).markAsDone();
        return "Nice! I've marked this task as done:\n" + "\t" + taskList.get(taskIndex);

    }
    /**
     * Finds blitz.tasks which contain the given keyword.
     *
     * @param keyword specifies keyword to be searched for.
     * @return list of blitz.tasks that contain the given keyword.
     */
    public TaskList findMatchingTasks(String keyword) throws BlitzException {
        if (size() == 0) {
            throw new BlitzException("Cannot perform find on empty list!!");
        }
        ArrayList<Task> matchList = new ArrayList<>();
        for (Task task : taskList) {
            if (task.toString().contains(keyword)) {
                matchList.add(task);
            }
        }
        if (matchList.size() == 0) {
            throw new BlitzException("No matches found!!");
        }
        return new TaskList(matchList);
    }
    /**
     * Returns string representation of list items to be printed on screen.
     *
     * @param str string to be displayed before list.
     * @return string representation of list items to be printed on screen.
     */
    public String listToString(String str, Ui ui) throws BlitzException {
        String result = str;
        int ctr = 1;
        if (taskList.size() == 0) {
            if (str.equals(ui.getGreetingMessage())) {
                result = result.concat("\n\n---No items added yet ---\n");
            } else {
                throw new BlitzException("No items currently in the list!!");
            }
        }
        for (Task t: taskList) {
            result = result.concat("\n" + ctr + ". " + taskList.get(ctr - 1));
            ctr++;
        }
        return result + "\n";
    }
}
