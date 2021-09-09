package duke;

/**
 * Represents the user interface that will be seen by the user when using Duke.
 */
public class Ui {

    /**
     * Class constructor that constructs a Ui object.
     */
    public Ui() {

    }

    /**
     * Displays welcome message.
     */
    public void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }

    /**
     * Displays error message.
     *
     * @param error DukeException that was caught.
     */
    public void showError(DukeException error) {
        System.out.println(error);
    }

    /**
     * Displays bye message.
     */
    public void bye() {
        System.out.println("Bye! Hope to see you again soon!");
    }

    /**
     * Displays the tasks in the TaskList.
     *
     * @param tasklist TaskList to be displayed.
     */
    public void list(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        System.out.println(taskList);
    }

    /**
     * Displays done message with the associated task.
     *
     * @param task The task that was done.
     */
    public void done(Task task) {
        System.out.println("Nice! I have marked this task as done!");
        System.out.println(task);
    }

    /**
     * Displays message that a task is added and also informs how many tasks are in the list after.
     *
     * @param task The task that is added.
     * @param tasklist TaskList that the task is added to.
     */
    public void addTask(Task task, TaskList taskList) {
        int length = taskList.noOfTask();

        System.out.println("Added task:");
        System.out.println(task);
        System.out.println("You have " + length + " tasks in the list");
    }

    /**
     * Displays message that a task is deleted and also informs how many tasks are in the list after.
     *
     * @param task The task that is deleted.
     * @param tasklist TaskList that the task is deleted from.
     */
    public void deleteTask(Task task, TaskList taskList) {
        int length = taskList.noOfTask();

        System.out.println("Ok! I have removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + length + " tasks in the list.");
    }

    public void findTask(TaskList taskList) {
        int length = taskList.noOfTask();

        if (length != 0) {
            System.out.println("Here are the matching tasks in your list:");
            System.out.println(taskList);
        } else {
            System.out.println("Oh no there are no matching tasks in your list :((");
        }

    }
}
