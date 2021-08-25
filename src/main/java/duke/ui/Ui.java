package duke.ui;

import duke.command.Command;
import duke.exception.MissingArgumentException;

import java.util.Scanner;

public class Ui {
    private static final String LOGO = "  /$$                           /$$      \n"
            + " | $$                          | $$      \n" + " | $$$$$$$   /$$$$$$  /$$   /$$| $$$$$$$ \n"
            + " | $$__  $$ /$$__  $$| $$  | $$| $$__  $$\n" + " | $$  \\ $$| $$  \\__/| $$  | $$| $$  \\ $$\n"
            + " | $$  | $$| $$      | $$  | $$| $$  | $$\n" + " | $$$$$$$/| $$      |  $$$$$$/| $$  | $$\n"
            + " |_______/ |__/       \\______/ |__/  |__/\n";
    private final Scanner userInputScanner = new Scanner(System.in);

    public String readUserInput() throws MissingArgumentException {
        if (userInputScanner.hasNextLine()) {
            return userInputScanner.nextLine();
        }
        throw new MissingArgumentException("Please enter a valid command.");
    }

    public void showGreeting() {
        String greeting = String.format("What can\n\n%s\ndo for you today?\n", LOGO);
        System.out.println(greeting);
    }

    public void showCommandDescription(Command command) {
        String formattedDescription = formatContent(command.getDescription());
        System.out.println(formattedDescription);
    }

    public void showErrorMessage(String errorMessage) {
        System.out.println(formatContent(errorMessage));
    }

    /**
     * Checks for missing argument in the user input.
     *
     * @param content The user's entered arguments, divided into relevant
     *                sections.
     */
    private String formatContent(String content) {
        final String divider = "____________________________________________________________\n";
        content = divider + content.replaceAll("(?m)^", " ") + '\n' + divider;
        return content.replaceAll("(?m)^", "    ");
    }
}
