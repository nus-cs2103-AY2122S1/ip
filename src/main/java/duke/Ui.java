package duke;

import java.util.Scanner;

/**
 * This represents the user interface that interacts with the user.
 */
public class Ui {

    private final Scanner reader;

    /**
     * This constructor creates a reader to read user input
     * and stores this reader.
     */
    public Ui() {
        reader = new Scanner(System.in);
    }

    /**
     * This method outputs a greeting message that is shown to the user.
     */
    public void showWelcome() {
        String welcomeMessage = "Hello! I'm duke.Duke\nWhat can I do for you?";
        System.out.println(welcomeMessage);
    }

    /**
     * This method outputs a given String text that is bordered above and below by lines.
     * @param text String that gets bordered.
     */
    public void stringWithDivider(String text) {
        System.out.println("_______________________________________________________________");
        System.out.println(text);
        System.out.println("_______________________________________________________________");
    }

    /**
     * This method uses the stored reader to read the user input that user typed in.
     * @return String that is what the user typed in.
     */
    public String readCommand() {
        return reader.nextLine();
    }
}
