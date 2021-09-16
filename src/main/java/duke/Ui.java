package duke;

import duke.task.Task;

/**
 * Ui class to handle printing notices to the user, and reading of user input to pass to Parser.
 */
public class Ui {

    /**
     * Method called at beginning of application run. Prints welcome message.
     */
    public String init() {
        return "I'm Frosty, your personal task manager! How can I help?";
    }

    /**
     * Returns a String representing tasks in the list.
     *
     * @param tasklist tasks to be displayed.
     * @return String representation of tasks.
     */

    public String displayList(TaskList tasklist) {
        String res = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasklist.size(); i++) {
            res += tasklist.get(i).toString() + "\n";
        }
        return res;
    }

    /**
     * Returns a String representing tasks found.
     *
     * @param tasklist tasks to be displayed.
     * @return String representation of tasks.
     */
    public String displayFindList(TaskList tasklist) {
        String res = "Here are the matching tasks in your list:\n";
        return res + displayList(tasklist);
    }

    /**
     * Returns a String to notify user of task being added.
     *
     * @param tasklist tasks containing new added task.
     * @return String representation of message.
     */
    public String notifySuccessfulAdd(TaskList tasklist) {
        String res = "";
        res += "Got it. I've added this task:\n";
        res += "  " + tasklist.get(tasklist.size() - 1) + "\n";
        res += "Now you have " + tasklist.size() + " tasks in the list.";
        return res;
    }

    /**
     * Returns a String to notify user that task has been marked done.
     *
     * @param tasklist tasks containing task marked done.
     * @param index index of the task marked done.
     * @return String to notify user that task has been marked done.
     */
    public String notifySuccessfulMarkDone(TaskList tasklist, int index) {
        String res = "";
        res += "Nice! I've marked this task as done:\n";
        res += "  " + tasklist.get(index);
        return res;
    }

    /**
     * Returns a String to notify user that task has been deleted.
     *
     * @param tasklist remaining tasks after deletion
     * @param removed task that was removed
     * @return String to notify user that task has been deleted.
     */
    public String notifySuccessfulDelete(TaskList tasklist, Task removed) {
        String res = "";
        res += "Noted. I've removed this task:\n";
        res += "  " + removed + "\n";
        res += "Now you have " + tasklist.size() + " tasks in the list.";
        return res;
    }

    public String notifyBadCommand() {
        return ("Sorry! I don't know what your request means. Please try again?");
    }

    public String notifySavingComplete() {
        return ("Your list has been saved!");
    }

    public String notifySavingError() {
        return ("Something went wrong and I can't save your list, sorry!");
    }

    public String notifyIndexOutOfBounds() {
        return ("Sorry! Your command has an invalid index choice, or is missing components");
    }

    public String notifyImproperIndex() {
        return ("Sorry! I can't understand the index for your command");
    }

    public String notifyError(DukeException e) {
        return ("Sorry! Your command has the following issue: " + e.getMessage());
    }

    public String notifyImproperDateTime() {
        return ("Sorry! I don't recognise the format for the date and time you've entered.");
    }

    public String notifyFolderFound() {
        return ("I've found the data folder. Your session will be saved there.");
    }

    public String notifyFolderCreated() {
        return ("I couldn't find a data folder. I've initialised one for your data.");
    }

    public String notifyUpdateComplete(Task after) {
        return ("I've updated your selected task to: " + after);
    }

    public String notifyUpdateError() {
        return "Sorry! You've tried to update the date of a todo task";
    }
}
