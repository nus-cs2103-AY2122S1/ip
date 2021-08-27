package Duke;

import Duke.Tasks.Task;
import Duke.Tool.TaskList;

import java.util.Scanner;
import java.lang.String;

/**
 * Represents Ui class: deals with interactions with the user
 */
public class Ui {

    private final static String UNDERLINE = "_________________________________";
    private final static String INDENTATION ="  ";

    /**
     * The method of showLogo at start
     */
    public void showLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
    }

    /**
     * The showWelcome method after showLogo
     */
    public static void showWelcome(){
        System.out.println(INDENTATION + UNDERLINE);
        System.out.println(INDENTATION + "Hello! I'm Duke.Duke\n" +
                INDENTATION + "What can I do for you?");
        System.out.println(INDENTATION + UNDERLINE);

    }

    /**
     * The method of exit
     */
    public static void exit() {
        System.out.println(INDENTATION + UNDERLINE);
        System.out.println(INDENTATION + "Bye. Hope to see you again soon!");
        System.out.println(INDENTATION + UNDERLINE);

    }

    /**
     * The method of showLoadingError
     */
    public void showLoadingError() {
        System.out.println("Loading error! please try again");
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
    public void showDoneMessage(TaskList task, int numRemoved) {
        System.out.println(INDENTATION + UNDERLINE);
        System.out.println(INDENTATION + "Nice! I've marked this task as done:");
        System.out.println(INDENTATION + " " + task.get(numRemoved));
        System.out.println(INDENTATION + UNDERLINE);

    }

    /**
     * The method of showDeletedMessage
     * @param task
     * @param taskDeleted
     */
    public void showDeletedMessage(TaskList task, Task taskDeleted) {
        System.out.println(INDENTATION + UNDERLINE);
        System.out.println(INDENTATION + "Noted. I've removed this task:");
        System.out.println(INDENTATION + " " + taskDeleted);
        System.out.println(INDENTATION + "Now you have " + task.size()  + " tasks in the list.");
        System.out.println(INDENTATION + UNDERLINE);

    }

    /**
     * The method of showAddOnTask
     * @param task
     * @param numAdded
     */
    public void showAddOnTask(TaskList task, int numAdded) {
        System.out.println(INDENTATION + UNDERLINE);
        System.out.println(INDENTATION + "Got it. I've added this task:");
        System.out.println(INDENTATION + INDENTATION + task.get(numAdded)); //toString in Deadline or Event
        System.out.println(INDENTATION + "Now you have " + (numAdded + 1) + " tasks in the list.");
        System.out.println(INDENTATION + UNDERLINE);
    }

    /**
     * The method of showListDetails
     * @param task
     */
    public void showListDetails(TaskList task) {
        System.out.println(INDENTATION + UNDERLINE);
        System.out.println(INDENTATION + "Here are the tasks in your list:");
        for (int i = 0; i < task.size(); i ++) {
            System.out.println(INDENTATION + (i + 1) + "." + INDENTATION + task.get(i));
        }
        System.out.println(INDENTATION + UNDERLINE);
    }

    /**
     * The method of showFindDetails
     * @param task
     * @param findTarget
     */
    public void showFindDetails(TaskList task, String findTarget) {
        System.out.println(INDENTATION + UNDERLINE);
        System.out.println(INDENTATION + "Here are the matching tasks in your list:");
        for (int i = 0; i < task.size(); i++) {
            if(task.getTasks().get(i).getDescription().contains(findTarget)) {
                System.out.println(INDENTATION + (i + 1) + "." + INDENTATION + task.get(i));
            }
        }
        System.out.println(INDENTATION + UNDERLINE);
    }

}
