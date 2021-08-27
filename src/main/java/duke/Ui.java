package duke;

/**
 * This class will handle the stdout messages that users will receive from Duke.
 */
public class Ui {

    /**
     * This method will wrap any message within a formatted box.
     *
     * @param content The message to be shown to the user.
     */
    public void reply(String content) {
        // wrap the reply in a divider
        String divider = "    ____________________________________________________________";
        System.out.println(divider + "\n" + content + divider);
    }

    /**
     * This method will show a welcome message for the user.
     */
    public void hi() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String greeting = "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" ;
        reply(greeting);
    }

    /**
     * This method will show a farewell message for the user.
     */
    public void bye() {
        String message = "     Bye. Hope to see you again soon!\n";
        reply(message);
    }

    /**
     * This method will show users that the task has been added successfully to taskList.
     *
     * @param t The task being added.
     * @param size The current size of taskList.
     */
    public void add(Task t, int size) {
        String message = "     Got it. I've added this task: \n" +
                String.format("       %s\n", t) +
                String.format("     Now you have %d tasks in the list.\n", size);
        reply(message);
    }

    /**
     * This method will show users that the task has been successfully marked as completed.
     *
     * @param t The taks that is marked as completed.
     */
    public void done(Task t) {
        String message = String.format("     Nice! I've marked this task as done: \n" +
                "       %s\n", t);
        reply(message);
    }

    /**
     * This method will show users that the task has been deleted successfully from the taskList.
     *
     * @param t The task being deleted.
     * @param size The current size of the taskList.
     */
    public void delete(Task t, int size) {
        String message = "     Noted. I've removed this task: \n" +
                String.format("       %s\n", t) +
                String.format("     Now you have %d tasks in the list.\n", size);
        reply(message);
    }
}
