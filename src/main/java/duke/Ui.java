package duke;

import java.util.Scanner;

/**
 * Class that handles user interactions.
 */
public class Ui implements UiInterface {
    
    private final Scanner sc;

    /**
     * Ui constructor.
     */
    public Ui () {
        this.sc = new Scanner(System.in);
    }

    private static String wrapOutput(String s) {
        // Align list items properly
        // Adapted regex from https://stackoverflow.com/questions/15888934/how-to-indent-a-multi-line-paragraph-being-written-to-the-console-in-java

        String mstr = s.replaceAll("(?m)^", "\t\t\t ");
        return "\n\t@Herb:~$" + mstr.substring(3) + "\n";
    }

    /**
     * Shows welcome message.
     */
    public void showWelcome(String welcomeMessage) {
        System.out.println(wrapOutput(welcomeMessage));
    }

    /**
     * Shows end message.
     */
    public void sayBye(String endMessage) {
        System.out.println(endMessage);
    }

    /**
     * Shows error in loading initial file.
     */
    public void showLoadingError(String error) {
        System.out.println(error);
    }

    /**
     * Shows error messages.
     */
    public void showError(String error) {
        System.out.println(wrapOutput(error));
    }

    /**
     * Shows replies to user inputs.
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


