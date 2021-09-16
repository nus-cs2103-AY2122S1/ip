package duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Ui class handles the input and output of the program.
 */
public class Ui {
    private final Scanner SCANNER;
    private String separator = "-----------------------------------------" +
            "------------------------------------------------";

    /**
     * Constructor for Ui.
     */
    public Ui() {
        this.SCANNER = new Scanner(System.in);
    }

    /**
     * Shows the exit message.
     *
     * @return The output string.
     */
    public String showGoodBye() {
        return writeOutput("    Bye. Hope to see you again!");
    }

    /**
     * Lists the items in the list in the order added, along with a counter.
     *
     * @param taskList The list of task.
     * @return The output string.
     */
    public String showList(ArrayList<String> taskList) {
        String list = "";

        int count = 1;
        for (String s : taskList) {
            list += count + ". " + s + "\n";
            count++;
        }

        return writeOutput(list);
    }

    /**
     * Writes the message to output with a line divider around it.
     *
     * @param message The message to be displayed.
     * @return The output string.
     */
    public String writeOutput(String message) {
        String output = separator + "\n";
        output += message + "\n";
        output += separator + "\n";
        return output;
    }

    /**
     * Displays the error message based on the given command.
     *
     * @param command Determines which error message to display.
     * @return The output string.
     */
    public String showInputError(String command) {
        String output = "";
        switch(command){
        case("done"):
            output = writeOutput("Enter an integer after done...");
            break;
        case("delete"):
            output = writeOutput("Enter an integer after delete...");
            break;
        case("todo"):
            output = writeOutput("The description of ToDo cannot be empty!\n Please try again :-(");
            break;
        case("deadline"):
            output = writeOutput("Follow the format: \ndeadline %description% /by %date%");
            break;
        case("event"):
            output = writeOutput("Follow the format: \nevent %description% /at %date/time%");
            break;
        case("dateformat"):
            output = writeOutput("Date format is wrong.\nPlease follow the format YYYY-MM-DD");
            break;
        case("invalid"):
            output = writeOutput("Command not recognised!\n" + menu());
            break;
        case("index"):
            output = writeOutput("You forgot to indicate which task!!!");
            break;
        case("find"):
            output = writeOutput("Please enter a keyword/phrase/#tag to search for!");
            break;
        case("tag"):
            output = writeOutput("Enter an integer followed by #tagName after tag...");
            break;
        default:
            output = writeOutput("Oops, something went wrong!");
            break;
        }

        return output;
    }

    /**
     * Displays the error message given by DukeException.
     *
     * @param e DukeException instance.
     * @return The output string.
     */
    public String showDukeException(DukeException e) {
        return writeOutput(e.getMessage());
    }

    /**
     * Returns a menu string.
     *
     * @return A String representing the menu.
     */
    public String menu() {
        return "Commands supported:\n"
                + "bye: Quit program\n"
                + "list: Displays the list of tasks\n"
                + "find {keyword}: Shows a list of tasks containing {keyword}\n"
                + "done {index}: Marks the {index} task as done\n"
                + "delete {index}: Deletes the {index} task\n"
                + "tag {index} #{tag}: tag the {index} task with {tag}"
                + "todo {task name}: Adds {task name} to the list\n"
                + "deadline {task name} /by {YYYY-MM-DD}: Adds {task name} to the list with a deadline\n"
                + "event {task name} /by {YYYY-MM-DD}: Adds {task name} to the list with an event date";
    }
}
