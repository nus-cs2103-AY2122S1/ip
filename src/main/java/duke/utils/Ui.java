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

    public String greet() {
        return welcomeMessage;
    }

    /**
     * Prints out error message for unrecognised commands
     */
    public String echo() {
        String out = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        return out;
    }

    /**
     * Prints out exit message for unrecognised commands
     */
    public String exit() {
        String out = " Bye. Hope to see you again soon!";
        return out;
    }
    /**
     * Prints out message when new Task has been added
     */
    public String add(String text) {
        String out = "added: ".concat(text);
        return out;
    }

    /**
     * Prints out message when a todo has been added
     */
    public String todo(Task t, int size) {
        String out = "Got it. I've added duke.task:\n  "
                .concat(t.toString())
                .concat("\nNow you have ".concat(size + "").concat(" tasks in the list."));
        return out;
    }

    /**
     * Prints out message when a deadline has been added
     */
    public String deadline(Task t, int size) {
        String out = "Got it. I've added duke.task:\n  "
                .concat(t.toString())
                .concat("\nNow you have ".concat(size + "").concat(" tasks in the list."));
        return out;
    }

    /**
     * Prints out message when an event has been added
     */
    public String event(Task t, int size) {
        String out = "Got it. I've added duke.task:\n  "
                .concat(t.toString())
                .concat("\nNow you have ".concat(size + "").concat(" tasks in the list."));
        return out;
    }

    /**
     * Prints out message when a task has been deleted
     */
    public String delete(Task t, int size) {
        String out = "Noted. I've removed duke.task:\n  "
                .concat(t.toString())
                .concat("\nNow you have ".concat(size + "").concat(" tasks in the list."));
        return out;
    }

    /**
     * Prints out message when a task has been done
     */
    public String done(Task t) {
        String out = "Nice! I've marked duke.task as done: \n  ".concat(t.toString());
        return out;
    }

    /**
     * Prints out the current list of tasks
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
}
