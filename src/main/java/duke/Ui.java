package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class prints messages for the user
 */
public class Ui {
    private Scanner sc = new Scanner(System.in);
    private static final String BYE_OUTPUT = "Bye. Hope to see you again soon!";
    private static final String GREET_OUTPUT = "Hello! I'm Duke. What can I do for you?";
    private static final String INDENT = "    ";
    private static final String DIV_LINE = "    ____________________________________________________________________________";

    public String readCommand() {
        return sc.nextLine();
    }

    public void showError(DukeException e) {
        echo(e.toString());
    }

    public void echo(String next_input) {
        System.out.println(DIV_LINE + "\n" + INDENT + next_input + "\n" + DIV_LINE);
    }

    public void exit() {
        echo(BYE_OUTPUT);
    }

    public void greet() {
        echo(GREET_OUTPUT);
    }

    public void printAll(ArrayList<Task> tasks, String heading) {
        System.out.println(DIV_LINE);
        System.out.println(INDENT + heading);
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(INDENT + i + " " + tasks.get(i - 1));
        }
        System.out.println(DIV_LINE);
    }
}
