package main.java;

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
    private final Task[] list = new Task[100];
    private int listCount = 0;

    /**
     * Adds the task into the list.
     *
     * @param task the task to be added into the list
     */
    protected void addTask(Task task) {
        list[listCount++] = task;

        System.out.println(Duke.LINE);
        System.out.println(ADD);
        System.out.println("\t   " + task);
        if (listCount == 1) {
            System.out.println("\t Now you have 1 task in the list.");
        } else {
            System.out.println("\t Now you have " + listCount + " tasks in the list.");
        }
        System.out.println(Duke.LINE + "\n");
    }

    /**
     * Setter to change the done status of the task.
     */
    protected void setDone(int index) {
        Task task = list[index];
        task.setDone();
        Duke.reply(DONE + task);
    }

    /**
     * Prints the list.
     */
    protected void printList() {
        System.out.println(Duke.LINE);
        System.out.println(LIST_INTRO);
        if (listCount == 0) {
            System.out.println("\t List is empty");
        } else {
            for (int i = 0; i < listCount; i++) {
                System.out.println("\t " + (i + 1) + "." + list[i]);
            }
        }
        System.out.println(Duke.LINE + "\n");
    }
}
