package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner sc = new Scanner(System.in);
    private static String bye_output = "Bye. Hope to see you again soon!";
    private static String indent = "    ";
    private static String div_line = "    ____________________________________________________________";

    public String readCommand() {
        return sc.nextLine();
    }

    public void showError(DukeException e) {
        echo(e.toString());
    }

    public void echo(String next_input) {
        System.out.println(div_line + "\n" + indent + next_input + "\n" + div_line);
    }

    public void exit() {
        echo(bye_output);
    }

    public void greet() {
        String line1 = "Hello! I'm duke.Duke\n";
        String line2 = indent + "What can I do for you?";
        echo(line1 + line2);
    }

    public void printAll(ArrayList<Task> tasks) {
        System.out.println(div_line);
        System.out.println(indent + "Here are the tasks in your list:");
        for (int i = 1; i < tasks.size() + 1; i++) {
            System.out.println(indent + i + " " + tasks.get(i - 1));
        }
        System.out.println(div_line);
    }
}
