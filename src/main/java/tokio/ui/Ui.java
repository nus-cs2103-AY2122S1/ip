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
        return "Hola Río!\n" + "I am your chatbot, Tokio.\n" + "Let me know what do you need help in!";
    }

    /**
     * Prints bye message, to be shown when user terminates Duke with "Bye" command.
     */
    public String printBye() {
        return "Come back soon Río! \n" + "I'll miss you <3";
    }

    /**
     * Prints invalid type error, to be shown when Type command is not included in Instructions enum.
     */
    public String printInvalidTypeError() {
        return "Oh no, I do not understand this type...\n";
    }

    /**
     * Prints invalid index error, to be shown when index does not exist/is more than size of task list.
     */
    public String printInvalidIndexError() {
        return "Oh no Río, this index does not exist!\n" + "Please make sure that index < size of tasks";
    }

    /**
     * Shows all the items in the task list.
     *
     * @param tasks Task list to be printed.
     */
    public String printList(TaskList tasks) {
        StringBuilder listMsg = new StringBuilder("Sure Río! Here's everything on your list:\n");
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
            findResultsMsg = new StringBuilder("Okay Río, this is what I have found based on the keyword: " + keyword);
            for (int i = 0; i < taskList.getSize(); i++) {
                int taskNo = i + 1;
                Task curr = taskList.getTask(i);
                findResultsMsg.append(taskNo).append(". ").append(curr.toString()).append("\n");
            }
        } else {
            findResultsMsg = new StringBuilder("Oops Río, based on your keyword: "
                    + keyword + ", I am not able to find any match :(");
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
        return "Sure Río! I Have added " + task.toString() + " to your list.\n"
                + "Now you have a total of " + taskList.getSize() + " tasks!";
    }

    /**
     * Prints success message when task is marked as done successfully.
     *
     * @param task Task to be marked as done.
     * @param taskList Task list which task is from.
     * @return Success message.
     */
    public String printDoneCommand(Task task, TaskList taskList) {
        return "Good job on completing your task Río!\n" + "I have marked this task as done: "
                + task.toString() + "\nNow you have " + taskList.getSize() + " tasks left.";
    }

    /**
     * Prints warning message to indicate that the task list is empty.
     *
     * @return Message.
     */
    public String printEmptyList() {
        return "Wow Río, there's nothing on your list\n"
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
        return "Okay Río, I have deleted this task from you list:\n" + task.toString()
                + "\nNow, you have " + taskList.getSize() + " tasks left.";
    }

    /**
     * Prints warning message to indicate that command did not execute properly.
     *
     * @param cmdType Type of command.
     * @return Warning message.
     */
    public String printSomethingMissing(String cmdType) {
        return "Oh no Río, I think something is missing from this " + cmdType;
    }

    /**
     * Prints loading error message, to be shown during start of Duke.
     */
    public void printLoadingError() {
        System.out.println("Oh no, there was a problem loading this file...\n" + "Wanna try another file instead?");
    }

    /**
     * Prints file not found error message, to be shown during start of Duke.
     */
    public void printFileNotFoundError() {
        System.out.println("Oh no, I cannot find this file...\n" + "Wanna try another file instead?");
    }
}
