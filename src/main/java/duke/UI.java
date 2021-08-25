package duke;

import duke.task.Task;

import java.util.Scanner;

/**
 * Class to handle the user interface of Duke.
 */
public class UI {
    final private static String strBreak = "    ____________________________________________________________\n";

    /**
     * Prints out sentence welcoming user when Duke starts.
     */
    public void showWelcome() {
        System.out.println("Urgh I hate having to wake up. Why did you do that");
    }

    /**
     * Prints out confirmation when user adds a task.
     *
     * @param task the task which is added.
     * @param count the current number of tasks.
     */
    public void taskAdded(Task task, int count) {
        String toPrint = String.format("     Got it. I've added this task:\n     %s\n     Now you have %x task%s in the list.",
                task.toString(), count, count > 1 ? "s" : "");
        System.out.println(strBreak + toPrint + "\n" + strBreak);
    }

    /**
     * Prints out sentence saying bye when user exits.
     */
    public void sayBye() {
        System.out.println("Don't wake me up again");
    }

    /**
     * Prints out sentence confirming when user marks a task as done.
     *
     * @param task task that is marked done.
     */
    public void showMarkDone(Task task) {
        System.out.println("Toight!\n" + task + " marked done.");
    }

    /**
     * Prints our sentence confirming when user deletes a task.
     *
     * @param task task that is deleted.
     */
    public void deleteTask(Task task) {
        System.out.println(task + " deleted. Bruh be more careful next time");
    }

    /**
     * Prints out the list of current tasks.
     *
     * @param tasks the current list of tasks.
     */
    public void printList(Tasklist tasks) {
        System.out.println(tasks);
    }

    /**
     * Prints out the error message of exception that was thrown.
     *
     * @param e the exception that was thrown.
     */
    public void showError(Exception e) {
        System.out.println(e.getMessage());
    }

    /**
     * To read the command that was input by user.
     *
     * @return the command input that was input by user.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
