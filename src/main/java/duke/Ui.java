package duke;

/**
 * Class to contain all the UI elements of Duke.
 */
public class Ui {

    public Ui() {

    }

    /**
     * Prints out the inputted text in the specified format.
     * @param text the text to be printed.
     */
    public static void print(String text) {
        System.out.println("=======================================");
        text.lines().map(x -> "    " + x).forEach(x -> System.out.println(x));
        System.out.println("=======================================");
    }

    /**
     * Marks a task as done.
     * @param doneEntry input for the task to be marked as done.
     * @throws DukeException if the task number inputted is invalid.
     */
    public static void done(String doneEntry) throws DukeException {
        int taskNumber = Integer.parseInt(doneEntry.substring(5,6));
        if (taskNumber > TaskList.noOfTasks()) {
            throw new DukeException("Sorry ☹, please enter a valid task to complete!");
        }
        duke.Task doneTask = TaskList.getCurrentTask(taskNumber - 1);
        doneTask.markAsDone();
        print("Congratulations on finishing this task!\n [X] " + doneTask.getDescription());
    }

    /**
     * Throws an exception when the User enters an invalid input.
     * @throws DukeException if the input entered by the user is invalid.
     */
    public static void invalidInput() throws DukeException {
        throw new DukeException("Sorry ☹, please enter a valid command!");
    }

    /**
     * Prints out the list of tasks that the User currently has.
     */
    public static void list() {
        int len = TaskList.noOfTasks();
        String sentence = "";
        for (int i = 1; i < len + 1; i++) {
            duke.Task currentTask = TaskList.getCurrentTask(i - 1);
            sentence = sentence + i + "." + currentTask.toString() + "\n";
        }
        print(sentence);
    }
}
