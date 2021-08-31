package duke;

import java.util.Scanner;

/**
 * Class that handles user interactions.
 */
public class Ui {
    private static final String WELCOME_MESSAGE = "\n\tHi! I'm Herbert, you can call me Herb  ٩(˘◡˘)۶\n"
            + "\tHow can I help you?\n\n"
            + "\tYou can type:\n"
            + "\t\t `list` to get a list of tasks\n"
            + "\t\t `todo ${item}` to add a todo\n"
            + "\t\t `deadline ${item} /by ${time}` to add a deadline\n"
            + "\t\t `event ${item} /at ${time}` to add an event\n"
            + "\t\t `done ${i}` to mark task i as completed\n"
            + "\t\t `delete ${i}` to delete task i\n"
            + "\t\t `find ${keyword}` to find tasks by keyword\n"
            + "\t\t `bye` to end this chat\n";
    private static final String END_MESSAGE = "\n\tSad to see you go :(\n\t...shutting down...";
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
    public void showWelcome() {
        System.out.println(wrapOutput(WELCOME_MESSAGE));
    }

    /**
     * Shows end message.
     */
    public void sayBye() {
        System.out.println(END_MESSAGE);
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
