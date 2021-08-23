import java.util.Scanner;

public class Ui {
    private Scanner userInputScanner = new Scanner(System.in);

    private static final String LOGO = "  /$$                           /$$      \n"
            + " | $$                          | $$      \n" + " | $$$$$$$   /$$$$$$  /$$   /$$| $$$$$$$ \n"
            + " | $$__  $$ /$$__  $$| $$  | $$| $$__  $$\n" + " | $$  \\ $$| $$  \\__/| $$  | $$| $$  \\ $$\n"
            + " | $$  | $$| $$      | $$  | $$| $$  | $$\n" + " | $$$$$$$/| $$      |  $$$$$$/| $$  | $$\n"
            + " |_______/ |__/       \\______/ |__/  |__/\n";

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
     * @param sections
     *            The user's entered arguments, divided into relevant sections.
     * @param errorMessage
     *            The error message to be displayed, in the case of missing
     *            arguments.
     * @throws MissingArgumentException
     *             if missing arguments are detected.
     */
    private String formatContent(String content) {
        final String divider = "____________________________________________________________\n";
        content = divider + content.replaceAll("(?m)^", " ") + '\n' + divider;
        return content.replaceAll("(?m)^", "    ");
    }
}
