package duke;

import exception.DukeException;
import task.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    /**
     * Displays the Duke welcome message.
     */
    public void showWelcome() {
        showOpenLine();
        System.out.print("  Hello! I'm Duke.\n  What's up?\n");
        showCloseLine();
    }

    /**
     * Displays a line to represent the top of the Duke message box.
     */
    public void showOpenLine() {
        System.out.println("  __________________________________________________________________");
    }

    /**
     * Displays a line to represent the bottom of the Duke message box.
     */
    public void showCloseLine() {
        System.out.println("  __________________________________________________________________\n");
    }

    /**
     * Displays the user input prompt.
     */
    public void showInputPrompt() {
        System.out.print("> ");
    }

    /**
     * Reads the given user input.
     * 
     * @param input The Scanner used to take in user input.
     * @return A string of the given user input.
     */
    public String readCommand(Scanner input) {
        return input.nextLine().trim();
    }

    /**
     * Displays the program closing message.
     */
    public void showBye() {
        System.out.println("  See you next time!");
    }

    /**
     * Displays the current task list.
     * 
     * @param taskList The task list to be displayed.
     */
    public void showList(ArrayList<Task> taskList) {
        System.out.println("  Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); ++i) {
            System.out.println("  " + (i + 1) + "." + taskList.get(i).listEntry());
        }
    }

    /**
     * Displays a message to show that the given task has been set to done.
     * 
     * @param toSetDone The task that has been set to done.
     */
    public void showDone(Task toSetDone) {
        System.out.print("  Nice! I've marked this task as done:\n    "
                + toSetDone.listEntry() + "\n");
    }

    /**
     * Displays a message to show that the given task has been deleted.
     * 
     * @param deleted The task that has been deleted.
     * @param listLength The new length of the task list after the deletion.
     */
    public void showDelete(Task deleted, int listLength) {
        System.out.print("  Noted. I've removed this task:\n    "
                + deleted.listEntry()
                + "\n  Now you have " + listLength + " tasks in the list.\n");
    }

    /**
     * Displays a list of tasks in the current task list that takes place on the given date.
     *
     * @param taskList The task list.
     * @param listLength The length of the current task list.
     * @param desiredDate The date used to find tasks.
     */
    public void showDateFind(ArrayList<Task> taskList, int listLength, LocalDate desiredDate) {
        System.out.println("  Here are the tasks for the given day:");
        for (int i = 0; i < listLength; ++i) {
            Task currTask = taskList.get(i);
            if (currTask.isTodayTask(desiredDate)) {
                System.out.println("  " + (i + 1) + "." + taskList.get(i).listEntry());
            }
        }
    }

    /**
     * Displays a list of tasks in the current task list that contain the given keyword.
     *
     * @param taskList The task list.
     * @param listLength The length of the current task list.
     * @param keyword The keyword to look for in the task names.
     */
    public void showKeywordFind(ArrayList<Task> taskList, int listLength, String keyword) {
        System.out.println("  Here are the tasks with the given keyword:");
        for (int i = 0; i < listLength; ++i) {
            Task currTask = taskList.get(i);
            if (currTask.containsKeyword(keyword)) {
                System.out.println("  " + (i + 1) + "." + taskList.get(i).listEntry());
            }
        }
    }

    /**
     * Displays a message to show that the given task has been added to the task list.
     * 
     * @param newTask The task that has been added to the task list.
     * @param listLength The new length of the task list after the addition.
     */
    public void showAdd(Task newTask, int listLength) {
        System.out.print("  Got it. I've added this task:\n    "
                + newTask.listEntry()
                + "\n  Now you have " + listLength + " tasks in the list.\n");
    }

    /**
     * Displays the error message of the given DukeException.
     * 
     * @param e The DukeException whose error message is to be displayed.
     */
    public void showException(DukeException e) {
        System.out.println(e.getMessage());
    }

    /**
     * Displays the Duke initialisation message.
     */
    public void showInitialise() {
        System.out.println("  Loading Duke...");
    }

    /**
     * Displays a message to show that the hard disk's directory did not exist and has been created.
     */
    public void showNewDataDirectory() {
        System.out.println("  Data directory does not exist, it has been created!");
    }

    /**
     * Displays a message to show that the hard disk did not exist and has been created.
     */
    public void showNewHardDisk() {
        System.out.println("  Hard disk does not exist, a new one has been created!");
    }
}
