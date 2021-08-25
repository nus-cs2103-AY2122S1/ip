package duke;

import java.util.Scanner;

public class Ui {
    private static String DIVIDER = "____________________________________________________________";

    public static void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Duke: Hello from\n" + logo);

        System.out.println(DIVIDER);
        System.out.println("Duke: Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(DIVIDER);
    }

    public static void showLoadingError() {
        System.out.println("LOADING ERROR");
    }

}
