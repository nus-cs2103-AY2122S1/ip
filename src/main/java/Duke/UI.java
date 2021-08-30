package duke;

import java.util.Scanner;

/**
 * UI for Duke.
 */
public class UI {
    private final Scanner myScanner;

    public UI() {
        this.myScanner = new Scanner(System.in);
    }
    /**
     * Prints greeting text of the robotFriend.
     */
    public static String greet() {
        return "Hello I am your RobotFriend. How can i help you?";
    }


    /**
     * Prints the exiting text in a robot way.
     */
    public static String bye() {
        return "Bye~ Hope to see you soon :)";
    }


}
