package duke.util;

public class Ui {
    private static final String INDENTATION = "     ";
    private static final String LINE_SEPARATOR = "    ____________________________________________________________";
    private static final String[] GREETING = {"Hey! I'm Duke (Tsun)!", "What do you want?",
            "...It's not like I want to help you or anything!"};
    private static final String[] GOODBYE = {"Hmph! It's not like I want to see you again or anything!"};

    /**
     * Prints messages line by line enclosed within line separators.
     *
     * @param messages an array of String that contains the messages to be printed
     */
    public static void displayMessage(String[] messages) {
        for (String message : messages) {
            System.out.println(INDENTATION + message);
        }
    }

    public static void showLine() {
        System.out.println(LINE_SEPARATOR);
    }

    public static void newLine() {
        System.out.println();
    }

    public static void showWelcome() {
        displayMessage(GREETING);
    }

    public static void bidFarewell() {
        displayMessage(GOODBYE);
    }

    /**
     * Prints tasks line by line enclosed within line separators.
     *
     * Each task is indexed by the order they were added into the task list, starting from 1.
     */
    public static void displayTasks(TaskList tasksList, String dateString) {
        System.out.println(LINE_SEPARATOR);

        System.out.println(LINE_SEPARATOR + "\n");
    }

}