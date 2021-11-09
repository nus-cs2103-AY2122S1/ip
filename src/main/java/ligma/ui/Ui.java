package ligma.ui;

import ligma.TaskList;
import ligma.task.Task;

public class Ui {
    private static final String PARTITION = "______________________";

    /**
     * Returns all tasks from the tasklist given.
     *
     * @param tasks tasklist whose tasks are to be printed
     */
    public static String getStringTaskList(TaskList tasks) {
        if (tasks.isEmpty()) {
            return "No tasks.";
        }
        String[] tasksText = tasks.getStringArr();
        int len = tasksText.length;
        String reply = "1." + tasksText[0];
        for (int i = 1; i < len; i++) {
            reply += String.format("\n%d." + tasksText[i], i + 1);
        }
        return reply;
    }

    /**
     * Returns tasks that matches a target string for a FindCommand.
     *
     * @param tasks tasks that match the target string
     */
    public static String getMatches(Task[] tasks) {
        int len = tasks.length;
        String reply = "Found " + len + " matches:";
        for (int i = 0; i < len; i++) {
            reply += String.format("\n%d." + tasks[i], i + 1);
        }
        return reply;
    }

    /**
     * Returns Ligma's introduction.
     */
    public static String getIntroduction() {
        return "Hello! I'm Ligma, Ligma Balls.\nWhat can I do for you?";
    }

    /**
     * Returns Ligma's exit message.
     */
    public static String getFarewell() {
        return "Bye. I love Imagine Dragons...\n\n\n\n\n\n\n\n\n\n" +
                "Imagine Dragon Deez Nuts Cross Your Face.";
    }

    /**
     * Returns status of execution for successful execution of user command
     * @param commandDesc description of command executed
     * @return status message with details of command executed
     */
    public static String getSuccessMessage(String commandDesc) {
        return "Successfully " + commandDesc;
    }

    /**
     * Returns description of all commands in Ligma
     * @return help message for command formats
     */
    public static String getCommandManual() {
        String addDesc = "Creating tasks\n" +
                "1. Create a todo: todo {desc}\n" +
                "2. Create an event: event {desc} /at yyyy-mm-dd\n" +
                "3. Create a deadline: deadline {desc} /by yyyy-mm-dd\n" +
                "4. Create a recurring task: recur {desc} " +
                "/every {d/w/m/y} {specific time (1900)/day of week/date (1-30)/month}\n" +
                "eg. /every d 2100, /every w monday, /every m 15, /every y july\n";
        String deleteDesc = "Deleting tasks: delete {taskIndex}\n";
        String doneDesc = "Marking tasks as done: done {taskIndex}\n";
        String findDesc = "Finding tasks: find {targetString}\n";
        String listDesc = "Listing all tasks: list\n";
        String exitDesc = "Exiting conversation: bye\n";
        String helpDesc = "Getting help: help";
        return addDesc + deleteDesc + doneDesc + findDesc + listDesc + exitDesc + helpDesc;

    }

}
