import java.util.ArrayList;

/**
 * Represents a response the bot makes to the user.
 * @author Nigel Tan
 */
public class Response {
    private final String HORIZONTAL_LINE = "    ____________________________________________________________\n";
    private int count;

    /**
     * Constructor
     */
    public Response() {
        this.count = 1;
    }

    /**
     * This method handles the greet event.
     */
    public void greet() {
        System.out.println(HORIZONTAL_LINE +
                "    Hello! I'm LebronChatBot\n" +
                "    What can I do for you?\n" +
                HORIZONTAL_LINE);
    }

    /**
     * This method handles the add event.
     * @param task the Task that the user wants to add
     */
    public void add(Task task) {
        lst.add(task);
        String num = String.valueOf(lst.size());
        System.out.println(HORIZONTAL_LINE + "    Got it. I've added this task: \n" + "    " +
                task.toString() + "\n" + "    Now you have " + num + " tasks in the list\n" +
                HORIZONTAL_LINE);
    }

    /**
     * This method handles the exit event when the user says bye.
     */
    public void exit() {
        System.out.println(HORIZONTAL_LINE +
                "    Bye. Hope to see you again soon!\n" +
                HORIZONTAL_LINE);
    }

    /**
     * This method handles the display list event when the user says list.
     */
    public void display() {
        this.count = 1;
        System.out.println(HORIZONTAL_LINE + "    Here are the tasks in your list:");
        lst.forEach(item -> {
            System.out.println("    " + count + ". " + item.toString());
            count++;
        });
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * This method handles the markDone event when the user says done, marking the Task as done.
     * @param pos the position of the Task in the ArrayList lst
     */
    public void markDone(int pos) {
        Task task = lst.get(pos);
        task.mark();
        System.out.println(HORIZONTAL_LINE + "    Nice! I've marked this task as done:\n" +
                "    " + task.toString() +
                "\n" + HORIZONTAL_LINE);
    }

    /**
     * This method handles the delete response.
     * @param pos the position of the task in the list to delete
     */
    public void delete(int pos) {
        Task task = lst.remove(pos);
        String num = String.valueOf(lst.size());
        System.out.println(HORIZONTAL_LINE + "    Noted. I've removed this task:\n" +
                "    " + task.toString() +
                "\n" + "    Now you have " + num + " tasks in the list\n"
                +  HORIZONTAL_LINE);
    }

    public ArrayList<Task> getLst() {
        return lst;
    }
}
