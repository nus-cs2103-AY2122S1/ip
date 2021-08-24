package duke;

import duke.Task;
import duke.TaskList;

import java.util.Scanner;

public class Ui {
    private Scanner myScanner = new Scanner(System.in);

    public Ui() {
    }


    public void showWelcome() {
        System.out.println("*******************************************");
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("*******************************************");
    }

    public void showExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("*******************************************");
    }

    public void showLoadingError() {
        System.out.println("There is a problem loading saved data.");
        System.out.println("*******************************************");
    }

    public void showSavingError() {
        System.out.println("There is a problem saving data.");
        System.out.println("*******************************************");
    }

    public void showMeaninglessInput() {
        System.out.println("I'm sorry, but I don't know what that means :-(");
        System.out.println("*******************************************");
    }

    public void showWrongTodoInput() {
        System.out.println("The description of a todo cannot be empty.");
        System.out.println("*******************************************");
    }

    public void showWrongEventInput() {
        System.out.println("The description and time of an event cannot be empty.");
        System.out.println("*******************************************");
    }

    public void showWrongTimeInput() {
        System.out.println("The time must be in this format: yyyy-MM-dd HH:mm");
        System.out.println("*******************************************");
    }

    public void showWrongDeadlineInput() {
        System.out.println("The description and time of a deadline cannot be empty.");
        System.out.println("*******************************************");
    }

    public void showWrongDeleteInput() {
        System.out.println("Please only enter an integer after command 'delete'!");
        System.out.println("*******************************************");
    }

    public void showTaskNotExist(String s) {
        System.out.println("Task number " + s + " does not exist!");
        System.out.println("*******************************************");
    }

    public void showAddTask(Task task, int numOfTasks) {
        System.out.println("Got it. I've added this task:\n  "
                + task.toString() + "\nNow you have " + numOfTasks
                + " task(s) in the list.");
        System.out.println("*******************************************");
    }

    public void showDeleteTask(Task task, int numOfTasks) {
        System.out.println("Noted. I've removed this task:\n  "
                + task.toString() + "\nNow you have " + numOfTasks
                + " task(s) in the list.");
        System.out.println("*******************************************");
    }

    public void showTaskList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        System.out.println(tasks.toString());
        System.out.println("*******************************************");
    }

    public void showFoundTasks(TaskList tasks) {
        System.out.println("Here are the matching tasks in your list:");
        System.out.println(tasks.toString());
        System.out.println("*******************************************");
    }

    public void showTaskDone(Task task) {
        System.out.println("Nice! I've marked this task as done:\n  "
                + task.toString());
        System.out.println("*******************************************");
    }

    public void showError(String s) {
        System.out.println(s);
        System.out.println("*******************************************");
    }

    public boolean hasUserInput() {
        return myScanner.hasNextLine();
    }

    public String readCommand() {
        String userInput = myScanner.nextLine();
        return userInput;
    }

    public void closeUserInput() {
        myScanner.close();
    }
}
