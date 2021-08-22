package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * TaskList stores the list of tasks.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S2
 */
public class TaskList {
    private static final String ADD = "\t Got it. I've added this task:";
    private static final String LIST_INTRO = "\t Here are the tasks in your list:";
    private static final String DONE = "Nice! I've marked this task as done:\n\t   ";
    private static final String DELETE = "Noted. I've removed this task:\n\t   ";
    private final ArrayList<Task> LIST;

    /**
     * Constructor for TaskList.
     */
    TaskList(ArrayList<Task> list) {
        this.LIST = list;
    }

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
     * Prints Tasks on the specified date.
     */
    protected void printListDate(String date) {
        LocalDate localDate = LocalDate.parse(date.replace(" ", ""), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(Duke.LINE);
        System.out.println(LIST_INTRO);
        if (this.LIST.size() == 0) {
            System.out.println("\t There are no tasks on or due on this date.");
        } else {
            int count = 0;
            for (int i = 0; i < this.LIST.size(); i++) {
                Task t = this.LIST.get(i);
                if (t.onDate(localDate)) {
                    System.out.println("\t " + (++count) + "." + t);
                }
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
