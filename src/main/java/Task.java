package main.java;

/**
 * A task contains its description and a boolean of whether it is done or not.
 *
 * @author Zhen Xuan (Tutorial Group 04)
 * @version CS2103T AY21/22 S2
 */
public abstract class Task {
    private final String DESCRIPTION;
    private boolean isDone = false;
    private static final String DONE = "Nice! I've marked this task as done: \n\t   ";
    private static final String ADD = "\t Got it. I've added this task: ";
    private static final String LIST_INTRO = "\t Here are the tasks in your list:";
    private static Task[] list = new Task[100];
    private static int listCount = 0;

    /**
     * Constructor for task.
     *
     * @param description the description
     */
    public Task(String description) {
        this.DESCRIPTION = description;
        list[listCount++] = this;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return status icon
     */
    private String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Prints the list.
     */
    protected static void printList() {
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

    /**
     * Prints the number of tasks in list.
     */
    protected static void printCount() {
        if (listCount == 1) {
            System.out.println("\t Now you have 1 task in the list.");
        } else {
            System.out.println("\t Now you have " + listCount + " tasks in the list.");
        }
    }

    /**
     * Setter to change the done status of the task.
     */
    protected static void setDone(int index) {
        Task task = list[index];
        task.isDone = true;
        Duke.reply(DONE + task);
    }

    /**
     * Print newly-initialised task.
     */
    protected void printInitTask() {
        System.out.println(Duke.LINE);
        System.out.println(ADD);
        System.out.println("\t   " + this);
        Task.printCount();
        System.out.println(Duke.LINE + "\n");
    }

    /**
     * Returns the string representation of the task.
     *
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.DESCRIPTION;
    }
}
