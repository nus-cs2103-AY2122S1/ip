package cs2103.duke;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class encapsulates an Ui object, which abstracts the user interactions with Duke out of
 * the Duke class.
 */
public class Ui {
    private static final int lv = 8;
    private static final String[] features = {
            "",
            "Greet, Echo, Exit",
            ", Add, List",
            ", Mark as Done",
            ", ToDos, Events, Deadlines",
            ", Handle Errors",
            ", Delete",
            ", Save",
            ", Dates and Times"
    };
    private static TaskList taskArrayList;

    public Ui(TaskList tasks) {
        taskArrayList = tasks;
    }

    /**
     * This method takes an input string and formats it by including horizontal lines above
     * and below the input string.
     *
     * @param str Input string to be sandwiched.
     * @return The original string sandwiched between two horizontal lines.
     */
    public static String sandwich(String str) {
        return "____________________________________________________________\n"
                + str + "\n"
                + "____________________________________________________________";
    }


    /**
     * Shows the welcome message when the user runs Duke.
     *
     * @return The welcome message to the user.
     */
    public String showWelcome() {
        // populating featuresCombined so each level has all elements of levels before it
        StringBuilder featuresCombined = new StringBuilder();
        for (int count = 0; count <= lv; count++) {
            featuresCombined.append(features[count]);
        }

        // Welcome message
        String welcome = "Hello! I'm Duke: Level " + lv + "\n"
                + "What would you like to do today?\n"
                + "My current features are: " + featuresCombined + "\n"
                + "Here are your tasks: " + "\n"
                + sandwich(taskArrayList.listBeautify());

        return (sandwich(welcome));
    }

    /**
     * Shows the goodbye message when the user exits Duke.
     *
     * @return The goodbye message to the user.
     */
    public String showGoodbye() throws IOException {
        // Goodbye message
        String goodbye = "Thank you for using Duke: Level " + lv + "\n"
                + "See you soon!";

        return (sandwich(goodbye));
    }

    /**
     * Reads user's inputs and responds to it accordingly.
     *
     * @param scanner   The scanner object used to read user inputs.
     * @param userInput The information entered by the user after the command.
     * @param tasks     The Tasklist object containing all currently present tasks.
     * @return The string representing the task added by the user.
     * @throws DukeException If user enters an invalid input.
     */
    public String handleInput(Scanner scanner, String userInput, TaskList tasks) throws DukeException {
        switch (userInput) {
        case "list":  // user inputs 'list', return all text stored
            return tasks.listTasks();
        case "done":  // first input is done, check second input for integer
            if (scanner.hasNextInt()) {
                int taskNum = scanner.nextInt();
                return tasks.finishTask(taskNum);
            } else throw new DukeException("unspecified task to mark as done");
        case "delete":
            if (scanner.hasNextInt()) {
                int taskNum = scanner.nextInt();
                return tasks.deleteTask(taskNum);
            } else throw new DukeException("unspecified task to delete");
        case "todo":
            String todoName = scanner.nextLine();
            if (todoName.trim().equals("")) {
                throw new DukeException("No task description");
            }
            return tasks.addTask("todo", todoName, "");
        case "deadline":
            String[] deadlineTokens = scanner.nextLine().split("\\s*/by\\s*");
            if (deadlineTokens.length == 0) {
                throw new DukeException("No task description");
            } else if (deadlineTokens.length == 1) {
                throw new DukeException("No task deadline");
            }
            String deadlineName = deadlineTokens[0];
            String deadlineReminder = deadlineTokens[1];
            return tasks.addTask("deadline", deadlineName, deadlineReminder);
        case "event":
            String[] eventTokens = scanner.nextLine().split("\\s*/at\\s*");
            if (eventTokens.length == 0) {
                throw new DukeException("No task description");
            } else if (eventTokens.length == 1) {
                throw new DukeException("No task duration");
            }
            String eventName = eventTokens[0];
            String eventReminder = eventTokens[1];
            return tasks.addTask("event", eventName, eventReminder);
        default:
            throw new DukeException("Unknown Input"); // unknown input
        }
    }

    /**
     * Alert the user if the duke.txt could not be loaded.
     *
     * @return A string alerting the user of the error.
     */
    public String showLoadingError() {
        return ("Loading error: duke.txt could not be loaded");
    }

}
