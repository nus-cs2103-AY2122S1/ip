package duke;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner = new Scanner(System.in);
    public void say(String message) {
        System.out.printf("Iris: %s%n", message);
    }

    public void say(String message, boolean isFirst) {
        String name = isFirst ? "Iris:": "     ";
        System.out.printf("%s %s%n", name, message);
    }

    public void sayError(IrisException exception) {
        say(exception.getMessage());
    }

    public void sayTaskAdded(TaskList tasks) {
        int count = tasks.getCount();
        say("Got it. I've added this task:");
        say(tasks.get(count - 1).toString(), false);
        say(String.format("Now you have %d %s in the list.",
                count, count == 1 ? "task" : "tasks"), false);
    }

    public void listTasks(TaskList tasks) {
        for (int i = 0; i < tasks.getCount(); i++) {
            say(String.format("%d. %s", i + 1, tasks.get(i)), i == 0);
        }
    }

    public String prompt() {
        System.out.print("me: ");
        return scanner.nextLine();
    }
}
