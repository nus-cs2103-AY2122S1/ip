package duke;

/**
 * Class to contain all the UI elements of Duke.
 */

public class Ui {

    private static final String separator = "=======================================";

    public Ui() {

    }

    /**
     * Prints out the inputted text in the specified format.
     * @param text the text to be printed.
     */
    public static void print(String text) {
        System.out.println(separator);
        text.lines().map(x -> "    " + x).forEach(x -> System.out.println(x));
        System.out.println(separator);
    }

    /**
     * Marks a task as done.
     * @param doneEntry input for the task to be marked as done.
     * @throws DukeException if the task number inputted is invalid.
     */
    public static String done(String doneEntry) throws DukeException {
        int taskNumber = Integer.parseInt(doneEntry.substring(5, 6));
        if (taskNumber > TaskList.noOfTasks()) {
            throw new DukeException("Sorry, please enter a valid task to complete!");
        }
        duke.Task doneTask = TaskList.getCurrentTask(taskNumber - 1);
        doneTask.markAsDone();
        return "Congratulations on finishing this task!\n [X] " + doneTask.getDescription();
    }

    /**
     * Throws an exception when the User enters an invalid input.
     * @throws DukeException if the input entered by the user is invalid.
     */
    public static void invalidInput() throws DukeException {
        throw new DukeException("Sorry, please enter a valid command!");
    }

    /**
     * Prints out the list of tasks that the User currently has.
     */
    public static String list() {
        int len = TaskList.noOfTasks();
        String sentence = "";
        for (int i = 1; i < len + 1; i++) {
            Task currentTask = TaskList.getCurrentTask(i - 1);
            sentence = sentence + i + "." + currentTask.toString() + "\n";
        }
        if (len == 0) {
            return "You currently have no pending tasks!";
        } else {
            sentence = "Here are your current tasks!" + "\n" + sentence;
            return sentence;
        }
    }

    /**
     * Used to find matching tasks using the keyword inputted.
     * @param findWord key word that wants to be found.
     */
    public static String find(String findWord) {
        String word = findWord.substring(5);
        int len = TaskList.noOfTasks();
        String sentence = "";
        for (int i = 1; i < len + 1; i++) {
            Task currentTask = TaskList.getCurrentTask(i - 1);
            String currentDescription = currentTask.getDescription();
            if (currentDescription.contains(word)) {
                sentence = sentence + i + "." + currentTask.toString() + "\n";
            }
        }
        if (sentence.equals("")) {
            return "Sorry! I can't find any task that matches your keyword";
        } else {
            sentence = "Here are the matching tasks I found:" + "\n" + sentence;
            return sentence;
        }
    }

    public static String alexaGreeting() {
        return "Hello! My name is Alexa \nHow can I help you today?";
    }
}
