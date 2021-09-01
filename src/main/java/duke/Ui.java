package duke;

import java.util.Scanner;

public class Ui {
    private static String line = "*･゜ﾟ･*･゜ﾟ･*:.｡..｡.:*･'(*ﾟ▽ﾟ*)'･*:.｡. .｡.:*･゜ﾟ･*゜ﾟ･*";
    private static Scanner sc = new Scanner(System.in);

    public static String format(String msg) {
        return msg + "\n\n" + line;
    }

    public String readCommand() {
        return sc.nextLine();
    }


    public void showWelcome() {
        System.out.println(format("Hello! I'm Duke\nWhat can I do for you?"));
    }

    public void displayGoodbyeMsg() {
        System.out.println(format("See you next time!"));
    }

    public void displayErrorMsg(DukeException e) {
        System.out.println(format(e + "\n\nWhat else can I do for you?"));
    }

    public String formatNumTasks(int size) {
        return size == 0 ? "no tasks"
                : size == 1 ? "1 task"
                : size + " tasks";
    }
}
