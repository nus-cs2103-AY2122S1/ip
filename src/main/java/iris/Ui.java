package iris;

import java.util.Scanner;

/**
 * Encapsulates UI-related functionality of Iris
 */
public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Gets Iris to say a message
     * 
     * @param message message to say
     */
    public void say(String message) {
        say(message, true);
    }

    /**
     * Gets Iris to say a message
     *
     * @param message message to say
     * @param isFirst say Iris's name only if first message
     */
    public void say(String message, boolean isFirst) {
        String name = isFirst ? "Iris:" : "     ";
        System.out.printf("%s %s%n", name, message);
    }

    public void sayHello() {
        say("Hello! I'm Iris. What can I do for you?");
    }

    public void sayGoodbye() {
        say("Bye. Hope to see you again soon!");
    }

    /**
     * Gets Iris to notify user about error
     *
     * @param exception exception to convey to user
     */
    public void sayError(IrisException exception) {
        say(exception.getMessage());
    }

    /**
     * Prompts user for a command
     *
     * @return user's command
     */
    public String prompt() {
        System.out.print("me: ");
        return scanner.nextLine();
    }
}
