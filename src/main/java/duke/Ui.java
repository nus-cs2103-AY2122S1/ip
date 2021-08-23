package duke;

import java.util.List;
import java.util.Scanner;

import duke.task.Task;

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

    public void sayTaskAdded(TaskList taskList) {
        int count = taskList.getCount();
        say("Got it. I've added this task:");
        say(taskList.get(count - 1).toString(), false);
        say(String.format("Now you have %d %s in the list.",
                count, count == 1 ? "task" : "tasks"), false);
    }

    public void listTasks(TaskList taskList) {
        for (int i = 0; i < taskList.getCount(); i++) {
            say(String.format("%d. %s", i + 1, taskList.get(i)), i == 0);
        }
    }

    public void listTasks(List<Task> taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            say(String.format("%d. %s", i + 1, taskList.get(i)), i == 0);
        }
    }

    public String prompt() {
        System.out.print("me: ");
        return scanner.nextLine();
    }
}
