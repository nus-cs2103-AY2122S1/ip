package cs2103t.duke.ui;

import java.util.Scanner;

public class Ui {

    public static String LOGO = "      ____        _        \n"
            + "     |  _ \\ _   _| | _____ \n"
            + "     | | | | | | | |/ / _ \\\n"
            + "     | |_| | |_| |   <  __/\n"
            + "     |____/ \\__,_|_|\\_\\___|\n";
    public static String DIVIDER = "    ____________________________________________________________";
    public static String SPACE = "     ";
    public static String INVALID_INPUT = SPACE + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";

    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void greet() {
        System.out.println(DIVIDER);
        System.out.println(LOGO);
        System.out.println(SPACE + "Hello! I'm Duke\n" + SPACE + "What can I do for you?");
        System.out.println(DIVIDER + "\n");
    }

    public void dismiss() {
        displayText(SPACE + "Bye. Hope to see you again soon!");
    }

    public String readLine() {
        return scanner.nextLine();
    }

    public void showError(String message) {
        displayText(message);
    }

    public void displayText(String text) {
        System.out.println(DIVIDER);
        System.out.println(text);
        System.out.println(DIVIDER + "\n");
    }

}
