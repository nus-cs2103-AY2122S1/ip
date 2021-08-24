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
    public void greeting() {
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
        String greeting = "I am Jo the Frog! RIBBIT! \n";
        System.out.println(frog + greeting + "How may I help you?\n" + LINE);
    }

    /**
     * Message to be seen by user upon closing the program.
     */
    public void goodbye() {
        System.out.println("See you again in my frog hole! RIBBIT!");
        in.close();
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
            throw new DukeException(e.getMessage());
        }
        return input;
    }

    /**
     * Message to be seen by user if hard disk file does not exist.
     */
    public void showLoadingError() {
        System.out.println("File not detected, new file will be created!");
    }

    /**
     * Divider line.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Prefix for error messages.
     *
     * @param msg The error message
     */
    public void showError(String msg) {
        System.out.println("ERROR: " + msg);
    }

    /**
     * Message signalling completion of task by user.
     *
     * @param tasks The TaskList to be affected
     * @param index The index of the task to be marked done
     */
    public void showDoneMessage(TaskList tasks, int index) {
        System.out.println("You have swallowed that pesky fly! RIBBIT!");
        System.out.println("  " + tasks.get(index).toString());
    }

    /**
     * Message to be seen by user upon deleting the given task.
     *
     * @param tasks The TaskList to be affected
     * @param index The index of the task to be deleted
     */
    public void showDeleteMessage(TaskList tasks, int index) {
        System.out.println("Rotten flies deserve to die!");
        System.out.println("  " + tasks.get(index).toString());
        System.out.printf("Now you have %d flies to eat! RIBBIT!\n",
                tasks.size() - 1);

    }

    /**
     * Message to be seen by user upon "list" user input.
     *
     * @param tasks The TaskList to be affected
     */
    public void showListMessage(TaskList tasks) {
        System.out.println("Here is your menu for today:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + "." + tasks.get(i).toString());
        }
    }

    /**
     * Message to be seen by user when they add a task into the task list.
     *
     * @param tasks The TaskList to be affected
     */
    public void addTaskMessage(TaskList tasks) {
        System.out.println("A fly has been added to the menu:");
        System.out.println("  " + tasks.get(tasks.size() - 1).toString());
        System.out.println("Now you have " + tasks.size() + " flies to eat! RIBBIT!");
    }

    public void showFindMessage(TaskList tasks, ArrayList<Integer> matches) {
        System.out.println("Here are the flies with the matching smell:");
        for (int i = 0; i < matches.size(); i++) {
            System.out.println(i + 1 + "." + tasks.get(matches.get(i)).toString());
        }
    }
}
