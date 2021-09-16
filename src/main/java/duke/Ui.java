package duke;

import duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * A class that contains methods to handle i/o operations through screen
 */
public class Ui {
    /**
     * Reads command line string from the user.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        System.out.println("ENTER COMMAND:");
        System.out.print("\t");
        return sc.nextLine().trim();
    }

    /**
     * Displays goodbye message on exiting.
     */
    public void exit() {
        String message = "\t" + "Bye. Hope to see you again soon!";
        displayResponse(message);
    }

    /**
     * Greets the user with a welcome message.
     */
    public void displayWelcome() {
        displayLine();
        displayWelcomeMessage();
        displayMenuOptions();
        displayLine();
    }

    /**
     * Displays a message with proper formatting.
     * @param message Message to be displayed
     */
    public void displayResponse(String message) {
        displayLine();
        System.out.println(message);
    }

    /**
     * Dis[lays an error message with proper formatting
     * @param message Error message to be displayed
     */
    public void displayError(String message) {
        displayLine();
        System.out.println("ERROR MESSAGE:");
        System.out.println("\t" + "☹ " + message);
    }

    /**
     * Displays a line to seoarate different parts of a message
     */
    public void displayLine() {
        String horizontalLine = "____________________________________________________________";
        System.out.println(horizontalLine);
    }

    /**
     * Displays welcome message with proper formatting.
     */
    private void displayWelcomeMessage() {
        String message = "\t" + "Hello! I'm Duke, your Personal Assistant ChatBot."
                + System.lineSeparator()
                + "\t" + "What can I do for you?"
                + System.lineSeparator();
        System.out.println(message);
    }

    /**
     * Displays the menu of options.
     */
    private void displayMenuOptions() {
        String format = "%-25s%s%n";

        String menuOption1 = "\t" + "list:";
        String menuOption1Info = "List the tasks in your list";

        String menuOption2 = "\t" + "todo ABC:";
        String menuOption2Info = "Add a todo [T] task, ABC, into your list";

        String menuOption3 = "\t" + "deadline ABC /by XYZ:";
        String menuOption3Info = "Add a deadline [D] task, ABC, into your list "
                + "and specify the date/time, XYZ, it needs to be completed by";

        String menuOption4 = "\t" + "event ABC /at XYZ:";
        String menuOption4Info = "Add an event [E] task, ABC, into your list "
                + "and specify the start and end date/time, XYZ";

        String menuOption5 = "\t" + "done N:";
        String menuOption5Info = "Mark a task number, N, as done";

        String menuOption6 = "\t" + "delete N:";
        String menuOption6Info = "Delete a task number, N, from your list";

        String menuOption7 = "\t" + "print /on yyyy-mm-dd:";
        String menuOption7Info = "Print deadlines/events on a specific date";

        String menuOption8 = "\t" + "bye:";
        String menuOption8Info = "Exit the ChatBot";

        System.out.printf(format, menuOption1, menuOption1Info);
        System.out.printf(format, menuOption2, menuOption2Info);
        System.out.printf(format, menuOption3, menuOption3Info);
        System.out.printf(format, menuOption4, menuOption4Info);
        System.out.printf(format, menuOption5, menuOption5Info);
        System.out.printf(format, menuOption6, menuOption6Info);
        System.out.printf(format, menuOption7, menuOption7Info);
        System.out.printf(format, menuOption8, menuOption8Info);
    }

    /**
     * Displays a message when a new task is added.
     * @param task The task added
     * @return
     */
    public String taskAddedMessage(Task task) {
        return "\t" + "Got it. I've added this task:"
                + System.lineSeparator() + "\t\t" + task;
    }

    /**
     * Displays a message when a task is removed
     * @param task The task removed
     * @return
     */
    public String taskDeletedMessage(Task task) {
        return "\t" + "Noted. I've removed this task:"
                + System.lineSeparator() + "\t\t" + task;
    }

    /**
     * Displays a message when a task has been executed.
     * @param task The task executed
     * @return
     */
    public String taskDoneMessage(Task task) {
        return "\t" + "Nice! I've marked this task as done:"
                + System.lineSeparator() + "\t\t" + task;
    }

    /**
     * Gets a message containing the number of tasks.
     * @param taskList List of tasks
     * @return a string containing size of the list of tasks.
     */
    public String getNumOfTasksInList(TaskList taskList) {
        return "\t" + "Now you have " + taskList.size()
                + (taskList.size() > 1 ? " tasks" : " task")
                + " in the list.";
    }

    /**
     * Gets a message containing the list of tasks.
     * @param taskList List of tasks
     * @return a string containing the list of tasks
     */
    public String getTasksInList(TaskList taskList) {
        StringBuilder response = new StringBuilder("\t" + "Here are the tasks in your list:"
                + System.lineSeparator()
                + "\t" + "[Legend: T = todo, D = deadline, E = event]"
                + System.lineSeparator()
                + System.lineSeparator());

        for (int i = 0; i < taskList.size(); i++) {
            if (i != 0) {
                response.append(System.lineSeparator());
            }

            response.append("\t\t").append(i + 1).append(".")
                    .append("\t").append(taskList.get(i).toString());
        }

        return response.toString();
    }

    /**
     * Gets a message containing the list of tasks on a specified date.
     * @param dateStr The date corresponding which the tasks have to be found.
     * @param taskList List of all tasks
     * @return a string containing the list of tasks on the given date
     */
    public String getTasksOnDate(String dateStr, TaskList taskList) {
        TaskList taskOnDateList = new TaskList();

        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).isOnDate(dateStr)) {
                taskOnDateList.add(taskList.get(i));
            }
        }

        StringBuilder response;

        if (taskOnDateList.size() != 0) {
            response = new StringBuilder("\t" + "Here are the tasks on this date ("
                    + processDateStr(dateStr) + "):"
                    + System.lineSeparator()
                    + "\t" + "[Legend: T = todo, D = deadline, E = event]"
                    + System.lineSeparator()
                    + System.lineSeparator());

            for (int i = 0; i < taskOnDateList.size(); i++) {
                if (i != 0) {
                    response.append(System.lineSeparator());
                }

                response.append("\t\t").append(i + 1).append(".")
                        .append("\t").append(taskOnDateList.get(i).toString());
            }
        } else {
            response = new StringBuilder("\t" + "There are no tasks on this date.");
        }

        return response.toString();
    }

    /**
     * Converts a given date from local format to "mmm d yyyy" format.
     * @param dateStr A string containing date in local format
     * @return A string containing date in "mmm d yyyy" format
     */
    private String processDateStr(String dateStr) {
        LocalDate date;
        String processedDateStr;

        try {
            date = LocalDate.parse(dateStr);
            processedDateStr = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (Exception e) {
            processedDateStr = dateStr;
        }

        return processedDateStr;
    }
}
