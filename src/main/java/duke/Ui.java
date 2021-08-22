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
    public void init() {
        System.out.println(
                        "░▄░█░░░▄▀▀▀▀▀▄░░░█░▄░\n" +
                        "▄▄▀▄░░░█─▀─▀─█░░░▄▀▄▄\n" +
                        "░░░░▀▄▒▒▒▒▒▒▒▒▒▄▀░░░░\n" +
                        "░░░░░█────▀────█░░░░░\n" +
                        "░░░░░█────▀────█░░░░░\n");
        System.out.println("I'm Frosty, your personal task manager! How can I help?");
    }

    public void displayList(TaskList tasklist) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasklist.size(); i++) {
            System.out.println((i + 1) + ". " + tasklist.get(i));
        }
    }

    public void notifySuccessfulAdd(TaskList tasklist) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasklist.get(tasklist.size() - 1));
        System.out.println("Now you have " + tasklist.size() + " tasks in the list.");
    }

    public void notifySuccessfulMarkDone(TaskList tasklist, int index) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasklist.get(index));
    }

    public void notifySuccessfulDelete(TaskList tasklist, Task removed) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + removed);
        System.out.println("Now you have " + tasklist.size() + " tasks in the list.");
    }

    public void notifyBadCommand() {
        System.out.println("Sorry! I don't know what your request means. Please try again?");
    }

    public void notifyLoadingError() {
        System.out.println("An error occurred while loading in the saved data...");
    }

    public void closing() {
        System.out.println("Have a Merry Christmas and a Happy New Year!");
    }

    public void notifyLoadingBegin() {
        System.out.println("It look like you have a previous session! Loading your data now...");
    }

    public void notifyLoadingComplete() {
        System.out.println("Loading complete!");
    }

    public void notifySavingBegin() {
        System.out.println("Just a moment, i'm saving your list!");
    }

    public void notifySavingComplete() {
        System.out.println("Done!");
    }

    public void notifySavingError() {
        System.out.println("Something went wrong and I can't save your list, sorry!");
    }

    public void notifyIndexOutOfBounds() {
        System.out.println("Sorry! Your command has an invalid index choice");
    }

    public void notifyImproperIndex() {
        System.out.println("Sorry! I can't understand the index for your command");
    }

    public void notifyEmptyDescription() {
        System.out.println("Sorry! Your command appears to be missing a description");
    }

    public void notifyImproperDateTime() {
        System.out.println("Sorry! I don't recognise the format for the date and time you've entered.");
    }

    public void printLine() {
        System.out.println("--------------------------------------------------");
    }

    public void notifyFolderFound() {
        System.out.println("I've found the data folder. Your session will be saved there.");
    }

    public void notifyFolderCreated() {
        System.out.println("I couldn't find a data folder. I've initialised one for your data.");
    }
}
