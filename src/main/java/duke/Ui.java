package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner sc = new Scanner(System.in);
    private static final String BYE_OUTPUT = "Bye. Hope to see you again soon!";
    private static final String INDENT = "    ";
    private static final String DIV_LINE = "    ____________________________________________________________";

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
        String line1 = "Hello! I'm duke.Duke\n";
        String line2 = INDENT + "What can I do for you?";
        echo(line1 + line2);
    }

    public void printAll(ArrayList<Task> tasks) {
        System.out.println(DIV_LINE);
        System.out.println(INDENT + "Here are the tasks in your list:");
        for (int i = 1; i < tasks.size() + 1; i++) {
            System.out.println(INDENT + i + " " + tasks.get(i - 1));
        }
        System.out.println(DIV_LINE);
    }
}
