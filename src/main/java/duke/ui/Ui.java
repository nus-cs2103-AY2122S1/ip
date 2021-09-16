package duke.ui;


import duke.task.Task;

/**
 * Manages the user interface and contains all user outputs to print during user interaction.
 */
public class Ui {

    /**
     * Creates new Ui object.
     */
    public Ui() {
        printIntro();
    }

    /**
     * Compiles messages in an array for Duke output.
     *
     * @param messages Messages to be output by Duke.
     * @return String Array of messages to be output.
     */
    public static String[] display(String... messages) {
        return messages;
    }

    /**
     * Compiles message in an array for Duke output.
     *
     * @param message Message to be output by Duke.
     * @return String Array of messages to be output.
     */
    public static String[] display(String message) {
        return new String[] { message };
    }

    /**
     * Prints logo of DUKE and short introduction.
     */
    public static String[] printIntro() {
        String logo = "____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return display("HELLO FROM", logo);
    }


    /**
     * Prints simple farewell message to user.
     *
     * @return String Array of output.
     */
    public static String[] end() {
        return display("Bye. Hope to see you again soon!");
    }

    /**
     * Prints simple message to indicate the task that has been added and how many items user has in the list.
     *
     * @param task Task that is currently being added into the list.
     * @param size Size of current list including current Task being added.
     * @return String Array of output.
     */
    public static String[] printTaskAdded(Task task, int size) {
        return display("Got it. I've added this task: ",
                task.toString(), "Now you have " + size + " tasks in the list");
    }

    /**
     * Prints simple message to indicate that a certain task has been marked as completed.
     *
     * @param task Task that user has indicated as completed.
     * @return String Array of output.
     */
    public static String[] printTaskCompleted(Task task) {
        return display("Nice! I've marked this task as done:",
                task.toString());
    }

    /**
     * Prints simple message to indicate that a certain task has been marked as uncompleted.
     *
     * @param task Task that user has indicated as completed.
     * @return String Array of output.
     */
    public static String[] printTaskUncompleted(Task task) {
        return display("OHNOES! I've marked this task as not completed:",
                task.toString());
    }

    /**
     * Prints simple message to indicate that a certain task has been deleted and how many tasks remain.
     *
     * @param task Task that the user wants deleted.
     * @param size Size of list with remaining tasks.
     * @return String Array of output.
     */
    public static String[] printDeleteTask(Task task, int size) {
        return display("Noted. I've removed this task:",
                task.toString(), "Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints results of finding tasks.
     *
     * @param result Result of tasks that pass the search.
     * @return String Array of output.
     */
    public static String[] printFindTask(Task[] result) {
        if (result[0] == null) {
            return display("There are no matching tasks in your list!");
        }
        int count = 0;
        for (int i = 0; i < result.length; i++) {
            if (result[i] == null) {
                break;
            }
            count++;
        }
        String[] output = new String[count + 1];
        output[0] = "Here are the matching tasks in your list:";
        for (int i = 0; i < result.length; i++) {
            if (result[i] == null) {
                break;
            }
            output[i + 1] = (i + 1) + ". " + result[i].toString();
        }
        return output;
    }

    /**
     * Prints out items in history ArrayList.
     *
     * @param input Input String array containing objects in history.
     * @return Numbered list of items in history.
     */
    public static String[] printHistory(String[] input) {
        for (int i = 0; i < input.length; i++) {
            input[i] = (i + 1) + ". " + input[i];
        }
        return input;
    }

}
