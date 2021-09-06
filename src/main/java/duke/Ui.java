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
     *
     * @param removed deleted task.
     * @param taskList TaskList containing remaining tasks.
     */
    public String displayDelete(Task removed, TaskList taskList) {
        String defaultLine = " > i've removed this task:";
        String task = "  " + removed.toString();
        String itemsLeft = "you have " + taskList.getSize() + " things in your list";
        String resultingLine = defaultLine + '\n' + task + '\n' + itemsLeft;
        System.out.println(resultingLine);
        return resultingLine;
    }

    /**
     * Displays clashing event.
     *
     * @param dateOfEvent date at which event clashes.
     * @return response back to user that there is a clashing event.
     */
    public String displayClashDate(String dateOfEvent) {
        String defaultLine = " > you have an existing event on the same date:";
        String date = "  " + dateOfEvent;
        String resultingLine = defaultLine + '\n' + date;
        System.out.println(resultingLine);
        return resultingLine;
    }

    /**
     * Displays finished task.
     *
     * @param done done task.
     * @param taskList TaskList containing remaining tasks.
     */
    public String displayDone(Task done, TaskList taskList) {
        String defaultLine = " > i've marked this as done:";
        String task = "  " + done.toString();
        String resultingLine = defaultLine + '\n' + task;
        System.out.println(resultingLine);
        return resultingLine;
    }

    /**
     * Displays goodbye message.
     */
    public String displayBye() {
        String resultingLine = " > see you! hope to see you again :-)";
        System.out.println(resultingLine);
        return resultingLine;
    }

    /**
     * Displays added task.
     *
     * @param taskAdded Added task.
     */
    public String displayAdd(Task taskAdded) {
        String defaultLine = " > added:";
        String task = "  " + taskAdded.toString();
        String resultingLine = defaultLine + '\n' + task;
        System.out.println(resultingLine);
        return resultingLine;
    }

    /**
     * Displays invalid comment.
     */
    public String displayWrongCommand() {
        String resultingLine = "i'm not sure i know what you mean :-( try typing something\n"
                + "using 'todo', 'deadline' or 'event'";
        System.out.println(resultingLine);
        return resultingLine;
    }

    /**
     * Displays taskList.
     *
     * @param taskList task list with all the tasks.
     */
    public String displayList(TaskList taskList) {
        if (taskList.getSize() == 0) {
            String resultingLine = "add anything using 'todo', 'deadline' or 'event'";
            System.out.println(resultingLine);
            return resultingLine;
        } else {
            String defaultLine = " > here you go!";
            String itemList = "";
            System.out.println(defaultLine);
            // loop through the arraylist to show everything
            for (int count = 0; count < taskList.getSize(); count++) {
                Task eachTask = taskList.get(count);
                int countFromOne = count + 1;
                itemList += countFromOne + ". " + eachTask.toString() + '\n';
                System.out.println(countFromOne + ". " + eachTask.toString());
            }
            return itemList;
        }
    }

    /**
     * Displays items searched by user.
     *
     * @param taskList
     * @return
     */
    public String displayFind(TaskList taskList) {
        String defaultLine = " > here are the matching tasks in your list!";
        System.out.println(defaultLine);
        return defaultLine + '\n' + displayList(taskList);
    }

    /**
     * Displays how many tasks are remaining in the list.
     *
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
    public String endOfCommand() {
        String defaultLine = "------------------------------------";
        System.out.println(defaultLine);
        return defaultLine;
    }

    /**
     * Displays straight line.
     */
    public String endOfResponse() {
        String defaultLine = "==================================Oo";
        System.out.println(defaultLine);
        return defaultLine;
    }

    /**
     * Displays error message
     * @param e Exception
     */
    public String showError(Exception e) {
        String defaultLine = e.getMessage();
        System.out.println(defaultLine);
        return defaultLine;
    }
}
