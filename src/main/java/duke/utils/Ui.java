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

    private final String SEP_LINE = "____________________________________________________________";
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

    public void greet() {
        System.out.println(welcomeMessage);
    }

    /**
     * Prints out error message for unrecognised commands
     */
    public void echo() {
        String out = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        System.out.println(messageWrapper(out));
    }

    /**
     * Prints out exit message for unrecognised commands
     */
    public void exit() {
        String out = " Bye. Hope to see you again soon!";
        System.out.println(messageWrapper(out));
    }
    /**
     * Prints out message when new Task has been added
     */
    public void add(String text) {
        String out = "added: ".concat(text);
        System.out.println(messageWrapper(out));
    }

    /**
     * Prints out message when a todo has been added
     */
    public void todo(Task t, int size) {
        String out = "Got it. I've added duke.task:\n  "
                .concat(t.toString())
                .concat("\nNow you have ".concat(size + "").concat(" tasks in the list."));
        System.out.println(messageWrapper(out));
    }

    /**
     * Prints out message when a deadline has been added
     */
    public void deadline(Task t, int size) {
        String out =  "Got it. I've added duke.task:\n  "
                .concat(t.toString())
                .concat("\nNow you have ".concat(size + "").concat(" tasks in the list."));
        System.out.println(messageWrapper(out));
    }

    /**
     * Prints out message when an event has been added
     */
    public void event(Task t, int size) {
        String out = "Got it. I've added duke.task:\n  "
                .concat(t.toString())
                .concat("\nNow you have ".concat(size + "").concat(" tasks in the list."));
        System.out.println(messageWrapper(out));
    }

    /**
     * Prints out message when a task has been deleted
     */
    public void delete(Task t, int size) {
        String out = "Noted. I've removed duke.task:\n  "
                .concat(t.toString())
                .concat("\nNow you have ".concat(size + "").concat(" tasks in the list."));
        System.out.println(messageWrapper(out));
    }

    /**
     * Prints out message when a task has been done
     */
    public void done(Task t) {
        String out = "Nice! I've marked duke.task as done: \n  ".concat(t.toString());
        System.out.println(messageWrapper(out));
    }

    /**
     * Prints out the current list of tasks
     */
    public void list(TaskList tl) {
        String out = "Here are the tasks in your list:\n";
        for (int i = 0; i < tl.size(); i++) {
            if (i != 0) out = out.concat("\n");
            out = out.concat((i+1) + ".").concat(tl.get(i).toString());
        }
        System.out.println(messageWrapper(out));
    }

    private String messageWrapper(String text) {
        return SEP_LINE.concat("\n").concat(text).concat("\n").concat(SEP_LINE);
    }
}
