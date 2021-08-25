package duke.ui;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

public class Ui {

    /**
     * Returns inputs entered by user.
     *
     * @return User's input.
     */
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

    /**
     * Shows welcome message when user start the program.
     */
    public void showWelcome() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Shows exit message when user exit the programs
     */
    public void showExit() {
        String bye = "Bye. Hope to see you again soon!";
        this.sendMessage(bye);
    }

    /**
     * Shows task added to the list.
     *
     * @param task Task that is added.
     * @param taskList The list of tasks.
     */
    public void showAddTask(Task task, TaskList taskList) {
        String addTask = String.format("Got it. I've added this task:\n%s\nNow you have %d task(s) in the list.",
                task.toString(), taskList.totalTask());
        this.sendMessage(addTask);
    }

    /**
     * Shows task that is deleted from the list.
     *
     * @param task Task that is deleted.
     * @param taskList The list of tasks.
     */
    public void showDeleteTask(Task task, TaskList taskList) {
        String deleteTask = String.format("Noted. I've removed this task:\n%s\nNow you have %d task(s) in the list.",
                task.toString(), taskList.totalTask()) ;
        this.sendMessage(deleteTask);
    }

    /**
     * Shows task that is marked as done.
     *
     * @param task Task that is marked as done.
     */
    public void showDone(Task task) {
        String done = String.format("Nice! I've marked this task as done:\n%s",
                task.toString()) ;
        this.sendMessage(done);
    }

    /**
     * Shows list of tasks that are stored.
     *
     * @param taskList The list of tasks that are stored.
     * @throws DukeException If task list has error.
     */
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

    /**
     * Tells user that Duke can't understand the input.
     */
    public void showLost() {
        String lost = "OOPS!!! I'm sorry, but I don't know what that means :-(";
        this.sendMessage(lost);
    }

    /**
     * Tells user the error that Duke faced
     *
     * @param errorMessage Error message to be shown to the user.
     */
    public void showError(String errorMessage) {
        this.sendMessage(errorMessage);
    }

    /**
     * Tells user that there is an error loading the file.
     */
    public void showLoadingError() {
        this.sendMessage("OOPS!!! There is a loading error.");
    }

}
