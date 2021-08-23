import commands.Command;
import commands.DukeException;
import tasks.TaskList;

import java.util.Scanner;

public class Ui {
    private static final String LINE = "\n------------------------------------------\n";
    private static final String EXIT_KEYWORD = "bye";

    public static void print(String s) {
        System.out.print(LINE + s + LINE);
    }

    public static String getInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public static void printStartDisplay() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "\n------------------------------------------\n";
        String startGreeting = LINE + "Hello! I'm Duke\n"
                + "What can I do for you?" + LINE;
        System.out.print("Hello from\n" + logo + startGreeting);
    }

    public static void printEndDisplay() {
        String endGreeting = "Bye. Hope to see you again soon!";
        print(endGreeting);
    }
}
