package duke;

import duke.task.Task;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    public static final String DEFAULT_SPACES = "    ";
    public static final String INDENTED_SPACES = "     ";
    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public Scanner input;
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Ui() {
        this.input = new Scanner(System.in);
    }

    public static void showWelcome() {
        Ui.showLine();
        System.out.println(DEFAULT_SPACES + "Hello! I'm Duke");
        System.out.println(DEFAULT_SPACES + "What can I do for you?");
        Ui.showLine();
    }

    public String readCommand() {
        return input.nextLine();
    }

    public static void listTasks(ArrayList<Task> taskList) {
        if (taskList.size() == 0) {
            System.out.println(DEFAULT_SPACES + "You have no tasks in your list!");
            System.out.println(DEFAULT_SPACES + "Try adding some tasks with todo, event, or deadline");
        } else {
            System.out.println(DEFAULT_SPACES + "Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(INDENTED_SPACES + (i + 1) + "." + taskList.get(i));
            }
        }
    }

    public static void finishTask(Task task) {
        System.out.println(DEFAULT_SPACES + "Nice! I've marked this task as done:");
        System.out.println(INDENTED_SPACES + task);
    }

    public static void remainingTasks(ArrayList<Task> tasks) {
        System.out.printf(DEFAULT_SPACES + "Now you have %d tasks in the list.%n", tasks.size());
    }

    public static void deleteTask(Task task) {
        System.out.println(DEFAULT_SPACES + "Noted. I've removed this task:");
        System.out.println(INDENTED_SPACES + task);
    }

    public static void endDuke() {
        System.out.println(DEFAULT_SPACES + "Bye. Hope to see you again soon!");
    }

    public static void numberFormatExceptionMessage() {
        System.out.println(DEFAULT_SPACES + "☹ OOPS!!! The value you inputted is not valid!");
    }

    public static void dateTimeParseExceptionMessage() {
        System.out.println(DEFAULT_SPACES + "☹ OOPS!!!  You used an invalid date! Hint: Use 'YYYY-MM-DD HH:mm'");
    }

    public static void arrayIndexOutOfBoundsExceptionMessage() {
        System.out.println(DEFAULT_SPACES + "☹ OOPS!!!  Did you miss a term?");
    }

    public static void toDoHint() {
        System.out.println(DEFAULT_SPACES + "Hint: add a description!");
    }

    public static void deadlineHint() {
        System.out.println(DEFAULT_SPACES + "Hint: Use /by to add a deadline!");
    }

    public static void eventHint() {
        System.out.println(DEFAULT_SPACES + "Hint: Use /at to add a timing for the event!");
    }

    public static void fileNotFoundError() {
        System.out.println("File not found! Please create an empty file in /ip/data named duke.txt to get started");
    }

    public static void createNewFile() {
        System.out.println(DEFAULT_SPACES + "Saved file not found! Creating a new file for you...");
    }

    public static void createNewDirectory() {
        System.out.println(DEFAULT_SPACES + "Directory does not exist! Creating a new directory for you...");
    }

    public static void addTask(Task task) {
        System.out.println(DEFAULT_SPACES + "Got it. I've added this task:");
        System.out.println(INDENTED_SPACES + task);
    }

    public static void numberOfTasks(ArrayList<Task> tasks) {
        System.out.printf(DEFAULT_SPACES + "Now you have %d tasks in the list.%n", tasks.size());
    }

    public static void defaultMessage() {
        System.out.println(DEFAULT_SPACES + "OOPS I did not quite understand that :(");
    }

    public static void showLine() {
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Given a task list which is the result of a search operation, prints out the results.
     *
     * @param taskList task list comprising matching search terms.
     */
    public static void listTasksSearchResults(ArrayList<Task> taskList) {
        if (taskList.size() == 0) {
            System.out.println(DEFAULT_SPACES + "There were no search results. Try another keyword!");
        } else {
            System.out.println(DEFAULT_SPACES + "Here are the matching tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(INDENTED_SPACES + (i + 1) + "." + taskList.get(i));
            }
        }
    }

    /**
     * Provides the hint for users when they use the find command.
     */
    public static void findHint() {
        System.out.println(DEFAULT_SPACES + "Add the keyword at the back of find!");
    }
}
