package duke.ui;

import java.util.Scanner;

import duke.exception.LoadingException;
import duke.task.Task;
import duke.task.TaskList;

public class Ui {
    private String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private Scanner sc;

    /**
     * Sets up the ui scanner.
     */
    public Ui() {
        //User input.
        sc = new Scanner(System.in);
    }

    /**
     * Returns the formatted string output.
     *
     * @param task The possible task string representations.
     * @param count The number of tasks.
     * @return The desirable output string related to task.
     */
    public String getOutputFrame(String task, int count) {
        String title = "   Got it. I've added this task:\n   ";
        String middle = "  " + task + "\n   ";
        String end = "Now you have " + count + " tasks in the list.";
        return title + middle + end;
    }

    /**
     * Shows the error message.
     *
     * @param message
     */
    public String showError(String message) {
        return "   " + message;
    }



    /**
     * Shows the deleted task.
     *
     * @param shouldDelete The task should be deleted.
     * @param tasks The list of tasks.
     */
    public String showDelete(Task shouldDelete, TaskList tasks) {
        String title = "   Noted. I've removed this task: \n";
        String out = "     " + shouldDelete.toString() + "\n   ";
        String end = "Now you have " + tasks.getSize() + " tasks in the list.";
        return title + out + end;
    }

    /**
     * Shows adding the task to the list.
     *
     * @param task The task want to be shown.
     * @param count The number of tasks in task list.
     */
    public String showAdding(String task, int count) {
        return getOutputFrame(task, count);
    }

    /**
     * Shows error while loading.
     */
    public String showLoadingError() {
        LoadingException e = new LoadingException();
        return e.getMessage();
    }

    /**
     * Shows the task marked as done.
     *
     * @param stringForm The string form of that task.
     */
    public String showDone(String stringForm) {
        String title = "   Nice! I've marked this task as done: \n";
        String out = "     " + stringForm;
        return title + out;
    }

    public String showBye() {
        return "   " + "Bye, see you soon. ^-^";
    }

    public String showComings(TaskList taskListMonth, TaskList taskListDay) {
        String titleMonth = "   <<Coming tasks within same month>> \n";
        String listInMonth = taskListMonth.toString() + "\n";
        String titleDay = "   <<Coming tasks within the same day>> \n";
        String listInDay = taskListDay.toString() + "\n";
        return titleMonth + listInMonth + titleDay + listInDay;
    }
}
