package duke.utils;

import duke.task.Task;

/**
 * @author Dr-Octavius
 *
 * Class that represents Interactions with the user
 *
 * @version 1.0
 */
public class Ui {

    private static final String SEP_LINE = "____________________________________________________________";
    private final String welcomeMessage =
            SEP_LINE
            .concat(
                    " \n____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n"
                    + "\nMade by Dr-Octavius\n")
            .concat(SEP_LINE)
            .concat(
                    "\nHello! I'm Duke\n"
                    + "What can I do for you?\n")
            .concat(SEP_LINE);

    /**
     * Output message when bot is started
     *
     * @return String output of error message
     */
    public String greet() {
        return welcomeMessage;
    }

    /**
     * Output message when unknown user input is encountered
     *
     * @return String output of error message
     */
    public String echo() {
        String out = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        return out;
    }

    /**
     * Output message when bot has been added
     *
     * @return String output of goodbye message
     */
    public String exit() {
        String out = " Bye. Hope to see you again soon!";
        return out;
    }

    /**
     * Output message for a To-do task that has been added
     *
     * @param t Added To-do Task
     * @return String output To-do that has been marked as added
     */
    public String todo(Task t, int size) {
        String out = "Got it. I've added the following To-do:\n  "
                .concat(t.toString())
                .concat("\nNow you have ".concat(size + "").concat(" tasks in the list."));
        return out;
    }

    /**
     * Output message for a deadline task that has been added
     *
     * @param t Added Deadline
     * @return String output of deadline task that has been marked as added
     */
    public String deadline(Task t, int size) {
        String out = "Got it. I've added the following deadline:\n  "
                .concat(t.toString())
                .concat("\nNow you have ".concat(size + "").concat(" tasks in the list."));
        return out;
    }

    /**
     * Output message for a task that has been added
     *
     * @param t Added Event
     * @return String output of event that has been marked as added
     */
    public String event(Task t, int size) {
        String out = "Got it. I've added the following event:\n  "
                .concat(t.toString())
                .concat("\nNow you have ".concat(size + "").concat(" tasks in the list."));
        return out;
    }

    /**
     * Output message for a task that has been deleted
     *
     * @param t Deleted Task
     * @return String output task that has been marked as deleted
     */
    public String delete(Task t, int size) {
        String out = "Noted. I've removed the following task:\n  "
                .concat(t.toString())
                .concat("\nNow you have ".concat(size + "").concat(" tasks in the list."));
        return out;
    }

    /**
     * Output message for a task that has been done
     *
     * @param t Done Task
     * @return String output task that has been marked as done
     */
    public String done(Task t) {
        String out = "Nice! I've marked the following task as done: \n  ".concat(t.toString());
        return out;
    }

    /**
     * Lists out the current tasks in the Task List and builds it into an output string
     *
     * @param tl Task List to sort
     * @return All Tasks in Task List as a string output
     */
    public String list(TaskList tl) {
        String out = "Here are the tasks in your list:\n";
        for (int i = 0; i < tl.size(); i++) {
            if (i != 0) {
                out = out.concat("\n");
            }
            out = out.concat((i + 1) + ".").concat(tl.get(i).toString());
        }
        return out;
    }

    /**
     * Sorts the tasks based on chronological order based on date then time and builds it into an output string
     *
     * @param tl Task List to sort
     * @return All Tasks in sorted order as a string output
     */
    public String sort(TaskList tl) {
        TaskList sortedTL = tl.safeSort();
        return list(sortedTL);
    }
}
