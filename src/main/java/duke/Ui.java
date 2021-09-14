package duke;

import java.util.Base64;
import java.util.Scanner;

public class Ui {
    private static String line =
            "*･゜ﾟ･*･゜ﾟ･*:.｡..｡.:*･'(*ﾟ▽ﾟ*)'･*:.｡. .｡.:*･゜ﾟ･*゜ﾟ･*";
    private static String welcomeMsg = "Hello! I'm Duke\nSay 'help' to see how you can use me.";
    private static String goodbyeMsg = "See you next time!";
    private static String secretCmdKey = new String(Base64.getDecoder().decode("aGVhZHBhdA=="));
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

    public String getSecretCmdKey() {
        return secretCmdKey;
    }

    public String getHpMsg(int hpCount) {
        if (hpCount == 1) {
            return String.format("1 %s given", secretCmdKey);
        }
        return String.format("%d %ss given", hpCount, secretCmdKey);
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
