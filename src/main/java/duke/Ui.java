package duke;

import duke.task.Task;
import duke.task.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * Class that encapsulates user interaction.
 */
public class Ui {
    private final Scanner sc = new Scanner(System.in);

    /**
     * Displays a separator.
     */
    public void showLine() {
        System.out.println("__________________________________________");
    }

    /**
     * Displays the welcome message.
     */
    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Talky McTalkFace");
        System.out.println("What can I do for you?");
        showLine();
    }

    /**
     * Displays the error message.
     * @param message The error message.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Prompts the user for input and reads the input.
     * @return The user input.
     */
    public String readCommand() {
        System.out.println("What would you like to do?");
        return sc.nextLine();
    }

    /**
     * Display file created message.
     * @param fileName The file name.
     */
    public void showFileCreated(String fileName) {
        System.out.println("New Duke file created: " + fileName);
    }

    /**
     * Display file exists message.
     */
    public void showFileExists() {
        System.out.println("YAY! Duke file already exists.");
    }

    /**
     * Display task done message.
     * @param task The completed task.
     */
    public void showTaskDone(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task.toString());
    }

    /**
     * Displays add task message.
     * @param task The added task.
     * @param taskNo Task number.
     * @param t "task" or "tasks".
     */
    public void showAddTask(Task task, int taskNo, String t) {
        System.out.println("Got it. I've added this task:\n"
                + task.toString()
                + "\n"
                + "Now you have "
                + taskNo
                + t
                + "in the list.");
    }

    /**
     * Displays delete task message.
     * @param task The deleted task.
     * @param tasksLeft Number of tasks left.
     * @param t "task" or "tasks"
     */
    public void showDeleteTask(Task task, int tasksLeft, String t) {
        System.out.println("Noted. I've removed this task:\n"
                + task.toString()
                + "\n"
                + "Now you have "
                + tasksLeft
                + t
                + "in the list.");
    }

    /**
     * Displays tasks on a specified date.
     * @param date Specified date.
     * @param num Number of tasks.
     * @param tasks List of tasks occuring on that date.
     */
    public void showTasksOnDate(LocalDate date, int num, List<Task> tasks) {
        String d = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String s = num == 0
                ? "You have 0 tasks occurring on " + d + "."
                : num == 1
                ? "You have 1 task occurring on " + d + ":"
                : "You have " + num + " tasks occurring on " + d + ":";

        System.out.println(s);
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    /**
     * Displays all the tasks in the list.
     * @param tasks List of the tasks.
     */
    public void printList(TaskList tasks) {
        System.out.print("Here are the tasks in your list:\n" +
                tasks.saveList());
    }

    /**
     * Displays tasks in the list that matches a keyword.
     * @param tasks The list of matching tasks.
     */
    public void showMatchingTasks(TaskList tasks) {
        System.out.print("Here are the matching tasks in your list:\n" +
                tasks.saveList());
    }

    /**
     * Displays all the accepted commands.
     */
    public void showHelp() {
        System.out.println("Here is the list of commands I can understand:");
        System.out.println("list: displays your task list.");
        System.out.println("todo <task>: adds a todo to your task list.");
        System.out.println("deadline <task> /by <yyyy-mm-dd>: adds a deadline to your task list.");
        System.out.println("event <task> /at <yyyy-mm-dd>: adds an event to your tasks list.");
        System.out.println("done <task number>: marks the specific task as done.");
        System.out.println("delete <task number>: deletes the specific task from your task list.");
        System.out.println("occurring on <yyyy-mm-dd>: displays tasks occurring on the specified day.");
        System.out.println("find <keyword>: displays tasks that contain the keyword.");
        System.out.println("bye: quits the program.");
    }

    /**
     * Displays the farewell message.
     */
    public void showFarewell() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
