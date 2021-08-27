package duke;

import java.util.Scanner;

public class Ui {
    private final String divider = "________________________________";
    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println(divider);
    }

    public void reply(String input) {
        System.out.println(divider);
        System.out.println(input);
        System.out.println(divider);
    }

    public void welcome() {
        reply("Hello i is Duke\nWhat u want?");
    }

    public String readCommand() {
        return scanner.hasNext() ? scanner.nextLine() : "bye";
    }

    public void exit() {
        System.out.println("i zao first");
    }

    public void displayList(TaskList tasks) {
        for (int i = 0; i < tasks.length(); i++) {
            Task task = tasks.get(i);
            System.out.println((i + 1) + "." + task.toString());
        }
    }

    public void done(Task completedTask) {
        System.out.println("noice this thing done:\n" + completedTask);
    }

    public void showLoadingError(DukeException e) {
        System.out.println(e.getMessage());
    }

    public void add(Task task, int length) {
        System.out.println("one more thing: " + task.toString() + "\nNow you got " + length + " thing(s). sian");
    }

    public void delete(Task task, int length) {
        System.out.println("this one no more liao ah :\n" + task.toString()
                + "\nNow you got " + length + " thing(s). sian");
    }
}
