package duke;

import duke.logic.LCommandParser;
import duke.logic.LCommandsEnum;
import duke.logic.LStorage;
import duke.task.Task;
import duke.task.TaskList;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

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
     * @param storage the storage that the user wants the data to be stored into
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
        System.out.println("Bye, " + name +"! Hope to see you again soon.");
        sc.close();
    }

    /**
     * Called when the user successfully adds the task to tasklist.
     *
     * @param task the task that is added
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
     * Prints all upcoming tasks from a list of tasks.
     *
     * @param tasks the list of tasks
     */
    public void printUpcomingTasks(List<? extends Task> tasks) {
        int taskSize = tasks.size();
        Map<Task, Integer> upcomingTasks = new TreeMap<>((task1, task2) -> {
            LocalDateTime dateTime1 = task1.getDateTime();
            LocalDateTime dateTime2 = task2.getDateTime();
            // time can be null if task is to-do. By default, put all to-do to the last.
            return dateTime1 == null
                    ? 1
                    : dateTime2 == null
                    ? -1
                    : dateTime1.compareTo(dateTime2);
        });
        for (int i = 1; i <= taskSize; i++) {
            Task task = tasks.get(i - 1);
            String type = task.getTaskType();
            if (!task.isDone() && // Task is not done and it is either to-do or the date is later than now.
                    (type.equals("T") || task.getDateTime().isAfter(LocalDateTime.now()))) {
                upcomingTasks.put(task, i);
            }
        }
        upcomingTasks.forEach((task, i) -> print(task, i, taskSize));
    }

    /**
     * Prints all tasks from a list of tasks.
     *
     * @param tasks the list of tasks
     */
    public void printAllTasks(List<? extends Task> tasks) {
        int taskSize = tasks.size();
        for (int i = 1; i <= taskSize; i++) {
            Task task = tasks.get(i - 1);
            print(task, i, taskSize);
        }
    }

    private void print(Task task, int number, int max) {
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
