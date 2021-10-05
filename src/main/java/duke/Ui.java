package duke;

/**
 * Class that deals with the user interface of Duke bot.
 */
public class Ui {
    private Duke duke;

    public Ui(Duke duke) {
        this.duke = duke;
    }

    public void greetUser() {
        duke.showMessage("Hello! I'm Duke\n"
                        + "What can I do for you?", true);
    }

    public void displayTaskAdded(Task td, int ctr) {
        ctr++;
        duke.showMessage("Got it! I've added this task: \n"
                        + td.toString()
                        + "\nNow you have "
                        + ctr + " tasks in the list.", true);

    }

    public void displayTaskRemoved(Task td, int ctr) {
        duke.showMessage("Noted. I've now removed this task: \n"
                        + td + "\nNow you have "
                        + ctr + " tasks in the list.", true);
    }
    public void displayTaskDone(Task td) {
        duke.showMessage("Nice! I've marked this task as done: \n" + td, true);
    }
    public void displayTaskList(TaskList t) {
        duke.showMessage("Here are the tasks on your list: \n" + t.toString(), true);
    }
    public void displayFoundList(String keyword) {
        duke.showMessage(keyword, true);
    }
    public void displaySortedTaskList(TaskList t) {
        duke.showMessage("Here are the tasks on your list: \n" + t, true);
    }
    public void sayBye() {
        duke.showMessage("Bye! Hope to see you again soon!", true);
    }
    public void displayError(String msg) {
        duke.showMessage(msg, true);
    }
}
