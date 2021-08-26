package duke;

/**
 * Encapsulates methods needed for Duke to interact with the user through printing
 * messages to the console.
 */
public class Ui {
    private static final String introString = "Hey there! I'm Good duke.Duke. How many I help you today?";
    private static final String outroString = "That was an excellent chat - I look forward to seeing you again soon!";
    private static final String readSaveString = "Sorry, there was a problem reading the save file :(";
    private static final String writeSaveString = "Sorry, there was a problem saving your tasks :(";
    private static final String newSaveString = "Empty save file detected - loading a blank list.";

    private String addedString(Task task, int size) {
        return String.format("Alright, I've added this task: \n\t%s\nNow, you have %d tasks in the list.\n", task, size);
    }

    private String doneString(Task task) {
        return String.format("Certainly, I've marked this task as done: \n\t%s\n", task);
    }

    private String deletedString(Task task, int size) {
        return String.format("Certainly, I've deleted this task: \n\t%s\nNow, you have %d tasks in the list.\n", task, size);
    }

    /**
     * Returns a string representation of the tasks in the list.
     * @param taskList Duke's list of tasks
     * @return String representation of list
     */
    public static String taskListString(TaskList taskList) {
        String output = "";
        for (int i = 0; i < taskList.size(); i++) {
            output += String.format("%d. %s\n", i + 1, taskList.get(i));
        }
        return output;
    }

    /**
     * Prints a message to the console, bounded by dashed horizontal lines
     * before and after the message.
     *
     * @param str Message to be displayed
     */
    public void print(String str) {
        String horizontalLine = "________________________________________________________________________________";
        System.out.println(horizontalLine);
        System.out.println(str);
        System.out.println(horizontalLine);
    }

    /**
     * Prints the intro message.
     */
    public void showIntro() {
        print(introString);
    }

    /**
     * Prints the outro message.
     */
    public void showOutro() {
        print(outroString);
    }

    /**
     * Prints a confirmation of the task that has been added and the number of
     * tasks being tracked so far.
     *
     * @param task Task that has been added to list
     * @param size Number of tasks in list
     */
    public void showAdded(Task task, int size) {
        print(addedString(task, size));
    }

    /**
     * Prints a message to inform the user that a task has been marked as having
     * been completed.
     *
     * @param task Task that has been marked as done
     */
    public void showDone(Task task) {
        print(doneString(task));
    }

    /**
     * Prints a message to inform the user that a task has been deleted from the
     * list, along with the number of tasks remaining.
     *
     * @param task Task that has been deleted from list
     * @param size Number of remaining tasks
     */
    public void showDeleted(Task task, int size) {
        print(deletedString(task, size));
    }

    /**
     * Prints the list of tasks to the console.
     *
     * @param tasks List of all the tasks
     */
    public void showTasks(TaskList tasks) {
        print(taskListString(tasks));
    }

    /**
     * Prints a message to inform the user about an error in reading the save.
     */
    public void showReadSaveError() {
        print(readSaveString);
    }

    /**
     * Prints a message to inform the user about an error in writing the save.
     */
    public void showWriteSaveError() {
        print(writeSaveString);
    }

    /**
     * Prints a message to inform the user that a new save is necessary.
     */
    public void showNewSave() {
        print(newSaveString);
    }
}
