package duke;

/**
 * Ui is a static class that handles most of the print statements that the user will see.
 * @author meerian
 *
 */
public class Ui {

    /**
     * Prints out the logo of the DukeBot.
     *
     */
    public static void logo() {
        Ui.border();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Prints out the standard border between each print statement of the bot.
     *
     */
    public static void border() {
        System.out.println("──────────────────────────────────────────");
    }

    /**
     * Prints out the welcome message upon startup of the bot.
     *
     */
    public static void welcomeMessage() {
        System.out.println("What can i do for you?");
        Ui.border();
    }

    /**
     * Prints out an error message when an unknown command is entered by user.
     *
     */
    public static void unknownCommand() {
        Ui.border();
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        Ui.border();
    }

    /**
     * Prints out the goodbye message once user closes the bot.
     *
     */
    public static void goodbye() {
        System.out.println("Bye, hope to see you again soon!");
    }


}
