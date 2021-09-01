package duke.util;

import duke.exception.InvalidInputException;

import java.util.Scanner;

public class Ui {

    private static final String tab = "      ";
    private static final String line = "------------------------------------------------------------";
    private Scanner sc = new Scanner(System.in);

    /**
     * Shows the welcome message.
     */
    public void showWelcome() {
        String necroLogo =
                " ___    _                \n"
                        + "|   \\  | |   _____     ___    _  __     _____  \n"
                        + "| |\\ \\ | |  / __  \\  /   _|  | v __|  /  ___  \\ \n"
                        + "| | \\ \\| | |    __/  |  <_   |  /    |   |_|   |\n"
                        + "|_|  \\___|  \\_____|  \\____|  |__|     \\  ___  / \n";
        System.out.println("Hello from\n" + necroLogo);
        textBox("Hello. My name is Necro.",
                "What do you want me to do for you on this horrible day?");
    }

    /**
     * Shows a file loading error.
     */
    public void showLoadingError() {
        textBox("No such file exists. Creating one for you now. You're welcome.");
    }

    /**
     * Reads the user input.
     *
     * @return The input with all the redundant spaces removed.
     */
    public String readCommand() {
        return Parser.parseInput(sc.nextLine());
    }

    /**
     * Shows an InvalidInputException with a specified message.
     *
     * @param errorMessage The specific message we want to show.
     */
    public static void showError(String errorMessage) {
        try {
            throw new InvalidInputException(errorMessage);
        } catch (InvalidInputException e) {
            System.err.println(e);
        }
    }

    /**
     * Shows a message when the TaskList is empty.
     */
    public void showEmptyList() {
        textBox("There are currently no tasks, fool.");
    }

    /**
     * Shows a message that the specified Task has been added.
     *
     * @param taskString The String representation of the Task.
     * @param listSize The size of the TaskList.
     */
    public void showAdd(String taskString, int listSize) {
        textBox("Fine. I've added this meaningless task to your list: ",
                " --> " + taskString,
                "Satisfied now? You have " + listSize + " items in your list. ");
    }

    /**
     * Shows a Task completed message.
     *
     * @param taskString The String representation of the Task.
     */
    public void showComplete(String taskString) {
        textBox("Wow. Congratulations. You have completed the following task:",
                taskString,
                "Are you happy now?");
    }

    /**
     * Shows a Task deleted message.
     *
     * @param taskString The String representation of the Task.
     */
    public void showDelete(String taskString) {
        textBox("Since you are so lazy, I've helped you delete this task:",
                taskString,
                "Go do something useful with your life.");
    }

    /**
     * Shows the farewell message.
     */
    public void showGoodbye() {
        textBox("Farewell, may we never meet again.");
    }

    /**
     * A template for the formatted text box.
     *
     * @param messages The varargs of messages we want to show the user.
     */
    public void textBox(String... messages) {
        System.out.println(tab + line);
        for (String message : messages) {
            System.out.println(tab + " " + message);
        }
        System.out.println(tab + line);
    }

}
