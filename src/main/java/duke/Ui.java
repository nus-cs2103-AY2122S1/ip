package duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Ui class handles the input and output of the program.
 */
public class Ui {
    private final Scanner SCANNER;
    private String separator = "----------------------------------------";

    /**
     * Constructor for Ui.
     */
    public Ui() {
        this.SCANNER = new Scanner(System.in);
    }

    /**
     * Shows the welcome message
     */
    public void showWelcome() {
        String LOGO = "----------------------------------------\n"
                + " ____        _        \n"
                + "|  _ \\  ___ | | _____ \n"
                + "| | | |/ _ \\| |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\___/|_|\\_\\___|\n"
                + "Hello! I'm Doke\nWhat do you want??\n"
                + "----------------------------------------";
        System.out.println(LOGO);
    }

    /**
     * Reads input from user.
     *
     * @return The input from the user
     */
    public String readInput() {
        String input = SCANNER.nextLine();
        return input;
    }

    /**
     * Shows the exit message.
     */
    public void showGoodBye() {
        writeOutput("    Bye. Hope to see you again!");
    }

    /**
     * Lists the items in the list in the order added, along with a counter.
     */
    public void showList(ArrayList<String> taskList) {
        System.out.println(separator);
        int count = 1;
        for (String s : taskList) {
            System.out.println(count + ". " + s);
            count++;
        }
        System.out.println(separator);
    }

    /**
     * Writes the message to output with a line divider around it.
     *
     * @param message The message to be displayed.
     */
    public void writeOutput(String message) {
        System.out.println(separator);
        System.out.println(message);
        System.out.println(separator);
    }

    /**
     * Displays the error message based on the given command.
     *
     * @param command Determines which error message to display.
     */
    public void showInputError(String command) {
        switch(command){
        case("done"):
            writeOutput("Enter an integer after done...");
            break;
        case("delete"):
            writeOutput("Enter an integer after delete...");
            break;
        case("todo"):
            writeOutput("The description of ToDo cannot be empty!\n Please try again :-(");
            break;
        case("deadline"):
            writeOutput("Follow the format: \ndeadline %description% /by %date%");
            break;
        case("event"):
            writeOutput("Follow the format: \nevent %description% /at %date/time%");
            break;
        case("dateformat"):
            writeOutput("Date format is wrong.\nPlease follow the format YYYY-MM-DD");
            break;
        case("invalid"):
            writeOutput("Command not recognised!\n" + menu());
            break;
        case("index"):
            writeOutput("You forgot to indicate which task!!!");
            break;
        default:
            writeOutput("Oops, something went wrong!");
            break;
        }
    }

    /**
     * Displays the error message given by DukeException.
     *
     * @param e DukeException instance.
     */
    public void showDukeException(DukeException e) {
        writeOutput(e.getMessage());
    }

    /**
     * Returns a menu string.
     *
     * @return A String representing the menu.
     */
    public String menu() {
        return "Commands supported:\n"
                + "bye: quit program\n"
                + "list: show the list of tasks\n"
                + "done {index}: Marks the {index} task as done\n"
                + "delete {index}: Deletes the {index} task\n"
                + "todo {task name}: Adds {task name} to the list\n"
                + "deadline {task name} /by {YYYY-MM-DD}: Adds {task name} to the list with a deadline\n"
                + "event {task name} /by {YYYY-MM-DD}: Adds {task name} to the list with an event date";
    }
}
