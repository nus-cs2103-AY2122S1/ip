package duke;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;

/**
 * This class prints messages for the user
 */
public class Ui {
    private static final String DIV_LINE = "    __________________________________________________"
           + "__________________________";
    private static final String BYE_OUTPUT = "Bye. Hope to see you again soon!";
    private static final String GREET_OUTPUT = "Hello! I'm Duke. What can I do for you?";
    private static final String INDENT = "    ";
    private Scanner sc = new Scanner(System.in);

    public String readCommand() {
        return sc.nextLine();
    }

    public void showError(DukeException e) {
        echo(e.toString());
    }

    public void echo(String input) {
        System.out.println(DIV_LINE + "\n" + INDENT + input + "\n" + DIV_LINE);
    }

    public void exit() {
        echo(BYE_OUTPUT);
    }

    public void greet() {
        echo(GREET_OUTPUT);
    }

    /**
     * Method to print tasks.
     * @param tasks the list of tasks to be printed
     * @param heading The heading to precede the list of tasks
     */
    public void printAll(ArrayList<Task> tasks, String heading) {
        System.out.println(DIV_LINE);
        System.out.println(INDENT + heading);
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(INDENT + i + " " + tasks.get(i - 1));
        }
        System.out.println(DIV_LINE);
    }
}
