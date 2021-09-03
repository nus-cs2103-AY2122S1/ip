package duke;

import java.util.Scanner;

public class Ui {
    private static String line =
            "*･゜ﾟ･*･゜ﾟ･*:.｡..｡.:*･'(*ﾟ▽ﾟ*)'･*:.｡. .｡.:*･゜ﾟ･*゜ﾟ･*";
    private static String welcomeMsg = "Hello! I'm Duke\nWhat can I do for you?";
    private static String goodbyeMsg = "See you next time!";
    private static Scanner sc = new Scanner(System.in);

    public static String format(String msg) {
        return msg + "\n\n" + line;
    }

    public String readCommand() {
        return sc.nextLine();
    }


    public String getWelcomeMsg() {
        return welcomeMsg;
    }

    public String getErrorMsg(DukeException e) {
        return e + "\nWhat else can I do for you?";
    }

    public String getGoodbyeMsg() {
        return goodbyeMsg;
    }

    /**
     * Formats the number of tasks as part of a display message.
     * @param size The number of tasks.
     * @return A String representation of the number of tasks.
     */
    public String formatNumTasks(int size) {
        return size == 0 ? "no tasks"
                : size == 1 ? "1 task"
                : size + " tasks";
    }
}
