package duke.Ui;

import duke.Tasks.Task;
import duke.Tool.TaskList;

import java.util.Scanner;
import java.lang.String;
import java.lang.StringBuilder;

/**
 * Represents Ui class: deals with interactions with the user
 */
public class Ui {

    private final static String INDENTATION ="  ";

    /**
     * Shows welcome message
     *
     * @return String Welcome message
     */
    public String showWelcome(){
        return "Hello! I'm Duke \n" +
                "What can I do for you?";
    }

    /**
     * Exits application
     *
     * @return String exit message
     */
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Shows the loading error message
     *
     * @return string of showLoadingError
     */
    public String showLoadingError() {
        return "Loading error! please try again";
    }

    /**
     * Gets input command for CLI using
     * @return String
     */
    public String getCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Shows done message
     *
     * @param tasks
     * @param numRemoved
     * @return String details of done task
     */
    public String showDoneMessage(TaskList tasks, int numRemoved) {
        return "Nice! I've marked this task as done:\n" +
                INDENTATION+ tasks.get(numRemoved);
    }

    /**
     * Shows delete message
     *
     * @param tasks
     * @param taskDeleted
     * @return String details of deleted task
     */
    public String showDeletedMessage(TaskList tasks, Task taskDeleted) {
        return "Noted. I've removed this task:\n" +
                INDENTATION + taskDeleted +
                "Now you have " + tasks.size()  + " tasks in the list.";
    }

    /**
     * Shows addon task message
     *
     * @param tasks
     * @param numAdded
     * @return String details of addon task
     */
    public String showAddOnTask(TaskList tasks, int numAdded) {
        return "Got it. I've added this task:\n" +
                INDENTATION + tasks.get(numAdded) + "\n" + //toString in Deadline or Event or Period
                "Now you have " + (numAdded + 1) + " tasks in the list.";
    }

    /**
     * Shows the list of Details using stream
     *
     * @param tasks
     * @return String the list of all tasks
     */
    public String showListDetails(TaskList tasks) {
        String startSentence = "Here are the tasks in your list:\n";

        return tasks.getTasks().stream()
                .reduce(startSentence,
                        (string, singleTask) ->
                                string + (INDENTATION + (tasks.getTasks().indexOf(singleTask) + 1)
                                        + "." + singleTask +"\n"), (str1, str2) -> str1 + str2);
    }

    /**
     * Shows find message
     *
     * @param tasks
     * @param findTarget
     * @return String the list of find details
     */
    public String showFindDetails(TaskList tasks, String findTarget) {
        StringBuilder showFindDetailsString = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            if(tasks.getTasks().get(i).getDescription().contains(findTarget)) {
                showFindDetailsString.append(INDENTATION + (i + 1) + "." + INDENTATION + tasks.get(i) + "\n");
            }
        }
       return showFindDetailsString.toString();
    }

}
