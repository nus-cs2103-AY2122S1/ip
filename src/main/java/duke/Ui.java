package duke;

/**
 * Represents the Ui of the bot.
 */
public class Ui {

    public Ui() {

    }

    /**
     * Displays the Ui for the Add command.
     *
     * @param task particular task being handled.
     * @return String to be displayed to the user.
     */
    public String showAdd(String task) {
        return "    Got it. I've added this task:" + "\n" + task;
    }

    /**
     * Displays the Ui for the Delete command.
     *
     * @param task particular task being handled.
     * @param tasks the TaskList.
     * @return String to be displayed to the user.
     */
    public String showDelete(String task, TaskList tasks) {
        return "    Got it. I've deleted this task:" + "\n" + task + "\n"
                + "Now you have " + tasks.numberOfTasks()
                + " tasks remaining in your list!";
    }

    /**
     * Displays the Ui for the Done command.
     *
     * @param task particular task being handled.
     * @return String to be displayed to the user.
     */
    public String showDone(String task) {
        return "    Got it. I've marked this task as done:" + "\n" + task;
    }

    /**
     * Displays the Ui for the Find command.
     *
     * @param task particular task being handled.
     * @return String to be displayed to the user.
     */
    public String showFind(String task) {
        return "    Here are the matching tasks in your list:" + "\n" + task;
    }

    /**
     * Displays the Ui for the List command.
     *
     * @param tasks the TaskList.
     * @return String to be displayed to the user.
     */
    public String showList(TaskList tasks) {
        return "    Here is your list:" + "\n" + tasks.stringifyWholeList();
    }

    /**
     * Displays the Ui for the bye command.
     *
     * @return String to be displayed to the user.
     */
    public String showBye() {
        String bye = "Bye. Hope to see you again soon! Just a little reminder : "
                + "YOU ARE AWESOME - THAT'S WHAT SHE SAID :))";
        return bye;
    }

    /**
     * Displays the Ui for exceptions.
     *
     * @param e exception
     * @return Error message for the particular exception.
     */
    public String showException(Exception e) {
        String display = e.getMessage();
        System.out.println(display);
        return display;
    }

    /**
     * Displays the Ui for the case where
     * duplicate tasks exist.
     *
     * @return String to be displayed to the user.
     */
    public String showDuplicate() {
        return "Sorry you already have the same task type with the "
                + "same task description in your list!" + "\n"
                + "Seems like you're too stressed mann lol...."
                + "Please enter a different task!!!!";
    }
}
