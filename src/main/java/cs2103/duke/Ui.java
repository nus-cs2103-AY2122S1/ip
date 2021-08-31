package cs2103.duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final int lv = 9;
    private static final String[] features = {
            "",
            "Greet, Echo, Exit",
            ", Add, List\n",
            ", Mark as Done",
            ", ToDos, Events, Deadlines",
            ", Handle Errors\n",
            ", Delete",
            ", Save",
            ", Dates and Times",
            ", Find\n"
    };
    private static TaskList taskArrayList;

    public Ui(TaskList tasks) {
        this.taskArrayList = tasks;
    }

    /**
     * This method takes an input string and formats it by including horizontal lines above
     * and below the input string
     *
     * @param str input string to be sandwiched
     * @return the original string sandwiched between two horizontal lines
     */
    public static String sandwich(String str) {
        return "____________________________________________________________\n"
                + str + "\n"
                + "____________________________________________________________";
    }


    /**
     * Shows the welcome message when user runs Duke.
     */
    public String showWelcome() throws IOException {
        // populating featuresCombined so each level has all elements of levels before it
        StringBuilder featuresCombined = new StringBuilder();
        for (int count = 0; count <= lv; count++) {
            featuresCombined.append(features[count]);
        }
//
//        // parsing duke.txt
//        DukeParser dp = new DukeParser(dukeFilePath);
//        taskArrayList = dp.copyFileContents();

        // Welcome message
        String welcome = "Hello! I'm Duke: Level " + lv + "\n"
                + "What would you like to do today?\n"
                + "My current features are: " + featuresCombined + "\n"
                + "Here are your tasks: " + "\n"
                + sandwich(taskArrayList.listBeautify());

        return (sandwich(welcome));
    }

    /**
     * Shows the goodbye message when user exits Duke.
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
     * @param userInput the information entered by the user after the command
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
        case "find":
            if (scanner.hasNext()) {
                return tasks.findTasks(scanner.nextLine());
            } else throw new DukeException("unspecified keyword to search for");
        default:
            throw new DukeException("Unknown Input"); // unknown input
        }
    }

    public String showLoadingError() {
        return ("Loading error: duke.txt could not be loaded");
    }

}
