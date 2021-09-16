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
//        String necroLogoMonoFilled =
//                " ____     __\n"
//                        + "|NNNN\\   |NN|\n"
//                        + "|NNNNN\\  |NN|  ____    ____    ____  _____\n"
//                        + "|NN|\\NN\\ |NN| /e__e\\  /  __)|\\/  __)/  _  \\\n"
//                        + "|NN| \\NN\\|NN||e(__)e||  /   |  /   |  / \\  |\n"
//                        + "|NN|  \\NNNNN||ee____||  \\__ |  |   |  \\_/  |\n"
//                        + "|__|   \\____| \\_____) \\____)|__|    \\_____/\n";
//                " ___    _                \n"
//                        + "|   \\  | |   _____     ___    _  __     _____  \n"
//                        + "| |\\ \\ | |  / __  \\  /   _|  | v __|  /  ___  \\ \n"
//                        + "| | \\ \\| | |    __/  |  <_   |  /    |   |_|   |\n"
//                        + "|_|  \\___|  \\_____|  \\____|  |__|     \\  ___  / \n";
//        String necroLogo =
//                "_____       ___               \n"
//                        + "|       \\     |    |      ____       ____   __  ____      _____  \n"
//                        + "|    |\\   \\   |    |   / ___  \\   /    __)  |  V  ___)  /  ___   \\ \n"
//                        + "|    |  \\   \\ |    |  |     ___/  |    (__   |     /      (    |__|    )\n"
//                        + "|___|    \\_____|   \\______)   \\____)  |___|         \\  ___  /  ()\n";
        return "Hello. My name is:\n" + necroLogoMono + "\n\nWhat do you want me to do for you on this horrible day?";
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
            return e.getMessage();
        }
    }

    /**
     * Shows a message when the TaskList is empty.
     */
    public String showEmptyList() {
        return textBox("There are currently no tasks, fool.");
    }

    /**
     * Shows a message that the specified Task has been added.
     *
     * @param taskString The String representation of the Task.
     * @param listSize The size of the TaskList.
     */
    public String showAdd(String taskString, int listSize) {
        return textBox("Fine. I've added this meaningless task to your list: \n",
                " --> " + listSize + ". " + taskString,
                "\nSatisfied now? You have " + listSize + " items in your list: \n\n");
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

//    /**
//     * Shows a Task deleted message.
//     *
//     * @param taskString The String representation of the Task.
//     */
//    public String showDelete(String taskString) {
//        return textBox("Since you are so lazy, I've helped you delete this task:",
//                taskString,
//                "Go do something useful with your life.");
//    }

//    /**
//     * Shows the farewell message.
//     */
//    public static String showGoodbye() {
//        return "Farewell, may we never meet again.";
//    }

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
