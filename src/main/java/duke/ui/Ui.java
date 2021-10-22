package duke.ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Class that deals with interactions with the user.
 */
public class Ui {
    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Displays a default welcome message printed when user starts the program.
     */
    public void welcomeMessage() {
        lineGenerator();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        lineGenerator();
    }

    /**
     * Returns a standard welcome message for Duke GUI
     *
     * @return A standard welcome message {@code String} for Duke GUI
     */
    public static String guiWelcomeMessage() {
        return "Hello, I'm Duke\nWhat can I do for you?\n"
                + " ___            _        \n"
                + "|   _  \\ _   _ |  |  ___ \n"
                + "|  |  |   |  |  |   |  |/ / __ \\\n"
                + "|  |_|  |  |_|   |   <  __/\n"
                + "|___/ \\___|_|\\_\\__|\n";
    }

    public void lineGenerator() {
        System.out.println("____________________________________________________________\n");
    }

    public void enterCommand() {
        System.out.print("Enter command: ");
    }

    public String readInput() {
        return sc.nextLine();
    }

    public String printError(Exception e) {
        return e.getMessage();
    }

    public String taskListMessage() {
        return "Here are the tasks in your list: ";
    }

    /**
     * Returns a standard message signalling task is marked as done.
     *
     * @param t The task to be marked as done.
     * @return A {@code String} signalling task is marked as done.
     */
    public String taskDone(Task t) {
        return "Nice! I've marked this task as done: \n  " + t + '\n';
    }

    public String printTaskLength(TaskList tasks) {
        return "Now you have " + tasks.numberOfTasks() + " tasks in the list.\n";
    }

    /**
     * Returns a standard message by hour of the day.
     *
     * @param time
     * @return A {@code String} by hour of the day.
     */
    public String printScheduleByHourMessage(LocalTime time) {
        time = time.withMinute(0);
        return time.format(DateTimeFormatter.ofPattern("hh:mm a")) + " to "
                + time.plusHours(1).format(DateTimeFormatter.ofPattern("hh:mm a")) + ":\n";
    }

    /**
     * Returns a standard message signalling task is added.
     *
     * @param t The task to be added.
     * @return A {@code String} signalling task is added.
     */
    public String addTaskMessage(Task t) {
        return "Got it. I've added this task: \n  " + t + '\n';
    }

    public String byeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns a standard message signalling task is deleted.
     *
     * @param t The task to be deleted.
     * @return A {@code String} signalling task is deleted.
     */
    public String deleteTask(Task t) {
        return "Noted! I've removed this task: \n  " + t + '\n';
    }

    public static String showLoadingError() {
        return "No past saved data!\n";
    }

    /**
     * Returns a standard message with a list of matching tasks.
     *
     * @return A {@code String} with a list of matching tasks.
     */
    public String matchTaskMessage() {
        return "Here are the matching tasks in your list: \n";
    }

    public String viewScheduleMessage(LocalDate date) {
        return "Here are your tasks for " + date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)) + ':';
    }

    /**
     * Returns a standard message signalling that no task is scheduled for a particular date.
     *
     * @param date The chosen date of interest.
     * @return A {@code String} signalling that no task is scheduled for a particular date.
     */
    public String noTaskScheduledMessage(LocalDate date) {
        return "You have no tasks scheduled for "
                + date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)) + ".";
    }
}
