package duke.ui;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

public class Ui {

    public String readCommand() {
        Scanner sc= new Scanner(System.in);
        String userInput = sc.nextLine();
        return userInput;
    }

    private void sendMessage(String message) {
        String start = "_____________________________________\n";
        String end = "\n_____________________________________";
        String output = start + message + end;
        System.out.println(output);
    }

    public void showWelcome() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public void showExit() {
        String bye = "Bye. Hope to see you again soon!";
        this.sendMessage(bye);
    }

    public void showAddTask(Task task, TaskList taskList) {
        String addTask = String.format("Got it. I've added this task:\n%s\nNow you have %d task(s) in the list.",
                task.toString(), taskList.totalTask());
        this.sendMessage(addTask);
    }

    public void showDeleteTask(Task task, TaskList taskList) {
        String deleteTask = String.format("Noted. I've removed this task:\n%s\nNow you have %d task(s) in the list.",
                task.toString(), taskList.totalTask()) ;
        this.sendMessage(deleteTask);
    }

    public void showDone(Task task) {
        String done = String.format("Nice! I've marked this task as done:\n%s", task.toString()) ;
        this.sendMessage(done);
    }

    public void showList(TaskList taskList) throws DukeException {
        int numTask = taskList.totalTask();
        String task;

        if (numTask == 0) {
            task = "";
        } else {
            task = "1. " + taskList.getTask(1).toString();
            for(int taskNumber = 2; taskNumber <= numTask; taskNumber++) {
                task = task + "\n" + taskNumber + ". " + taskList.getTask(taskNumber).toString();
            }
        }

        String listStatement = "Here are the tasks in your list:\n";
        String output = listStatement + task;
        this.sendMessage(output);
    }

    public void showLost() {
        String lost = "OOPS!!! I'm sorry, but I don't know what that means :-(";
        this.sendMessage(lost);
    }

    public void showError(String errorMessage) {
        this.sendMessage(errorMessage);
    }

    public void showLoadingError() {
        this.sendMessage("OOPS!!! There is a loading error.");
    }

}
