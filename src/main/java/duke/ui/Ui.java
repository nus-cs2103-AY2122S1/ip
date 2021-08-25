package duke.ui;

import duke.commands.Task;

import java.util.ArrayList;

/**
 * Encapsulates the handling of user interactions of duke.
 *
 * @author Zhi Bin
 * @version Duke Level 8
 */
public class Ui {
    private static final String INDENTATION = "    ";
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    /** Introduction message with duke's logo. */
    private static final String INTRO = "Hello from\n"
            + INDENTATION + " ____        _        \n"
            + INDENTATION + "|  _ \\ _   _| | _____ \n"
            + INDENTATION + "| | | | | | | |/ / _ \\\n"
            + INDENTATION + "| |_| | |_| |   <  __/\n"
            + INDENTATION + "|____/ \\__,_|_|\\_\\___|\n"
            + INDENTATION + "What can I do for you?";

    public static void printMessageWithFormat(String msg){
        System.out.println(INDENTATION + HORIZONTAL_LINE);
        System.out.println(INDENTATION + msg);
        System.out.println(INDENTATION + HORIZONTAL_LINE);
    }

    /** Prints formatted message when duke start. */
    public void greet(){
        printMessageWithFormat(INTRO);
    }

    /** Prints formatted message when the user exits duke. */
    public void farewellMsg(){
        printMessageWithFormat("Bye bye, i go sleep already. See you again.");
    }

    /**
     * Prints formatted message when a task is added from the task list.
     * Message includes the descriptions of the task and number of task in the task list.
     *
     * @param taskLeft Number of task in the task list after adding the task.
     * @param task The task to be added.
     */
    public void addTaskMsg(int taskLeft, Task task){
        String msg = "Got it. I've added this task:\n" + INDENTATION + "  " + task.checkStatus();
        msg += String.format("\n%sNow you have %d tasks in the list.", INDENTATION, taskLeft);
        printMessageWithFormat(msg);
    }

    /**
     * Prints formatted message when a task is deleted from the task list.
     * Message includes the descriptions of the task and number of task in the task list.
     *
     * @param taskLeft Number of task in the task list after deleting the task.
     * @param task The task to be deleted.
     */
    public void deleteTaskMsg(int taskLeft, Task task){
        String msg = "Noted. I've removed this task:\n" + INDENTATION + "  " + task.checkStatus();
        msg += String.format("\n%sNow you have %d tasks in the list.", INDENTATION, taskLeft);
        printMessageWithFormat(msg);
    }

    /**
     * Prints formatted message when a task is mark as done.
     * Message includes the descriptions of the task and number of task in the task list.
     *
     * @param task The task to be marked.
     */
    public void markDoneMsg(Task task){
        String msg = "Nice! I've marked this task as done:\n   ";
        msg += INDENTATION + task.checkStatus();
        printMessageWithFormat(msg);
    }

    /**
     * Prints formatted message showing the list of task, including the description of the task.
     *
     * @param taskList The task list to be printed.
     */
    public void listTasks(ArrayList<Task> taskList){
        String msg = "Here are the tasks in your list:";
        for (int i = 1; i <= taskList.size(); i++){
            msg += String.format("\n%s%d. %s", INDENTATION, i, taskList.get(i-1).checkStatus());
        }
        printMessageWithFormat(msg);
    }
}
