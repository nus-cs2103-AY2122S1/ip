package duke.main;

import duke.task.Task;
import duke.task.TaskList;

import java.util.List;
import java.util.Scanner;

public class Ui {
    private final Scanner inputScanner;

    public Ui() {
        this.inputScanner = new Scanner(System.in);
        greetOnStart();
    }

    public void greetOnStart() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public String getNextInput() {
        return inputScanner.nextLine();
    }

    public void closeInput() {
        inputScanner.close();
    }

    public void showEmptyInputException() {
        System.out.println("\tTake your time :)\n");
    }

    public void showUnknownCommandException(String command) {
        System.out.println("\tI don't understand " + command + " (yet...)\n");
    }

    public void showDukeException(String message) {
        System.out.println(message);
    }

    public void printTaskList(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("\tYou haven't added any tasks yet\n");
        } else {
            System.out.println(tasks);
        }
    }

    /**
     * Displays matching tasks.
     *
     * @param matchingTasks to be displayed.
     */
    public void showMatchingTasks(List<Task> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            System.out.println("\tNo matching tasks found!\n");
        } else {
            System.out.println("\tHere are the matching tasks from your list:\n");
            System.out.println(TaskList.enumerateTasks(matchingTasks));
        }
    }

    public void greetWithFamiliarity(TaskList tasks) {
        System.out.println("\tNice to see you again.");
        System.out.println(tasks.taskSummary());
        if (!tasks.isEmpty()) {
            System.out.println(tasks);
        }
    }
}
