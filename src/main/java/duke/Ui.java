package duke;
import java.util.Scanner;
import duke.task.*;

public class Ui {
    private final Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }

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
}
