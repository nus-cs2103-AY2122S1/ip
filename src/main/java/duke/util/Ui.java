package duke.util;

import java.util.Scanner;
public class Ui {
    private final Scanner sc;
    /**
     * Constructor method of Deadline.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    public String readNextLine() {
        return sc.nextLine().trim();
    }
    
    public static void displayError(String error) {
        System.err.println(error);
    }

    public void exit() {
        sc.close();
        bye();
    }

    public void showLine() {
        System.out.println("-----------------------------------------");
    }

    public static void greet() {
        System.out.println("Hiiii~ I'm Duke created by Tianyue.\n" +
                "How can I help you? :)");
    }

    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }


}
