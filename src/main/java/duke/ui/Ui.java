package duke.ui;

import java.util.Scanner;

/**
 * Class that handles user interactions.
 */
public class Ui implements UiInterface {

    private final Scanner sc;

    /**
     * Constructs a Ui instance.
     */
    public Ui () {
        this.sc = new Scanner(System.in);
    }

    private static String wrapOutput(String s) {
        // Align list items properly
        // Adapted regex from https://stackoverflow.com/questions/15888934/how-to-indent-a-multi-line-paragraph-being-written-to-the-console-in-java

        String replaced = s.replaceAll("(?m)^", "\t\t\t ");
        return "\n\t@Herb:~$" + replaced.substring(3) + "\n";
    }

    /**
     * Shows the welcome message.
     *
     * @param welcomeMessage String welcome message
     */
    public void showWelcome(String welcomeMessage) {
        System.out.println(wrapOutput(welcomeMessage));
    }

    /**
     * Shows end message.
     *
     * @param endMessage String end message
     */
    public void showBye(String endMessage) {
        System.out.println(endMessage);
    }

    /**
     * Shows error in loading initial file.
     *
     * @param error String error message
     */
    public void showLoadingError(String error) {
        System.out.println(error);
    }

    /**
     * Shows error messages.
     *
     * @param error String error message
     */
    public void showError(String error) {
        System.out.println(wrapOutput(error));
    }

    /**
     * Shows replies to user inputs.
     *
     * @param message String message to be shown
     */
    public void print(String message) {
        System.out.println(wrapOutput(message));
    }

    /**
     * Reads user input in.
     *
     * @return User input as string
     */
    public String readCommand() {
        System.out.print("--> ");
        return this.sc.nextLine();
    }

}


