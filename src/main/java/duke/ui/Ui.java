package duke.ui;

public class Ui {

    public Ui() {}

    /**
     * Prints out a greeting for the user when the bot is first ran.
     */

    public void greet() {
        System.out.println("__________________________________");
        System.out.println("Hello! I'm duke.Duke");
        System.out.println("What can I do for you?");
        System.out.println("__________________________________");
    }

    /**
     * Prints out a goodbye message when the bot is exited.
     */

    public void exit() {
        System.out.println("__________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("__________________________________");
    }

}
