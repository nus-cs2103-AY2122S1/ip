package duke;

import duke.tasktypes.Task;
import duke.tasktypes.TaskList;

/**
 * Deals with the interactions with the user.
 */
public class Ui {

    public Ui() {}

    /**
     * Shows introduction paragraph and instructions to user.
     */
    public void showIntro() {
        String dory = "      _\n"
                + "     | |\n"
                + "   __| |   ___    _ __   _   _\n"
                + "  /    |  /   \\  | /__| | | | |\n"
                + " |   O | |  O  | | |    | |_| |\n"
                + "  \\__,_|  \\___/  |_|     \\__, |\n"
                + "   ________________________/  |\n"
                + "  (__________________________/\n"
                + "\n"
                + " how to use:\n"
                + "     * type down something and i'll remember\n"
                + "         'todo' followed by your 'task'\n"
                + "             eg. todo go to sleep\n"
                + "         'deadline' followed by the '/by yyyy-mm-dd'\n"
                + "             eg. deadline finish test /by 2020-12-31\n"
                + "         'event' followed by the '/at yyyy-mm-dd'\n"
                + "             eg. event christmas /at 2020-10-10\n"
                + "     * type 'list' to show everything\n"
                + "     * type 'bye' to leave\n"
                + "     * type 'done' followed by the task number\n"
                + "       to mark it as done\n"
                + "     * type 'delete' followed by the task number\n"
                + "       to delete it from your list\n"
                + " >";

        String fish = "                              ....\n"
                + "                             /.... \\\n"
                + "   hi my name is dory    .-`\\ \\   `...')\n"
                + "   and i can help you   ( o   | |      (\n"
                + "    remember things      `-_ / /_./``-._)\n"
                + "                             `\\___\\";

        // introduction to chat bot
        System.out.println(fish);
        System.out.println(dory);
    }

    /**
     * Displays deleted task.
     * @param removed Deleted Task.
     * @param taskList TaskList containing remaining tasks.
     */
    public void displayDelete(Task removed, TaskList taskList) {
        System.out.println(" > i've removed this task:");
        System.out.println("  " + removed.toString());
        System.out.println("you have " + taskList.getSize() + " things in your list");
    }

    /**
     * Displays finished task.
     * @param done Done Task.
     * @param taskList TaskList containing remaining tasks.
     */
    public void displayDone(Task done, TaskList taskList) {
        System.out.println(" > i've marked this as done:");
        System.out.println("  " + done.toString());
    }

    /**
     * Displays goodbye message.
     */
    public void displayBye() {
        System.out.println(" > see you! hope to see you again :-)");
    }

    /**
     * Displays added task.
     * @param taskAdded Added task.
     */
    public void displayAdd(Task taskAdded) {
        System.out.println(" > added:");
        System.out.println("    " + taskAdded.toString());
    }

    /**
     * Displays invalid comment.
     */
    public void displayWrongCommand() {
        System.out.println("i'm not sure i know what you mean :-( try typing something\n"
                + "using 'todo', 'deadline' or 'event'");
    }

    /**
     * Displays taskList.
     * @param taskList TaskList with all the tasks.
     */
    public void displayList(TaskList taskList) {
        if (taskList.getSize() == 0) {
            System.out.println("add anything using 'todo', 'deadline' or 'event'");
        } else {
            System.out.println(" > here you go!");
            // loop through the arraylist to show everything
            for (int count = 0; count < taskList.getSize(); count++) {
                Task eachTask = taskList.get(count);
                int countFromOne = count + 1;
                System.out.println(countFromOne + ". " + eachTask.toString());
            }
        }
    }


    public void displayFind(TaskList taskList) {
        System.out.println(" > here are the matching tasks in your list!");
    }

    /**
     * Displays how many tasks are remaining in the list.
     * @param taskList TaskList with all tasks.
     */
    public void displayTaskListSize(TaskList taskList) {
        if (taskList.getSize() == 1) {
            System.out.println("you have " + taskList.getSize() + " thing in your list");
        } else if (taskList.getSize() > 1) {
            System.out.println("you have " + taskList.getSize() + " things in your list");
        } else {
            System.out.println("add anything using 'todo', 'deadline' or 'event'");
        }
    }

    /**
     * Displays straight line.
     */
    public void endOfCommand() {
        System.out.println("------------------------------------");
    }

    /**
     * Displays straight line.
     */
    public void endOfResponse() {
        System.out.println("==================================Oo");
    }

    /**
     * Displays error message
     * @param e Exception
     */
    public void showError(Exception e) {
        System.out.println(e.getMessage());
    }

}
