package mango;

/**
 * Represents a user interface that controls the interactions with the user, such as
 * greeting, showing errors, and exiting.
 */
public class Ui {

    /**
     * Constructor for a Ui.
     */
    public Ui() {
    }

    /**
     * Greets the user with the chat-bot logo and an introduction.
     */
    public void greet() {
        String logo = " __  __    ___    _  _     ___     ___\n"
                + "|  \\/  |  /   \\  | \\| |   / __|   / _ \\\n"
                + "| |\\/| |  | - |  | .` |  | (_ |  | (_) |\n"
                + "|_|__|_|  |_|_|  |_|\\_|   \\___|   \\___/\n"
                + "_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|\n"
                + "\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\n";


        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Mango\nWhat can I do for you?");
    }

    /**
     * Echoes the input of the user.
     *
     * @param str The string input of the user.
     */
    public void echo(String str) {
        System.out.println(str);
    }

    /**
     * Prints a farewell message to the user.
     */
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a loading error message.
     *
     * @param e The exception that was thrown during loading.
     */
    public void showLoadingError(Exception e) {
        System.out.println("Error encountered when loading data: " + e.getMessage());
    }
}
