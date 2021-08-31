package lebron;

import lebron.task.Task;
import lebron.task.TaskList;

import java.util.ArrayList;

/**
 * Represents a response the bot makes to the user.
 *
 * @author Nigel Tan
 */
public class Ui {

    private final String HORIZONTAL_LINE = "    ____________________________________________________________\n";
    private int count;

    /**
     * Constructor
     */
    public Ui() {
        this.count = 1;
    }

    /**
     * Handles the greet event.
     */
    public void greet() {
        System.out.println(HORIZONTAL_LINE
                + "    Hello! I'm LebronChatBot\n"
                + "    What can I do for you?\n"
                + HORIZONTAL_LINE);
    }

    /**
     * Responds to the add event.
     *
     * @param lst the TaskList that the user wants to add the task to
     */
    public void replyAdd(ArrayList<Task> lst, Task task) {
        String num = String.valueOf(lst.size());
        System.out.println(HORIZONTAL_LINE + "    Got it. I've added this task: \n" + "    "
                + task.toString() + "\n" + "    Now you have " + num + " tasks in the list\n"
                + HORIZONTAL_LINE);
    }

    /**
     * Responds to the exit event when the user says bye.
     */
    public void exit() {
        System.out.println(HORIZONTAL_LINE
                + "    Bye. Hope to see you again soon!\n"
                + HORIZONTAL_LINE);
    }

    /**
     * Responds to the display list event when the user says list.
     *
     * @param lst The TaskList to be displayed.
     */
    public void replyDisplay(TaskList lst) {
        this.count = 1;
        System.out.println(HORIZONTAL_LINE + "    Here are the tasks in your list:");
        lst.getLst().forEach(item -> {
            System.out.println("    " + count + ". " + item.toString());
            count++;
        });
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Responds to the markDone event.
     *
     * @param task The task that is marked done.
     */
    public void replyMarkDone(Task task) {
        System.out.println(HORIZONTAL_LINE + "    Nice! I've marked this task as done:\n"
                + "    " + task.toString()
                + "\n" + HORIZONTAL_LINE);
    }

    /**
     * Responds to the delete event.
     *
     * @param task the task in the list to delete.
     * @param size the size of the TaskList.
     */
    public void replyDelete(Task task, int size) {
        String num = String.valueOf(size);
        System.out.println(HORIZONTAL_LINE + "    Noted. I've removed this task:\n"
                + "    " + task.toString()
                + "\n" + "    Now you have " + num + " tasks in the list\n"
                + HORIZONTAL_LINE);
    }

    /**
     * Responds to the find event.
     *
     * @param lst the current TaskList
     * @param keyword the keyword
     */
    public void replyFind(TaskList lst, String keyword) {
        this.count = 1;
        System.out.println(HORIZONTAL_LINE + "    Here are the matching tasks in your list:");
        lst.getLst().forEach(item -> {
            if (item.getName().contains(keyword)) {
                System.out.println("    " + count + ". " + item.toString());
                count++;
            }

        });
        System.out.println(HORIZONTAL_LINE);
    }

}
