package duke.ui;

import duke.command.Command;
import duke.exception.MissingArgumentException;

import java.util.Scanner;

/**
 * Encapsulates the user interface function of Bruh.
 */
public class Ui {
    private static final String LOGO = "  /$$                           /$$      \n"
            + " | $$                          | $$      \n" + " | $$$$$$$   /$$$$$$  /$$   /$$| $$$$$$$ \n"
            + " | $$__  $$ /$$__  $$| $$  | $$| $$__  $$\n" + " | $$  \\ $$| $$  \\__/| $$  | $$| $$  \\ $$\n"
            + " | $$  | $$| $$      | $$  | $$| $$  | $$\n" + " | $$$$$$$/| $$      |  $$$$$$/| $$  | $$\n"
            + " |_______/ |__/       \\______/ |__/  |__/\n";
    private final Scanner userInputScanner = new Scanner(System.in);

    /**
     * Reads a line of user input & returns it as a string.
     *
     * @return A string of one line of user input.
     * @throws MissingArgumentException if user input is missing.
     */
    public String readUserInput() throws MissingArgumentException {
        if (userInputScanner.hasNextLine()) {
            return userInputScanner.nextLine();
        }
        throw new MissingArgumentException("Please enter a valid command.");
    }

    /**
     * Shows the greeting message upon startup.
     */
    public void showGreeting() {
        String greeting = String.format("What can\n\n%s\ndo for you today?\n", LOGO);
        System.out.println(greeting);
    }

    /**
     * Shows the description of a specified command.
     *
     * @param command The command to be shown.
     */
    public void showCommandDescription(Command command) {
        String formattedDescription = formatContent(command.getDescription());
        System.out.println(formattedDescription);
    }

    /**
     * Shows an error message.
     *
     * @param errorMessage The error message to be displayed.
     */
    public void showErrorMessage(String errorMessage) {
        System.out.println(formatContent(errorMessage));
    }

    /**
     * Formats the specified content appropriately to be displayed,
     * by adding dividers & indentation.
     *
     * @param content The content to be formatted.
     */
    private String formatContent(String content) {
        final String divider = "____________________________________________________________\n";
        content = divider + content.replaceAll("(?m)^", " ") + '\n' + divider;
        return content.replaceAll("(?m)^", "    ");
    }
}
