package duke;

import duke.logic.LCommandParser;
import duke.logic.LCommandsEnum;
import duke.logic.LStorage;
import duke.task.Task;
import duke.task.TaskList;

import java.util.List;
import java.util.Scanner;

import static java.util.AbstractMap.SimpleImmutableEntry;

/**
 * The user interface of Duke. Deals with printing messages to the console and reading user input.
 */
public class Ui {
    private final Scanner sc;
    private final String name;
    private boolean willExit;

    /**
     * Creates a new instance of a user interface by creating a new scanner and querying for the user's name.
     */
    public Ui() {
        sc = new Scanner(System.in);
        String name = "";
        willExit = false;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello, I am\n" + logo);
        System.out.println("Please enter your name:");
        while (name.equals("")) {
            name = sc.nextLine();
        }
        this.name = name;
        System.out.printf("%s, that is a nice name. What can I do for you today?\n", this.name);
        System.out.println("----------------------------");

    }

    public boolean willExit() {
        return willExit;
    }

    /**
     * Checks the user input from stdin.
     *
     * @param taskList the task list that the user is using
     * @param storage  the storage that the user wants the data to be stored into
     */
    public void checkInput(TaskList taskList, LStorage storage) {
        String userInput = sc.nextLine();
        try {
            willExit = (new LCommandParser(userInput, taskList, storage, this)).willExit();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("----------------------------");
        }
    }

    /**
     * Called when the user wants to exit the program.
     */
    public void sayGoodBye() {
        System.out.println("Bye, " + name + "! Hope to see you again soon.");
        sc.close();
    }

    /**
     * Called when the user successfully adds the task to tasklist.
     *
     * @param task       the task that is added
     * @param sizeOfList the number of tasks so far
     */
    public void addTaskMessage(Task task, int sizeOfList) {
        System.out.println("Got it, " + name + ". I have added this task:");
        System.out.println("    " + task);
        System.out.println("Now you have " + sizeOfList + " task"
                + (sizeOfList <= 1 ? " in the list" : "s in the list"));
    }

    /**
     * Called when the user removes a task from the task list.
     *
     * @param task the task that is removed or deleted
     * @param size the final size of the task list
     */
    public void removeTaskMessage(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        displayTask(task);
        displayNumberOfTasks(size);
    }

    public void markAsDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done: ");
        displayTask(task);
    }

    /**
     * Displays the help message that contains documentations of the commands.
     */
    public void displayHelp(LCommandsEnum input) {
        if (input == null) {
            for (LCommandsEnum command : LCommandsEnum.values()) {
                System.out.println(command.helpMessage());
            }
        } else {
            System.out.println(input.helpMessage());
        }
    }

    /**
     * Prints all tasks from a list of tasks and task numbers.
     *
     * @param tasksWithTaskNumbers the list of tasks and task numbers, each in a SimpleImmutableEntry
     * @param maxTaskNumber the size of the task list. This will ensure proper padding of numbers.
     */
    public void printAllTasks(List<SimpleImmutableEntry<? extends Task, Integer>> tasksWithTaskNumbers,
                              int maxTaskNumber) {
        System.out.println("Ok, " + name + ". I am getting all your tasks:");
        printMultipleTasks(tasksWithTaskNumbers, maxTaskNumber);
    }

    /**
     * Prints all upcoming tasks from a list of tasks and task numbers.
     *
     * @param tasksWithTaskNumbers the list of tasks and task numbers, each in a SimpleImmutableEntry
     * @param maxTaskNumber the size of the task list. This will ensure proper padding of numbers.
     */
    public void printUpcomingTasks(List<SimpleImmutableEntry<? extends Task, Integer>> tasksWithTaskNumbers,
                                   int maxTaskNumber) {
        System.out.println("Ok, " + name + ". I am getting all your upcoming tasks:");
        printMultipleTasks(tasksWithTaskNumbers, maxTaskNumber);
    }

    /**
     * Prints all tasks from a list of tasks and task numbers containing a pattern.
     *
     * @param pattern the string pattern that the user is searching for
     * @param tasksWithTaskNumbers the list of tasks and task numbers, each in a SimpleImmutableEntry
     * @param maxTaskNumber the size of the task list. This will ensure proper padding of numbers.
     */
    public void printTasksContaining(String pattern, List<SimpleImmutableEntry<? extends Task, Integer>> tasksWithTaskNumbers,
                                     int maxTaskNumber) {
        System.out.println("Ok, " + name + ". I am getting all tasks containing " + pattern + ":");
        printMultipleTasks(tasksWithTaskNumbers, maxTaskNumber);
    }

    private void printMultipleTasks(List<SimpleImmutableEntry<? extends Task, Integer>> tasksWithTaskNumbers,
                                    int maxTaskNumber) {
        for (SimpleImmutableEntry<? extends Task, Integer> task : tasksWithTaskNumbers) {
            printSingleTask(task.getKey(), task.getValue(), maxTaskNumber);
        }
    }

    private void printSingleTask(Task task, int number, int max) {
        String leadingSpace = " ".repeat((int) Math.log10(max) - (int) Math.log10(number));
        // For better formatting if numbers exceed 9
        System.out.printf("%s%d: %s\n", leadingSpace, number, task);
    }

    private void displayTask(Task task) {
        System.out.println("    " + task);
    }

    private void displayNumberOfTasks(int num) {
        System.out.println("Now you have " + num + " task"
                + (num <= 1 ? " in the list" : "s in the list"));
    }
}
