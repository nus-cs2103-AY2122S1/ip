package duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private static final String LINE = "----------------------------------------------";

    private Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * Duke's greeting message seen by the user upon start-up.
     */
    public String greeting() {
        // Credits to http://allaboutfrogs.org/gallery/frogstuff/ascii.html
        // for the frog ASCII text art!
        String frog =
                "    _____\n" +
                        "   /     \\______\n" +
                        "  | o     |     \\____\n" +
                        "  /\\_____/           \\___\n" +
                        " /                       \\\n" +
                        "|_______/                 \\\n" +
                        "  \\______   _       ___    \\\n" +
                        "        /\\_//      /   \\    |\n" +
                        "       // //______/    /___/\n" +
                        "      /\\/\\/\\      \\   / \\ \\\n" +
                        "                    \\ \\   \\ \\\n" +
                        "                      \\ \\   \\ \\\n" +
                        "                       \\ \\  /\\/\\\n" +
                        "                       /\\/\\\n";
        String greeting = "I am Jo the Frog! RIBBIT!\n";
        return frog + greeting + "How may I help you?\n" + LINE;
    }

    /**
     * Message to be seen by user upon closing the program.
     */
    public String goodbye() {
        return "See you again in my frog hole! RIBBIT!";
    }

    /**
     * Receiving user input from the Scanner.
     * @return a valid string input
     * @throws DukeException If input string is not valid
     */
    public String readCommand() throws DukeException {
        String input = in.nextLine();
        try {
            DukeException.checkInput(input.trim());
        } catch (DukeException e) {
            return e.getMessage();
        }
        return input;
    }

    /**
     * Message to be seen by user if hard disk file does not exist.
     */
    public String showLoadingError() {
        return "File not detected, new file will be created!";
    }

    /**
     * Divider line.
     */
    public String showLine() {
        return LINE;
    }

    /**
     * Prefix for error messages.
     *
     * @param msg The error message
     */
    public String showError(String msg) {
        return String.format("ERROR: %s", msg);
    }

    /**
     * Message signalling completion of task by user.
     *
     * @param tasks The TaskList to be affected
     * @param index The index of the task to be marked done
     */
    public String showDoneMessage(TaskList tasks, int index) {
        return "You have swallowed that pesky fly! RIBBIT!\n" +
                "  " + tasks.get(index).toString() + "\n";
    }

    /**
     * Message to be seen by user upon deleting the given task.
     *
     * @param tasks The TaskList to be affected
     * @param description The description of the task to be deleted
     */
    public String showDeleteMessage(TaskList tasks, String description) {
        return "Rotten flies deserve to die!\n" +
                " " + description + "\n" +
                "Now you have " + tasks.size() + " flies to eat! RIBBIT!\n";
    }

    /**
     * Message to be seen by user upon "list" user input.
     *
     * @param tasks The TaskList to be affected
     */
    public String showListMessage(TaskList tasks) {
        StringBuilder message = new StringBuilder();
        message.append("Here is your menu for today:\n");
        for (int i = 0; i < tasks.size(); i++) {
            message.append(i + 1 + "." + tasks.get(i).toString() + "\n");
        }
        return message.toString();
    }

    /**
     * Message to be seen by user when they add a task into the task list.
     *
     * @param tasks The TaskList to be affected
     */
    public String addTaskMessage(TaskList tasks) {
        return "A fly has been added to the menu:\n" +
                " " + tasks.get(tasks.size() - 1).toString() + "\n" +
                "Now you have " + tasks.size() + " flies to eat! RIBBIT!";
    }

    public String showFindMessage(TaskList tasks, ArrayList<Integer> matches) {
        StringBuilder message = new StringBuilder();
        message.append("Here are the flies with the matching smell:\n");
        for (int i = 0; i < matches.size(); i++) {
            message.append(i + 1 + "." + tasks.get(matches.get(i)).toString());
        }
        return message.toString();
    }
}

