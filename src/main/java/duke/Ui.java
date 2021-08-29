package duke;

import duke.task.Task;
import duke.task.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Ui {
    private final Scanner sc = new Scanner(System.in);

    public void showLine() {
        System.out.println("__________________________________________");
    }

    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Talky McTalkFace");
        System.out.println("What can I do for you?");
        showLine();
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public String readCommand() {
        System.out.println("What would you like to do?");
        return sc.nextLine();
    }

    public void showFileCreated(String fileName) {
        System.out.println("New Duke file created: " + fileName);
    }

    public void showFileExists() {
        System.out.println("YAY! Duke file already exists.");
    }

    public void showTaskDone(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task.toString());
    }

    public void makeTaskSuffix(Task task, int taskNo, String t) {
        System.out.println("Got it. I've added this task:\n"
                + task.toString()
                + "\n"
                + "Now you have "
                + taskNo
                + t
                + "in the list.");
    }

    public void showDeleteTask(Task task, int tasksLeft, String t) {
        System.out.println("Noted. I've removed this task:\n"
                + task.toString()
                + "\n"
                + "Now you have "
                + tasksLeft
                + t
                + "in the list.");
    }

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

    public void printList(TaskList tasks) {
        System.out.print("Here are the tasks in your list:\n" +
                tasks.saveList());
    }

    public void showHelp() {
        System.out.println("Here is the list of commands I can understand:");
        System.out.println("list: displays your task list.");
        System.out.println("todo <task>: adds a todo to your task list.");
        System.out.println("deadline <task> /by <yyyy-mm-dd>: adds a deadline to your task list.");
        System.out.println("event <task> /at <yyyy-mm-dd>: adds an event to your tasks list.");
        System.out.println("done <task number>: marks the specific task as done.");
        System.out.println("delete <task number>: deletes the specific task from your task list.");
        System.out.println("occurring on <yyyy-mm-dd>: displays tasks occurring on the specified day.");
        System.out.println("bye: quits the program.");
    }

    public void showFarewell() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
