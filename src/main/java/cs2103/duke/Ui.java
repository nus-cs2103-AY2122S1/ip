package cs2103.duke;

import java.io.IOException;

/**
 * This class encapsulates an Ui object, which abstracts the user interactions with Duke out of
 * the Duke class.
 */
public class Ui {
    private static final int lv = 10;
    private static final String[] features = {
            "",
            "Greet, Echo, Exit, ",
            "Add, List, \n",
            "Mark as Done, ",
            "ToDos, Events, Deadlines, ",
            "Handle Errors, \n",
            "Delete, ",
            "Save, ",
            "Dates and Times, ",
            "Find\n"
    };
    private final Storage storage;
    private static TaskList taskArrayList;

    public Ui(Storage storage, TaskList tasks) {
        this.storage = storage;
        taskArrayList = tasks;
    }

    /**
     * Shows the welcome message when the user runs Duke.
     *
     * @return the welcome message to the user.
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
                + taskArrayList.listBeautify();

        return welcome;
    }

    /**
     * Shows the goodbye message when the user exits Duke.
     *
     * @return The goodbye message to the user.
     */
    public String showGoodbye() {
        // Goodbye message
        String goodbye = "Thank you for using Duke: Level " + lv + "\n"
                + "See you soon!";
        return goodbye;
    }

    /**
     * Reads user's inputs and responds to it accordingly.
     *
     * @param input The information entered by the user after the command.
     * @param tasks The Tasklist object containing all currently present tasks.
     * @return The string representing the task added by the user.
     * @throws DukeException If user enters an invalid input.
     */
    public String handleInput(String input, TaskList tasks) throws DukeException, IOException {
        // parse out the first word from input as the user's command
        String[] userInputs = input.split("\\s+", 2);
        String command = userInputs[0];
        String remainingInput = "";
        if (userInputs.length > 1) {
            remainingInput = userInputs[1];
        }

        switch (command) {
            case "bye":
                storage.overwriteFile(taskArrayList.listBeautify());
                return showGoodbye();
            case "list":  // user inputs 'list', return all text stored
                return tasks.listTasks();
            case "done":  // first input is done, check second input for integer
                if (remainingInput == null) {
                    throw new DukeException("unspecified task to mark as done");
                } else {
                    try {
                        int taskNumber = Integer.parseInt(remainingInput);
                        return tasks.finishTask(taskNumber);
                    } catch (NumberFormatException ex) {
                        ex.printStackTrace();
                    }
                }
            case "delete":
                if (remainingInput == null) {
                    throw new DukeException("unspecified task to delete");
                } else {
                    try {
                        int taskNumber = Integer.parseInt(remainingInput);
                        return tasks.deleteTask(taskNumber);
                    } catch (NumberFormatException ex) {
                        ex.printStackTrace();
                    }
                }
            case "todo":
                if (remainingInput.trim().equals("")) {
                    throw new DukeException("No task description");
                }
                return tasks.addTask("todo", remainingInput, "");
            case "deadline":
                String[] deadlineTokens = remainingInput.split("\\s*/by\\s*");
                if (deadlineTokens.length == 0) {
                    throw new DukeException("No task description");
                } else if (deadlineTokens.length == 1) {
                    throw new DukeException("No task deadline");
                }
                String deadlineName = deadlineTokens[0];
                String deadlineReminder = deadlineTokens[1];
                return tasks.addTask("deadline", deadlineName, deadlineReminder);
            case "event":
                String[] eventTokens = remainingInput.split("\\s*/at\\s*");
                if (eventTokens.length == 0) {
                    throw new DukeException("No task description");
                } else if (eventTokens.length == 1) {
                    throw new DukeException("No task duration");
                }
                String eventName = eventTokens[0];
                String eventReminder = eventTokens[1];
                return tasks.addTask("event", eventName, eventReminder);
            case "find":
                if (remainingInput == null) {
                    throw new DukeException("unspecified keyword to search for");
                } else {
                    return tasks.findTasks(remainingInput);
                }
            default:
                throw new DukeException("Unknown Input"); // unknown input
        }
    }

    /**
     * Alerts the user if the duke.txt could not be loaded.
     *
     * @return A string alerting the user of the error.
     */
    public String showLoadingError() {
        return ("Loading error: duke.txt could not be loaded");
    }

}
