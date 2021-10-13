package bobbybot.util;

/**
 * Encapsulates all user-facing interactions
 */
public class Ui {


    public void showLoadingError() {
        System.out.println("Could not load data from past memory");
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Show welcome message
     */
    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Bobby\nWhat can I do for you?");
        showLine();
    }

    /**
     * Say Bye and close program
     */
    public void sayBye() {
        System.out.println("Bye. Hope to see you again soon!\n");
        System.exit(1);
    }
}
