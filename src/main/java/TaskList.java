package main.java;
import java.util.ArrayList;

/**
 * TaskList stores the list of tasks.
 *
 * @author Zhen Xuan (Tutorial Group 04)
 * @version CS2103T AY21/22 S2
 */
public class TaskList {
    private static final String ADD = "\t Got it. I've added this task:";
    private static final String LIST_INTRO = "\t Here are the tasks in your list:";
    private static final String DONE = "Nice! I've marked this task as done:\n\t   ";
    private static final String DELETE = "Noted. I've removed this task:\n\t   ";
    private final ArrayList<Task> LIST = new ArrayList<>();

    /**
     * Adds the task into the list.
     *
     * @param task the task to be added into the list
     */
    protected void addTask(Task task) {
        this.LIST.add(task);
        System.out.println(Duke.LINE);
        System.out.println(ADD);
        System.out.println("\t   " + task);
        if (this.LIST.size() == 1) {
            System.out.println("\t Now you have 1 task in the list.");
        } else {
            System.out.println("\t Now you have " + this.LIST.size() + " tasks in the list.");
        }
        System.out.println(Duke.LINE + "\n");
    }

    /**
     * Setter to change the done status of the task.
     */
    protected void setDone(int index) {
        Task task = this.LIST.get(index);
        task.setDone();
        Duke.reply(DONE + task);
    }

    /**
     * Delete the task at a specified index.
     */
    protected void delete(int index) {
        Task task = this.LIST.get(index);
        this.LIST.remove(index);
        Duke.reply(DELETE + task);
    }

    /**
     * Prints the list.
     */
    protected void printList() {
        System.out.println(Duke.LINE);
        System.out.println(LIST_INTRO);
        if (this.LIST.size() == 0) {
            System.out.println("\t List is empty");
        } else {
            for (int i = 0; i < this.LIST.size(); i++) {
                System.out.println("\t " + (i + 1) + "." + this.LIST.get(i));
            }
        }
        System.out.println(Duke.LINE + "\n");
    }

    /**
     * Return the number of items in the TaskList.
     *
     * @return listCount
     */
    protected int count() {
        return this.LIST.size();
    }

}
