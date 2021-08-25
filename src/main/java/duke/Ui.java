package duke;

import java.util.Scanner;

import duke.task.Task;

public class Ui {
    Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void showLoadingError() {
        formatPrint("Loading Error");
    }

    public void showError(String errorMessage) {
        formatPrint(errorMessage);
    }

    public void showWelcome() {
        String greeting = "Howdy, pardner!";
        String question = "Is there anything old Vic can do you for?";
        formatPrint(greeting, question);
    }

    public void showFarewell() {
        String bye = "See ya round, buckaroo.";
        formatPrint(bye);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void formatPrint(String... lines) {
        System.out.println("\n    ____________________________________________________________");
        for (String line : lines) {
            System.out.printf("     %s\n", line);
        }
        System.out.println("    ____________________________________________________________\n");
    }

    public void printList(TaskList tasks) {
        int count = 1;
        System.out.print("\n    ____________________________________________________________" +
                "\n    Here are the tasks in your list:\n");
        for (Task task : tasks.getList()) {
            System.out.printf("     %d.%s\n", count, task.toString());
            count++;
        }
        System.out.println("    ____________________________________________________________\n");
    }
}
