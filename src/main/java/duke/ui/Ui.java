package duke.ui;

import java.util.Scanner;

public class Ui {
    private final static String lineBreak = "========================================================================";
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void intro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public void printLineBreak() {
        System.out.println(lineBreak);
    }

    public void greet() {
        System.out.println("Hello! I'm Duke" + '\n' + "What can I do for you?");
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
