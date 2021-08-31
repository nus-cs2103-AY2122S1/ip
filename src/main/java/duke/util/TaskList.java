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

    /** An ArrayList to store user's tasks. */
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
     * Adds a task into the taskList and prints the added task to the screen.
     *
     * @param task The task to be added.
     */
    public String add(Task task, Storage storage) throws IOException {
        this.taskList.add(task);
        storage.save(this);
        return "Got it. I have added this task:\n"
                + "  " + task.toString()
                + "\n Now you have " + size()
                + (size() == 1 ? " task" : " tasks") + " in the list.";
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
     * Marks a task as completed and prints the completed task to the screen.
     *
     * @param index The index of the task to mark as completed in the taskList.
     */
    public String markAsDone(int index, Storage storage) throws IOException {
        Task taskToComplete = get(index - 1);
        if (taskToComplete.getIsDone()) {
            return "I have already marked this task as completed!";
        } else {
            taskToComplete.setIsDone(true);
            storage.save(this);
            return "Nice! I've marked this task as done:\n  " + taskToComplete.toString();
        }
    }

    /**
     * Deletes a task and prints the deleted task to the screen.
     *
     * @param index The index of the task to delete in the taskList.
     */
    public String delete(int index, Storage storage) throws IOException {
        Task taskToDelete = this.taskList.remove(index - 1);
        storage.save(this);
        return "Noted. I've removed this task:\n"
                + "  " + taskToDelete.toString()
                + "\nNow you have " + taskList.size()
                + (taskList.size() == 1 ? " task" : " tasks") + " in the list.";
    }

    /**
     * Searches for all tasks whose description contains the specified keyword and prints them to screen.
     *
     * @param keyword The keyword to search for.
     */
    public String searchAndDisplay(String keyword) {
        ArrayList<Task> temp = new ArrayList<>();
        for (int i = 0; i < size(); i++) {
            if (get(i).getDescription().contains(keyword)) {
                temp.add(get(i));
            }
        }
        if (temp.size() == 0) {
            return "There are no matching tasks in your list!";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < temp.size(); i++) {
                int currNum = i + 1;
                Task currTask = temp.get(i);
                String taskString = "  " + currNum + ". " + currTask.toString() + "\n";
                sb.append(taskString);
            }
            return sb.toString();
        }
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
     * Prints the current contents of the taskList to the screen.
     */
    public String showList() {
        if (size() == 0) {
            return "There are no tasks in your list currently!";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Here are the tasks in your list:\n");
            for (int i = 0; i < size(); i++) {
                int currNum = i + 1;
                Task currTask = get(i);
                String taskString = "  " + currNum + ". " + currTask.toString() + "\n";
                sb.append(taskString);
            }
            return sb.toString();
        }
    }
}
