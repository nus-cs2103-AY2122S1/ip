package duke;

import java.util.ArrayList;

public class UI {

    public static String addedText = "Got it. I've added this task:\n";

    /**
     * Handles the greeting or opening message that is shown to the user just before inputs are read.
     */
    public static void greet() {
        String greetText = "Hello I'm Duke\nWhat can I do for you?\n";
        System.out.print(greetText);
    }

    /**
     * Handles the exiting or halting of DukePakage.Duke when the user has given the appropriate input.
     */
    public void exit() {
        String exitText = "Bye. Hope to see you again soon!";
        System.out.println(exitText);
    }
}
