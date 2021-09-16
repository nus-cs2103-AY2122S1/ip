package lebron;

import java.util.ArrayList;

import lebron.task.Task;
import lebron.task.TaskList;

/**
 * Represents a response the bot makes to the user.
 *
 * @author Nigel Tan
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "    "
            + "____________________________________________________________\n";
    private int count;
    private final Lebron app;

    /**
     * Constructor
     */
    public Ui(Lebron lebron) {
        this.count = 1;
        this.app = lebron;
    }

    /**
     * Handles the greet event.
     *
     * @return the desired response.
     */
    public String greet() {
        return ("    Hello! I'm LebronChatBot\n"
                + "    What can I do for you?\n");
    }

    /**
     * Responds to the add event.
     *
     * @param lst the TaskList that the user wants to add the task to.
     * @return the desired response.
     */
    public String replyAdd(ArrayList<Task> lst, Task task) {
        String num = String.valueOf(lst.size());
        return ("    Got it. I've added this task: \n" + "    "
                + task.toString() + "\n" + "    Now you have " + num + " tasks in the list\n");
    }

    /**
     * Responds to the exit event when the user says bye.
     *
     * @return the desired response.
     */
    public String replyExit() {
        return ("    Bye. Hope to see you again soon!\n");
    }

    /**
     * Responds to the display list event when the user says list.
     *
     * @param lst The TaskList to be displayed.
     * @return the desired response.
     */
    public String replyDisplay(TaskList lst) {
        StringBuilder reply = new StringBuilder("    Here are the tasks in your list:\n");
        this.count = 1;
        for (int i = 0; i < lst.getSize(); i++) {
            reply.append("    ").append(count).append(". ").append(lst.getItem(i).toString()).append("\n");
            count++;
        }
        return reply.toString();
    }

    /**
     * Responds to the markDone event.
     *
     * @param task The task that is marked done.
     * @return the desired response.
     */
    public String replyMarkDone(Task task) {
        return "    Nice! I've marked this task as done:\n"
                + "    " + task.toString()
                + "\n";
    }

    /**
     * Responds to the delete event.
     *
     * @param task the task in the list to delete.
     * @param size the size of the TaskList.
     * @return the desired response.
     */
    public String replyDelete(Task task, int size) {
        String num = String.valueOf(size);
        return "    Noted. I've removed this task:\n"
                + "    " + task.toString()
                + "\n" + "    Now you have " + num + " tasks in the list\n";
    }

    /**
     * Responds to the find event.
     *
     * @param lst the current TaskList.
     * @param keyword the keyword.
     * @return the desired response.
     */
    public String replyFind(TaskList lst, String keyword) {
        this.count = 1;
        StringBuilder reply = new StringBuilder("    Here are the matching tasks in your list:\n");
        for (int i = 0; i < lst.getSize(); i++) {
            if (lst.getItem(i).getName().contains(keyword)) {
                reply.append("    ").append(count).append(". ")
                        .append(lst.getItem(i).toString()).append("\n");
                count++;
            }
        }
        return reply.toString();
    }

    /**
     * Responds to the undo event.
     *
     * @return a reply.
     */
    public String replyUndo() {
        return "    I have removed your last action.";
    }

    /**
     * Prints out the given message.
     *
     * @param message the given message.
     * @return the error message.
     */
    public String printException(String message) {
        return message;
    }
}
