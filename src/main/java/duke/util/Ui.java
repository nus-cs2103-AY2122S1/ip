package duke.util;

import duke.exception.InvalidInputException;

import java.util.Scanner;

public class Ui {

    private Scanner sc = new Scanner(System.in);

    /**
     * Shows the welcome message.
     * @return
     */
    public static String showWelcome() {
        String necroLogoMono =
                " ____     __\n"
                    + "|    \\   |  |\n"
                    + "|     \\  |  |  ____    ____    ____  _____\n"
                    + "|  |\\  \\ |  | / __ \\  /  __)|\\/  __)/  _  \\\n"
                    + "|  | \\  \\|  || (__) ||  /   |  /   |  / \\  |\n"
                    + "|  |  \\     ||  ____||  \\__ |  |   |  \\_/  |\n"
                    + "|__|   \\____| \\_____) \\____)|__|    \\_____/\n";
        return "Hello. My name is:\n" + necroLogoMono + "\n\nWhat do you want me to do for you on this horrible day?\n\n" +
                "If you require any assistance, type \"help\", press enter, and depending on my mood, I may or may not reluctantly help you out.";
    }

    /**
     * Shows a file loading error.
     */
    public String showLoadingError() {
        return textBox("No such file exists. Creating one for you now. You're welcome.");
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
    public static String showError(String errorMessage) {
        try {
            throw new InvalidInputException(errorMessage);
        } catch (InvalidInputException e) {
            return e.getMessage() + "\n\n" + "If you are unsure and need assistance with my commands, type \"help\" and press enter.";
        }
    }

    /**
     * Shows a message when the TaskList is empty.
     */
    public String showEmptyList() {
        return textBox("There are currently no tasks, fool." +
                "\n\nIf you don't know how to create new tasks, simply type in \"help\" and press enter.");
    }

    /**
     * Shows a message that the specified Task has been added.
     *
     * @param taskString The String representation of the Task.
     * @param listSize The size of the TaskList.
     */
    public String showAdd(String taskString, int listSize) {
        String lastLine = (listSize == 1)
                ? "\nSatisfied now? You have " + listSize + " item in your list: \n\n"
                : "\nSatisfied now? You have " + listSize + " items in your list: \n\n";
        return textBox("Fine. I've added this meaningless task to your list: \n",
                " --> " + listSize + ". " + taskString,
                lastLine);
    }

    /**
     * Shows a Task completed message.
     *
     * @param taskString The String representation of the Task.
     */
    public String showComplete(String taskString) {
        return textBox("Wow. Congratulations. You have completed the following task:\n",
                taskString,
                "\nAre you happy now? This is your updated list:");
    }

    /**
     * A template for the formatted text box.
     *
     * @param messages The varargs of messages we want to show the user.
     */
    public String textBox(String... messages) {
        String output = "";
        for (String message : messages) {
            output += message + "\n";
        }
        return output;
    }

}
