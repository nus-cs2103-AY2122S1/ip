package duke;

import java.util.Scanner;

/**
 * Handles all the interaction with the user.
 */
public class Ui {

    /**
     * Global variables
     */
    private static final String LINE = "\t______________________________________________________________________";
    private static final String END_LINE = "\t======================================================================\n";
    private static final String LOGO = "\n"
            + "███████████████████████████████\n"
            + "█▄─▄─▀█─█─█▄─██─▄█─▄─▄─█▄─██─▄█\n"
            + "██─▄─▀█─▄─██─██─████─████─██─██\n"
            + "▀▄▄▄▄▀▀▄▀▄▀▀▄▄▄▄▀▀▀▄▄▄▀▀▀▄▄▄▄▀▀";
    private static Scanner sc = new Scanner(System.in);

    // Help Section Strings.
    private static final String welcomeToHelpSectionMessage = "Welcome to the help section. "
            + "\nI will help you find what you desire";
    private static final String byeCommandDescription = "Use the bye command to end the session.";
    private static final String byeCommandExample = "Command Example: bye";
    private static final String deadlineCommandDescription = "Use the deadline command to add a deadline type task.";
    private static final String deadlineCommandExample = "Command Example: deadline CS2103T iP /by 23-09-2021";
    private static final String deadlineCommandFormat = "-> deadline TASK_DESCRIPTION /by DD-MM-YYYY";
    private static final String deleteCommandDescription = "Use the delete command to delete a task.";
    private static final String deleteCommandExample = "Command Example: delete 4";
    private static final String deleteCommandFormat = "-> delete TASK_NUMBER";
    private static final String doneCommandMessage = "Use the done command to mark a task as done.";
    private static final String doneCommandExample = "Command Example: done 7";
    private static final String doneCommandFormat = "-> done TASK_NUMBER";
    private static final String eventCommandMessage = "Use the event command to add an event type task.";
    private static final String eventCommandExample = "Command Example: event fix hair /at weekend";
    private static final String eventCommandFormat = "-> event TASK_DESCRIPTION /at time";
    private static final String findCommandDescription = "Use the find command to find tasks.";
    private static final String findCommandExample = "Command Example: find notes";
    private static final String findCommandFormat = "-> find KEYWORD";
    private static final String helpCommandDescription = "Use the help command to open the help section.";
    private static final String helpCommandExample = "Command Example: help";
    private static final String listCommandDescription = "Use the list command to view the tasks "
            + "currently present in the task list.";
    private static final String listCommandExample = "Command Example: list";
    private static final String todoCommandDescription = "Use the todo command to add a todo type task.";
    private static final String todoCommandExample = "Command Example: todo iP cleanup";
    private static final String todoCommandFormat = "-> todo TASK_DESCRIPTION";
    private static final String undoCommandDescription = "Use the undo command to undo the latest action you "
            + "performed.";
    private static final String undoCommandExample = "Command Example: undo";
    private static final String goodbyeMessage = "Going so soon? Hope to see you again!";

    /**
     * Greets the user.
     */
    public static String getWelcomeText() {
        return "Hello! I'm Bhutu, your personal chatbot!\nWhat can I do for you today?";
    }

    /**
     * Gets the user input.
     *
     * @return The string representation of the user input.
     */
    public static String getInput() {
        return sc.nextLine();
    }

    /**
     * Prints the goodbye text.
     */
    public static String printGoodBye() {
        return goodbyeMessage;
    }

    /**
     * Prints all bot messages in a specific format.
     *
     * @param message message from the bot.
     */
    public static void printMessage(String message) {
        message = "\t" + message.replace("\n", "\n\t");
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(END_LINE);
    }

    /**
     * Returns the help text.
     *
     * @return a string containing guidance on how to use the bot.
     */
    public static String getHelpMenu() {
        String helpMessage = welcomeToHelpSectionMessage + "\n\n"
                + byeCommandDescription + "\n" + byeCommandExample + "\n\n"
                + deadlineCommandDescription + "\n" + deadlineCommandFormat + "\n" + deadlineCommandExample + "\n\n"
                + eventCommandMessage + "\n" + eventCommandFormat + "\n" + eventCommandExample + "\n\n"
                + todoCommandDescription + "\n" + todoCommandFormat + "\n" + todoCommandExample + "\n\n"
                + deleteCommandDescription + "\n" + deleteCommandFormat + "\n" + deleteCommandExample + "\n\n"
                + doneCommandMessage + "\n" + doneCommandFormat + "\n" + doneCommandExample + "\n\n"
                + findCommandDescription + "\n" + findCommandFormat + "\n" + findCommandExample + "\n\n"
                + listCommandDescription + "\n" + listCommandExample + "\n\n"
                + helpCommandDescription + "\n" + helpCommandExample + "\n\n"
                + undoCommandDescription + "\n" + undoCommandExample + "\n\n";

        return helpMessage;

    }
}
