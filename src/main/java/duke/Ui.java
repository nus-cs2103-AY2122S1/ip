package duke;


import java.util.Scanner;

public class Ui {
    private Scanner myScanner = new Scanner(System.in);

    public Ui() {
    }

    public String showExitMessage() {
        return "Bye. Hope to see you again soon!";
    }

    public String showLoadingError() {
        return "There is a problem loading saved data.";
    }
    
    public String showAddTask(Task task, int numOfTasks) {
        return "Got it. I've added this task:\n  "
                + task.toString() + "\nNow you have " + numOfTasks
                + " task(s) in the list.";
    }

    public String showDeleteTask(Task task, int numOfTasks) {
        return "Noted. I've removed this task:\n  "
                + task.toString() + "\nNow you have " + numOfTasks
                + " task(s) in the list.";
    }

    public String showTaskList(TaskList tasks) {
        return "Here are the tasks in your list:" + tasks.toString();
    }

    public String showFoundTasks(TaskList tasks) {
        return "Here are the matching tasks in your list:" + tasks.toString();
    }

    public String showTaskDone(Task task) {
        return "Nice! I've marked this task as done:\n  "
                + task.toString();
    }

    public String showError(String s) {
        return s;
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
