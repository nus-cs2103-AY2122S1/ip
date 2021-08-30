package duke;

import java.util.Scanner;
import duke.task.*;

/**
 * Ui class to handle printing notices to the user, and reading of user input to pass to Parser.
 */
public class Ui {
    private final Scanner sc;

    /**
     * Initialise scanner to read user input.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Method to read users input
     * @return one line of user input as a String.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Method called at beginning of application run. Prints welcome message.
     */
    public String init() {
        return  "░▄░█░░░▄▀▀▀▀▀▄░░░█░▄░\n" +
                "▄▄▀▄░░░█─▀─▀─█░░░▄▀▄▄\n" +
                "░░░░▀▄▒▒▒▒▒▒▒▒▒▄▀░░░░\n" +
                "░░░░░█────▀────█░░░░░\n" +
                "░░░░░█────▀────█░░░░░\n" +
                "I'm Frosty, your personal task manager! How can I help?";
    }

    //temporarily broken
    public String displayList(TaskList tasklist) {
        return "Here are the tasks in your list:";
    }

    //temporarily broken
    public String displayFindList(TaskList tasklist) {
        return "Here are the matching tasks in your list:";
    }

    public String notifySuccessfulAdd(TaskList tasklist) {
        String res = "";
        res +=  "Got it. I've added this task:\n";
        res +=  "  " + tasklist.get(tasklist.size() - 1) + "\n";
        res += "Now you have " + tasklist.size() + " tasks in the list.";
        return res;
    }

    public String notifySuccessfulMarkDone(TaskList tasklist, int index) {
        String res = "";
        res += "Nice! I've marked this task as done:\n";
        res += "  " + tasklist.get(index);
        return res;
    }

    public String notifySuccessfulDelete(TaskList tasklist, Task removed) {
        String res = "";
        res += "Noted. I've removed this task:\n";
        res += "  " + removed + "\n";
        res += "Now you have " + tasklist.size() + " tasks in the list.";
        return res;
    }

    public String notifyBadCommand() {
        return ("Sorry! I don't know what your request means. Please try again?");
    }

    public String notifyLoadingError() {
        return ("An error occurred while loading in the saved data...");
    }

    public String closing() {
        return ("Have a Merry Christmas and a Happy New Year!");
    }

    public String notifyLoadingBegin() {
        return ("It look like you have a previous session! Loading your data now...");
    }

    public String notifyLoadingComplete() {
        return ("Loading complete!");
    }

    public String notifySavingBegin() {
        return ("Just a moment, i'm saving your list!");
    }

    public String notifySavingComplete() {
        return ("Done!");
    }

    public String notifySavingError() {
        return ("Something went wrong and I can't save your list, sorry!");
    }

    public String notifyIndexOutOfBounds() {
        return ("Sorry! Your command has an invalid index choice");
    }

    public String notifyImproperIndex() {
        return ("Sorry! I can't understand the index for your command");
    }

    public String notifyEmptyDescription() {
        return ("Sorry! Your command appears to be missing a description");
    }

    public String notifyImproperDateTime() {
        return ("Sorry! I don't recognise the format for the date and time you've entered.");
    }

    public String printLine() {
        return ("--------------------------------------------------");
    }

    public String notifyFolderFound() {
        return ("I've found the data folder. Your session will be saved there.");
    }

    public String notifyFolderCreated() {
        return ("I couldn't find a data folder. I've initialised one for your data.");
    }
}
