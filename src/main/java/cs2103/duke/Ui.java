package cs2103.duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
    private static ArrayList<Task> taskArrayList = new ArrayList<>();
    private static final String dukeFilePath = "./data/duke.txt";

    public Ui() {

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
     * This method takes the user's input list and beautifies it for display.
     *
     * @param taskArrayList the user's input list to be beautified
     * @return the beautified string to display
     */
    public static String listBeautify(ArrayList<Task> taskArrayList) {
        StringBuilder listBeautified = new StringBuilder();
        for (int i = 0; i < taskArrayList.size(); i++) {
            listBeautified.append(i + 1)
                    .append(".")
                    .append(taskArrayList.get(i).toString());
            if (i < taskArrayList.size() - 1) { // new line except for last item
                listBeautified.append("\n");
            }
        }
        return listBeautified.toString();
    }

    /**
     * Shows the welcome message when user runs Duke.
     */
    public void showWelcome() throws IOException {
        // populating featuresCombined so each level has all elements of levels before it
        StringBuilder featuresCombined = new StringBuilder();
        for (int count = 0; count <= lv; count++) {
            featuresCombined.append(features[count]);
        }

        // parsing duke.txt
        DukeParser dp = new DukeParser(dukeFilePath);
        taskArrayList = dp.copyFileContents();

        // Welcome message
        String welcome = "Hello! I'm Duke: Level " + lv + "\n"
                + "What would you like to do today?\n"
                + "My current features are: " + featuresCombined + "\n"
                + "Here are your tasks: " + "\n"
                + sandwich(listBeautify(taskArrayList));

        System.out.println(sandwich(welcome));
    }

    /**
     * Shows the goodbye message when user exits Duke.
     */
    public void showGoodbye() throws IOException {
        // Goodbye message
        String goodbye = "Thank you for using Duke: Level " + lv + "\n"
                + "See you soon!";

        System.out.println(sandwich(goodbye));
    }

    /**
     * Reads user's inputs and responds to it accordingly.
     *
     * @param userInput the information entered by the user after the command
     */
    public void handleInput(Scanner scanner, String userInput, TaskList tasks) throws DukeException {
        switch (userInput) {
        case "list":  // user inputs 'list', return all text stored
            tasks.listTasks();
            break;
        case "done":  // first input is done, check second input for integer
            if (scanner.hasNextInt()) {
                int taskNum = scanner.nextInt();
                tasks.finishTask(taskNum);
            } else throw new DukeException("unspecified task to mark as done");
            break;
        case "delete":
            if (scanner.hasNextInt()) {
                int taskNum = scanner.nextInt();
                tasks.deleteTask(taskNum);
            } else throw new DukeException("unspecified task to delete");
            break;
        case "todo":
            String todoName = scanner.nextLine();
            if (todoName.trim().equals("")) {
                throw new DukeException("No task description");
            }
            tasks.addTask("todo", todoName, "");
            break;
        case "deadline":
            String[] deadlineTokens = scanner.nextLine().split("\\s*/by\\s*");
            if (deadlineTokens.length == 0) {
                throw new DukeException("No task description");
            } else if (deadlineTokens.length == 1) {
                throw new DukeException("No task deadline");
            }
            String deadlineName = deadlineTokens[0];
            String deadlineReminder = deadlineTokens[1];
            tasks.addTask("deadline", deadlineName, deadlineReminder);
            break;
        case "event":
            String[] eventTokens = scanner.nextLine().split("\\s*/at\\s*");
            if (eventTokens.length == 0) {
                throw new DukeException("No task description");
            } else if (eventTokens.length == 1) {
                throw new DukeException("No task duration");
            }
            String eventName = eventTokens[0];
            String eventReminder = eventTokens[1];
            tasks.addTask("event", eventName, eventReminder);
            break;
        default:
            throw new DukeException("Unknown Input"); // unknown input
        }
    }

    public void showLoadingError() {
        System.out.println("Loading error: duke.txt could not be loaded");
    }

}
