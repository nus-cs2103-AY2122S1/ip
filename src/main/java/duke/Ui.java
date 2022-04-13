package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
     * Prints a warning message on screen
     *
     * @param s string to be printed
     */
    public static void printWarning(String s) {
        if (mainWindow != null) {
            mainWindow.sendDukeWarning(s);
        }
    }

    /**
     * Prints an error message
     *
     * @param e takes in the exception
     */
    public static void showError(DukeException e) {
        if (mainWindow != null) {
            mainWindow.sendDukeWarning(e.toString());
        }
    }

    /**
     * Prints a list of tasks
     *
     * @param taskList takes in a list of tasks
     */
    public static void printTasks(ArrayList<Task> taskList) {
        Stream<String> taskString = taskList.stream().map(task -> {
            int position = taskList.indexOf(task) + 1;
            return IND_2 + position + ". " + task + "\n";
        });
        String tasks = "";
        List<String> stringList = taskString.collect(Collectors.toList());
        for (String s: stringList) {
            tasks += s;
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
        String s = "Noted. I've removed this task: " + "\n"
                + IND_2 + " " + deleted + "\n"
                + "Now you have " + total + " " + sOrNot + " in the list." + "\n";
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
        String s = "Got it. I've added this task: " + "\n"
                + IND_2 + " " + t + "\n"
                + "Now you have " + total + " " + sOrNot + " in the list." + "\n";

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
        String s = "Nice! I've marked this task as done: " + "\n"
                + p + "\n" + DIV + "\n";

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
        String g2 = "Here are the tasks to be finished today: ";
        if (mainWindow == null) {
            Ui.printDuke(g + "\n" + IND_2 + g2);
            return;
        }
        try {
            ArrayList<Task> tasks = TaskList.getOnADay("today");
            String taskString = "";
            for (Task t: tasks) {
                taskString += t + "\n";
            }
            Ui.printDuke(g + "\n" + IND_2 + g2 + "\n" + taskString);
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints the goodbye message.
     */
    public static void sayBye() {
        Ui.printDuke("Bye. Hope to see you again soon!");
        System.exit(0);
    }
}
