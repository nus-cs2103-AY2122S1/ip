package duke;

import java.util.Scanner;

/**
 * UI for Duke.
 */
public class UI {
    private static final String ROBOT_ICON = "[~o_o~]";
    private static final String LINE = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
            + "~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private static final String ROBOT_TEXT_SPACE = "         ";
    private final Scanner myScanner;

    public UI() {
        this.myScanner = new Scanner(System.in);
    }
    /**
     * Prints greeting text of the robotFriend.
     */
    public static void greet() {
        System.out.println(LINE);
        System.out.println(ROBOT_ICON + ": Hello I am your RobotFriend.\n" + ROBOT_TEXT_SPACE + "How can i help you?");
        System.out.println(ROBOT_TEXT_SPACE + "Commands:");
        System.out.println(ROBOT_TEXT_SPACE + "bye: terminate session.");
        System.out.println(ROBOT_TEXT_SPACE + "list: view all tasks in the list.");
        System.out.println(ROBOT_TEXT_SPACE + "done i: mark task number i as complete and view the task.");
        System.out.println(ROBOT_TEXT_SPACE + "delete i: delete the task at the given index i.");
        System.out.println(ROBOT_TEXT_SPACE + "todo *description*: add todo task.");
        System.out.println(ROBOT_TEXT_SPACE + "event *description* /at *date in dd/MM/YYYY*: add event task.");
        System.out.println(ROBOT_TEXT_SPACE + "deadline *description* /by *timing in dd/MM/YYYY*: add deadline task.");
        System.out.println(LINE);
    }

    /**
     * Echos the user inputted String in a robot way.
     *
     * @param userInput user inputted String
     */
    private static void echo(String userInput) {
        System.out.println(LINE);
        System.out.println(ROBOT_ICON + ": " + userInput);
        System.out.println(LINE);
    }

    /**
     * Prints the exiting text in a robot way.
     */
    public static void bye() {
        System.out.println(LINE);
        System.out.println(ROBOT_ICON + ": " + "Bye~ Hope to see you soon :)");
        System.out.println(LINE);
    }


    /**
     * Prints error message.
     *
     * @param e error
     */
    public static void printErrorMessage(Exception e) {
        System.out.println(LINE);
        System.out.println(e.getMessage());
        System.out.println(LINE);
    }

    public static void printLine() {
        System.out.println(LINE);
    }

    public static void printNoOfTasks(int noOfTasks) {
        System.out.println(ROBOT_ICON + ": " + "Now you have " + noOfTasks + " tasks!");
    }

    /**
     * Prints the input text prefixed with robot icon.
     *
     * @param text user input string
     */
    public static void printRobotMsg(String text) {
        System.out.println(ROBOT_ICON + ": " + text);
    }

    /**
     * Print message input by user.
     *
     * @param text user input string
     */
    public static void printMsg(String text) {
        System.out.println(text);
    }

    /**
     * Constructs a UI.
     */
    public String readInput() {
        return myScanner.nextLine();
    }


}
