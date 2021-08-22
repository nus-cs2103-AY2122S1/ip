package botto.util;

import botto.task.Task;

import java.util.List;
import java.util.Scanner;

public class Ui {
    private final String BOT_NAME = "Botto";
    private final String INDENTATION = "    ";

    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println(INDENTATION + "------------------------------");
    }

    public void showWelcome() {
        showLine();

        String greet = INDENTATION + "Hello! I'm " + BOT_NAME + ".\n"
                + INDENTATION + "What can I do for you?";
        System.out.println(greet);

        showLine();
    }

    public void showTasks(List<Task> list, String header) {
        System.out.println(INDENTATION + header);
        for(int i = 0; i < list.size(); i ++) {
            Task task = list.get(i);
            System.out.println(INDENTATION + (i + 1) + ". " + task);
        }
    }

    public void respondAdd(Task task, int size) {
        String response = INDENTATION + "Got it! I've added this task:\n"
                + INDENTATION + "  " + task + "\n"
                + INDENTATION + "Now you have " + size + " tasks in the list.";

        System.out.println(response);
    }

    public void respondDelete(Task task, int size) {
        String response = INDENTATION +  "Noted. I've removed this task:\n"
                + INDENTATION + "  " + task + "\n"
                + INDENTATION + "Now you have " + size + " tasks in the list.";

        System.out.println(response);
    }

    public void respondDone(Task task) {
        String response = INDENTATION + "Nice! I've marked this task as done:\n"
                + INDENTATION + "  " + task;

        System.out.println(response);;
    }

    public void sayGoodBye() {
        System.out.println(INDENTATION + "Bye. Hope to see you again soon!");
    }

    public void showError(String message) {
        System.out.println(INDENTATION + message);
    }

}
