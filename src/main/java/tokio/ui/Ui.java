package tokio.ui;

import java.util.Scanner;

import tokio.tasks.Task;
import tokio.tasks.TaskList;

/**
 * deals with interactions with the user.
 */
public class Ui {
    /**
     * Reads line of command.
     *
     * @return String of command.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Prints welcome message, to be shown when Duke starts up at the beginning.
     */
    public String printWelcome() {
        return "Hola Rio!\n" + "I am your Tokio.\n" + "What would you like to do now?";
    }

    /**
     * Prints bye message, to be shown when user terminates Duke with "Bye" command.
     */
    public String printBye() {
        return "Come back soon Rio! \n" + "I'll miss you <3";
    }

    /**
     * Shows all the items in the task list.
     *
     * @param tasks Task list to be printed.
     */
    public String printList(TaskList tasks) {
        StringBuilder listMsg = new StringBuilder("Sure Rio! Here's everything on your list:\n");
        for (int i = 0; i < tasks.getSize(); i++) {
            int taskNo = i + 1;
            Task curr = tasks.getTask(i);
            listMsg.append(taskNo).append(". ").append(curr.toString()).append("\n");
        }
        return listMsg.toString();
    }

    /**
     * Prints the entire tasks list when user uses "find" command.
     *
     * @param taskList Task list to be printed.
     * @param keyword Search task list and only print items that matches the keyword.
     */
    public String printFindResults(TaskList taskList, String keyword) {
        StringBuilder findResultsMsg;
        if (taskList.getSize() > 0) {
            findResultsMsg = new StringBuilder("Sure Rio!\n" + "This is what I have found based on the keyword: \""
                    + keyword + "\"\n");
            for (int i = 0; i < taskList.getSize(); i++) {
                int taskNo = i + 1;
                Task curr = taskList.getTask(i);
                findResultsMsg.append(taskNo).append(". ").append(curr.toString()).append("\n");
            }
        } else {
            findResultsMsg = new StringBuilder("Oops Rio, based on your keyword: \""
                    + keyword + "\", I am not able to find any match :(");
        }
        return findResultsMsg.toString();
    }

    /**
     * Prints success message when task has been added to task list successfully.
     *
     * @param task Task to be added.
     * @param taskList Task list which task will be added it.
     * @return Success message.
     */
    public String printAddCommand(Task task, TaskList taskList) {
        return "Sure Rio! I have added this task to your list:\n" + "\"" + task.toString() + "\"\n"
                + "You have " + taskList.getSize() + " task(s) on your list!";
    }

    /**
     * Prints success message when task is marked as done successfully.
     *
     * @param task Task to be marked as done.
     * @param taskList Task list which task is from.
     * @return Success message.
     */
    public String printDoneCommand(Task task, TaskList taskList) {
        return "Good job on completing your task Rio!\n" + "I have marked this task as done:\n \""
                + task.toString() + "\"\n" + "You have " + taskList.getSize() + " task(s) on your list.";
    }

    /**
     * Prints warning message to indicate that the task list is empty.
     *
     * @return Message.
     */
    public String printEmptyList() {
        return "Wow Rio, there's nothing on your list!\n"
                + "Feel free to let me know if you would like to add anything.";
    }

    /**
     * Prints success message to indicate that task has been removed from task list.
     *
     * @param task Task to be deleted.
     * @param taskList Task list which contains the task to be deleted.
     * @return Success message.
     */
    public String printDeleteCommand(Task task, TaskList taskList) {
        return "Okay Rio, I have deleted this task from your list:\n" + task.toString()
                + "\nYou have " + taskList.getSize() + " task(s) left on your list.";
    }

}
