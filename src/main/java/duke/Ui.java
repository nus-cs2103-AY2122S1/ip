package duke;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;

/**
 * Ui class to print lines and intake inputs from user.
 */
public class Ui {
    //for division
    private static String ind = "    ";
    //for sentences
    private static String ind2 = "     ";
    private static String div = "";
    private static MainWindow mainWindow = null;
    private Scanner sc = new Scanner(System.in);

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
    public static void myPrint(String s) {
        if (mainWindow != null) {
            mainWindow.sendDukeResponse(div + "\n" + ind2 + s + "\n" + div + "\n");
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
            tasks += ind2 + i + ". " + task + "\n";
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
        String s = div + "\n" + ind2 + "Noted. I've removed this task: " + "\n"
                + ind2 + " " + deleted + "\n"
                + ind2 + "Now you have " + total + " " + sOrNot + " in the list." + "\n"
                + div + "\n";
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
        String s = div + "\n" + ind2 + "Got it. I've added this task: " + "\n"
                + ind2 + " " + t + "\n"
                + ind2 + "Now you have " + total + " " + sOrNot + " in the list." + "\n"
                + div + "\n";

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
        String s = div + "\n" + ind2 + "Nice! I've marked this task as done: " + "\n"
                + ind2 + ind2 + p + "\n" + div + "\n";

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
        Ui.myPrint(g + "\n" + ind2 + g2);
    }

    public static void sayBye() {
        Ui.myPrint("Bye. Hope to see you again soon!");
        System.exit(0);
    }
}
