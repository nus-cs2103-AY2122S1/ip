package duke;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;

/**
 * Ui class to print lines and intake inputs from user.
 */
public class Ui {
    //for division
    private static final String IND = "    ";
    //for sentences
    private static final String IND_2 = "     ";
    private static final String DIV = "";
    private static MainWindow mainWindow = null;
    private final Scanner sc = new Scanner(System.in);

    /**
     * Checks whether sc still has next line of input
     *
     * @return boolean
     */
    public boolean hasNext() {
        return sc.hasNext();
    }

    /**
     * Returns next line in input
     *
     * @return next line as String
     */
    public String getNext() {
        return sc.nextLine();
    }

    /**
     * Prints a sentence in desirable format
     *
     * @param s takes in the content
     */
    public static void printDuke(String s) {
        if (mainWindow != null) {
            mainWindow.sendDukeResponse(DIV + "\n" + IND_2 + s + "\n" + DIV + "\n");
        }
    }

    /**
     * Prints an error message
     *
     * @param e takes in the exception
     */
    public static void showError(DukeException e) {
        if (mainWindow != null) {
            mainWindow.sendDukeResponse(e.toString());
        }
    }

    /**
     * Prints a list of tasks
     *
     * @param taskList takes in a list of tasks
     */
    public static void printTasks(ArrayList<Task> taskList) {
        String tasks = "";
        int i = 1;
        for (Task task : taskList) {
            tasks += IND_2 + i + ". " + task + "\n";
            i++;
        }
        if (mainWindow != null) {
            mainWindow.sendDukeResponse(tasks);
        }
    }

    /**
     * Prints message after deleting a task.
     *
     * @param deleted task deleted
     * @param total   total number of tasks in the list
     * @param sOrNot  check use "task" or "tasks" in the sentence.
     */
    public static void sayDelete(Task deleted, int total, String sOrNot) {
        String s = DIV + "\n" + IND_2 + "Noted. I've removed this task: " + "\n"
                + IND_2 + " " + deleted + "\n"
                + IND_2 + "Now you have " + total + " " + sOrNot + " in the list." + "\n"
                + DIV + "\n";
        if (mainWindow != null) {
            mainWindow.sendDukeResponse(s);
        }
    }

    /**
     * Prints message after adding a task.
     *
     * @param t      task added
     * @param total  total number of tasks in the list
     * @param sOrNot check use "task" or "tasks" in the sentence.
     */
    public static void sayAdd(Task t, int total, String sOrNot) {
        String s = DIV + "\n" + IND_2 + "Got it. I've added this task: " + "\n"
                + IND_2 + " " + t + "\n"
                + IND_2 + "Now you have " + total + " " + sOrNot + " in the list." + "\n"
                + DIV + "\n";

        if (mainWindow != null) {
            mainWindow.sendDukeResponse(s);
        }
    }

    /**
     * Prints message after finishing a task
     *
     * @param p takes in the String of the task
     */
    public static void sayDone(String p) {
        String s = DIV + "\n" + IND_2 + "Nice! I've marked this task as done: " + "\n"
                + IND_2 + IND_2 + p + "\n" + DIV + "\n";

        if (mainWindow != null) {
            mainWindow.sendDukeResponse(s);
        }
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        greeting();
    }

    private static void greeting() {
        String g = "Hello! I'm Duke";
        String g2 = "What can I do for you?";
        Ui.printDuke(g + "\n" + IND_2 + g2);
    }

    /**
     * Prints the goodbye message.
     */
    public static void sayBye() {
        Ui.printDuke("Bye. Hope to see you again soon!");
        System.exit(0);
    }
}
