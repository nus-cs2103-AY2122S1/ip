import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;
    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLoadingError() {
        System.out.println("Error loading data file, starting with empty TaskList.");
    }

    public void showError(String errorMessage) {
        System.out.println("☹ OOPS!!! " + errorMessage);
    }

    public void showLine() {
        System.out.println("_____________________");
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }

    public void showAddedNewTask(Task task, TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        showNumberOfTasks(tasks);
    }

    private void showNumberOfTasks(TaskList tasks) {
        System.out.println("Now you have " + tasks.getLength() + " tasks in the list.");
    }
    public void showRemovedTask(Task task, TaskList tasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        showNumberOfTasks(tasks);
    }

    public void showMarkedAsDone(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }

    public void showTaskList(TaskList tasks) {
        for (int i = 0; i < tasks.getLength(); i++) {
            System.out.println((i + 1) + "." + tasks.getTask(i).toString());
        }
    }

    public void showOccurringTasks(LocalDate date, TaskList tasks) {
        System.out.println("Tasks occurring on " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":");
        int count = 0;
        ArrayList<Task> relevantTasks = tasks.getTasksOccurringOn(date);
        for (Task task: relevantTasks) {
                System.out.println((count + 1) + ". " + task);
                count++;
            }
        if (relevantTasks.size() == 0) {
            System.out.println("No tasks.");
        }
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
