package duke;

import duke.task.Task;

import java.util.Scanner;

public class Ui {
    final private static String strBreak = "    ____________________________________________________________\n";

    public void showWelcome() {
        System.out.println("Urgh I hate having to wake up. Why did you do that");
    }

    public void taskAdded(Task task, int count) {
        String toPrint = String.format("     Got it. I've added this task:\n     "
                        + "%s\n     Now you have %x task%s in the list.",
                task.toString(), count, count > 1 ? "s" : "");
        System.out.println(strBreak + toPrint + "\n" + strBreak);
    }

    public void sayBye() {
        System.out.println("Don't wake me up again");
    }

    public void showMarkDone(Task task) {
        System.out.println("Toight!\n" + task + " marked done.");
    }

    public void deleteTask(Task task) {
        System.out.println(task + " deleted. Bruh be more careful next time");
    }

    public void printList(Tasklist tasks) {
        System.out.println(tasks);
    }

    public void showError(Exception e) {
        System.out.println(e.getMessage());
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
