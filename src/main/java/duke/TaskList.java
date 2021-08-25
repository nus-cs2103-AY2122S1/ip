package duke;

import duke.task.Task;
import java.util.ArrayList;

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
    public void add(Task task) {
        this.taskList.add(task);
        System.out.println("     Got it. I have added this task:");
        System.out.println("       " + task.toString());
        System.out.println("     Now you have " + size()
                + (size() == 1 ? " task" : " tasks") + " in the list.");
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
    public void markAsDone(int index) {
        Task taskToComplete = get(index - 1);
        if (taskToComplete.getIsDone()) {
            //if task is already completed
            System.out.println("     I have already marked this task as completed!");
        } else {
            taskToComplete.setIsDone(true);
            System.out.println("     Nice! I've marked this task as done:");
            System.out.println("       " + taskToComplete.toString());
        }
    }

    /**
     * Deletes a task and prints the deleted task to the screen.
     *
     * @param index The index of the task to delete in the taskList.
     */
    public void delete(int index) {
        Task taskToDelete = this.taskList.remove(index - 1);
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + taskToDelete.toString());
        System.out.println("     Now you have " + size()
                + (size() == 1 ? " task" : " tasks") + " in the list.");
    }

<<<<<<< HEAD
    /**
     * Returns the task at a specified index in the taskList.
     *
     * @param index The index of the task to retrieve in the taskList.
     * @return The task at the index-th position in the taskList.
     */
=======
    public void searchAndDisplay(String keyword) {
        ArrayList<Task> temp = new ArrayList<>();
        for (int i = 0; i < size(); i++) {
            if (get(i).getDescription().contains(keyword)) {
                temp.add(get(i));
            }
        }
        if (temp.size() == 0) {
            System.out.println("    There are no matching tasks in your list!");
        } else {
            System.out.println("    Here are the matching tasks in your list");
            for (int i = 0; i < temp.size(); i++) {
                int currNum = i + 1;
                Task currTask = temp.get(i);
                System.out.println("     " + currNum + ". " + currTask.toString());
            }
        }
    }

>>>>>>> branch-Level-9
    public Task get(int index) {
        return this.taskList.get(index);
    }

    /**
     * Prints the current contents of the taskList to the screen.
     */
    public void showList() {
        if (size() == 0) {
            System.out.println("    There are no tasks in your list currently!");
        } else {
            System.out.println("    Here are the tasks in your list:");
            for (int i = 0; i < size(); i++) {
                int currNum = i + 1;
                Task currTask = get(i);
                System.out.println("     " + currNum + ". " + currTask.toString());
            }
        }
    }
}
