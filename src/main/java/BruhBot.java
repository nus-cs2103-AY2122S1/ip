import java.util.Scanner;

public class BruhBot {
    /** The chatbot's logo. */
    private static final String LOGO = "  /$$                           /$$      \n"
            + " | $$                          | $$      \n" + " | $$$$$$$   /$$$$$$  /$$   /$$| $$$$$$$ \n"
            + " | $$__  $$ /$$__  $$| $$  | $$| $$__  $$\n" + " | $$  \\ $$| $$  \\__/| $$  | $$| $$  \\ $$\n"
            + " | $$  | $$| $$      | $$  | $$| $$  | $$\n" + " | $$$$$$$/| $$      |  $$$$$$/| $$  | $$\n"
            + " |_______/ |__/       \\______/ |__/  |__/\n";

    /** The greeting message displayed when the bot is initialized. */
    private static final String GREETING = String.format("What can\n\n%s\ndo for you today?\n", LOGO);

    /** The farewell message displayed when the bot is exited. */
    private static final String FAREWELL = "Bye. Hope to see you again soon!\n";

    /** The command which stops the bot. */
    private static final String STOP_SIGNAL = "bye";

    private Scanner userInputScanner = new Scanner(System.in);
    private TaskCenter taskCenter = new TaskCenter();

    /**
     * Initializes the chatbot.
     */
    public void init() {
        System.out.println(GREETING);
        listen();
    }

    /**
     * Performs cleanup tasks before exiting the bot.
     */
    private void shutdown() {
        userInputScanner.close();
    }

    /**
     * Begin listening for user input. Terminates when the user says 'bye'.
     */
    public void listen() {
        String userInput = "";
        while (true) {
            if (userInputScanner.hasNextLine()) {
                userInput = userInputScanner.nextLine();
                respond(userInput);
            }
            boolean isStopCommand = userInput.equals(STOP_SIGNAL);
            if (isStopCommand) {
                break;
            }
        }
        shutdown();
    }

    private void respond(String input) {
        String formattedResponse = formatResponse(processInput(input));
        System.out.println(formattedResponse);
    }

    /**
     * Processes the user's input by calling the appropriate method & returning the
     * resulting response.
     * 
     * @param input The input entered by the user.
     * @return The response to be displayed.
     */
    private String processInput(String input) {
        try {
            if (input.isEmpty()) {
                throw new InvalidArgumentException();
            } else if (input.equals("bye")) {
                return FAREWELL;
            } else {
                TaskOperation operation = taskCenter.createTaskOperation(input);
                operation.execute();
                return operation.getDescription();
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return "Please specify a valid task number (use 'list' to view your tasks).\n";
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Checks for missing argument in the user input.
     * 
     * @param sections     The user's entered arguments, divided into relevant
     *                     sections.
     * @param errorMessage The error message to be displayed, in the case of missing
     *                     arguments.
     * @throws MissingArgumentException if missing arguments are detected.
     */

    private String formatResponse(String content) {
        final String divider = "____________________________________________________________\n";
        content = divider + content.replaceAll("(?m)^", " ") + divider;
        return content.replaceAll("(?m)^", "    ");
    }
}
