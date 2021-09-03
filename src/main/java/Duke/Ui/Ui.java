package Duke.Ui;

import Duke.Tasks.Task;
import Duke.Tool.TaskList;

import java.util.Scanner;
import java.lang.String;
import java.lang.StringBuilder;

/**
 * Represents Ui class: deals with interactions with the user
 */
public class Ui {

    private final static String INDENTATION ="  ";

    /**
     * The showWelcome method after showLogo
     */
    public String showWelcome(){
        return "Hello! I'm Duke \n" +
                "What can I do for you?";
    }

    /**
     * The method of exit
     */
    public static String exit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * The method of showLoadingError
     */
    public String showLoadingError() {
        return "Loading error! please try again";
    }

    /**
     * The method of getCommand
     * @return String
     */
    public String getCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * The method of showDoneMessage
     * @param task
     * @param numRemoved
     */
    public String showDoneMessage(TaskList task, int numRemoved) {
        return "Nice! I've marked this task as done:\n" +
                INDENTATION+ task.get(numRemoved);
    }

    /**
     * The method of showDeletedMessage
     * @param task
     * @param taskDeleted
     */
    public String showDeletedMessage(TaskList task, Task taskDeleted) {
        return "Noted. I've removed this task:\n" +
                INDENTATION + taskDeleted +
                "Now you have " + task.size()  + " tasks in the list.";
    }

    /**
     * The method of showAddOnTask
     * @param task
     * @param numAdded
     */
    public String showAddOnTask(TaskList task, int numAdded) {
        return "Got it. I've added this task:\n" +
                INDENTATION + task.get(numAdded) + //toString in Deadline or Event
                "Now you have " + (numAdded + 1) + " tasks in the list.";
    }

    /**
     * The method of showListDetails
     * @param task
     */
    public String showListDetails(TaskList task) {
        StringBuilder showListDetailsString = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < task.size(); i ++) {
            showListDetailsString.append((i + 1) + "." + INDENTATION + task.get(i) + "\n");
        }
        return showListDetailsString.toString();
    }

    /**
     * The method of showFindDetails
     * @param task
     * @param findTarget
     */
    public String showFindDetails(TaskList task, String findTarget) {

        StringBuilder showFindDetailsString = new StringBuilder("Here are the matching tasks in your list:");
        for (int i = 0; i < task.size(); i++) {
            if(task.getTasks().get(i).getDescription().contains(findTarget)) {
                showFindDetailsString.append(INDENTATION + (i + 1) + "." + INDENTATION + task.get(i));
            }
        }
       return showFindDetailsString.toString();
    }

}
