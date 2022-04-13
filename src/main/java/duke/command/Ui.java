package duke.command;

import java.util.Scanner;
/**
 * This class handles the interactions with the user.
 */
public class Ui {
    private static Scanner sc;
    private final String botName;
    private final Parser parser;

    /**
     * Constructor to create a new UI.
     *
     * @param name The name of the bot.
     */
    public Ui(String name) {
        assert name.length() > 0 : "Name should not be empty!";
        this.botName = name;
        sc = new Scanner(System.in);
        this.parser = new Parser();
    }

    /**
     * Returns initial start up message.
     *
     * @return The String representing the welcome message.
     */
    public String getWelcome() {
        return String.format("Greetings! This is %s!\n"
                + "Attempting to fetch your tasks...\n",
                this.botName);
    }

    /**
     * Takes in user input and performs an action accordingly.
     *
     * @param input The user's input.
     * @return The String representing the command interpreted from user input.
     */
    public String receiveUserCommand(String input) {
        return this.parser.interpretCommand(input);
    }

    /**
     * Returns the message for various types of exceptions.
     *
     * @param type The type of exception.
     * @return A string representing the exception.
     */
    public String printException(String type) {
        assert type.length() > 0 : "Exception type should be stated!";
        switch (type) {
        case "IOException":
            return "There is a problem with saving the list to the file.\n"
                    + "Please ensure a duke.txt file is present in /data.\n";
        case "DateTimeParse":
            return "Your date might not be in the correct format.\n"
                    + "Please ensure it is in the YYYY-MM-DD format.\n";
        default:
            return "ERROR! ERROR!";
        }
    }

    /**
     * Returns the help message for the user.
     *
     * @return The String representing the help message.
     */
    public static String printHelp() {
        return "Here is the list of commands you can enter:\n"
                + "- Create new tasks:\n"
                + "    todo <task name>\n"
                + "    deadline <task name> /by <YYYY-MM-DD>\n"
                + "    event <task name> /at <YYYY-MM-DD>\n"
                + "- Display your list of tasks:\n"
                + "    list\n"
                + "- Mark a task as done\n"
                + "    done <task number>\n"
                + "- Delete a task\n"
                + "    delete <task number>\n"
                + "- Search the list for tasks matching keyword\n"
                + "    find <keyword>\n"
                + "- Exit the assistant\n"
                + "    bye\n";
    }

    /**
     * Returns the farewell message for the user.
     *
     * @return The String representing the farewell message.
     */
    public static String printBye() {
        sc.close();
        return "Goodbye. Hope to see you again soon!\n";
    }
}
