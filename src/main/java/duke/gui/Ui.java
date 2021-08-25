package duke.gui;

import java.util.Scanner;

public class Ui {
    private static Scanner sc = new Scanner(System.in);

//    public Ui() {
//        sc = new Scanner(System.in);
//    }

    public static void greet() {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        Ui.displayLine();
    }

    public static void sayBye() {
        displayLine();
        System.out.println("Bye. Hope to see you again soon!");
        displayLine();
    }

    public static void formatAndPrint(String output) {
        displayLine();
        System.out.println(output);
        displayLine();
    }

    public static void displayLine() {
        System.out.println("____________________________________________________________");
    }

    public static String readInput() {
        return sc.nextLine();
    }

    public static void showStorageFileNotFoundError(String message) {
        System.out.println("Storage file not found: " + message);
        System.out.println("Task List will be initialized to empty state.");
        System.out.println("Duke.txt will be created for you once you add tasks to the list.\n");
    }

}
