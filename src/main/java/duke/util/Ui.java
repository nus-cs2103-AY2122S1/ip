package duke.util;

import java.util.Scanner;

/**
 * Ui class to encapsulate display messages and handle input/output of duke.
 */
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
    
    public static String displayError(String error) {
        return error;
    }

    public void exit() {
        sc.close();
        bye();
    }
    

    public static String greet() {
        return "Hiiii~ I'm Duke! Just so you know I'm not a real human...\n" +
                "I'm created by Tianyue to assist you in simple task management.\n" +
                "How can I help you? :)";
    }

    public static String bye() {
        return "Bye. Hope to see you again soon!";
    }


}
