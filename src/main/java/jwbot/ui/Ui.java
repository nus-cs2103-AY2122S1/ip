package jwbot.ui;

import java.util.List;

import jwbot.data.TaskList;
import jwbot.data.task.Task;

/**
 * The class that is in charge of user interaction with the bot
 *
 * @author  Yim Jaewon
 */
public class Ui {

    /**
     * Show bye messages to the user.
     */
    public String showBye() {
        return "You leaving already? See you soon bro!";
    }

    /**
     * Shows the loading error message to the user.
     */
    public String showLoadingError() {
        return "Oops bro, there's an error with loading saved data";
    }

    /**
     * Show the message that the delete attempt was successful to the user.
     *
     * @param task a task that is deleted
     * @param listSize the total size of the list after the deletion
     */
    public String showDeleteSuccessMessage(Task task, int listSize) {
        StringBuilder r = new StringBuilder();
        r.append("OK Bro, I noted you've deleted this task:\n").append(task);
        r.append(System.getProperty("line.separator"));
        r.append("So bro, now you have ").append(listSize).append(" tasks stored in the list!");
        return r.toString();
    }

    /**
     * Show the list of the tasks to the user.
     *
     * @param tasks The list of the tasks stored currently
     */
    public String showList(TaskList tasks) {
        StringBuilder r = new StringBuilder();

        r.append("OK bro, the tasks in your list are: ");
        r.append(System.getProperty("line.separator"));
        for (int i = 1; i < tasks.getSize() + 1; i++) {
            r.append(i).append(". ").append(tasks.getTask(i - 1));
            r.append(System.getProperty("line.separator"));
        }
        r.append("Bro, now you have ").append(tasks.getSize()).append(" task(s)!");
        return r.toString();
    }

    /**
     * Show the message that the task is marked done successfully to the user.
     *
     * @param searchList The list that is the result of user find command
     */
    public String showSearchList(List<Task> searchList) {
        StringBuilder r = new StringBuilder();
        r.append("OK bro, the matching tasks in your list are: ");
        r.append(System.getProperty("line.separator"));
        for (int i = 1; i < searchList.size() + 1; i++) {
            r.append(i).append(". ").append(searchList.get(i - 1));
            r.append(System.getProperty("line.separator"));
        }
        return r.toString();
    }

    public String showDoneSuccessMessage(Task doneTask) {
        return "OK Bro, I noted you've done this task:\n"
                + doneTask;
    }

    /**
     * Show the message that the task was added successfully to the user.
     *
     * @param task The list of the tasks stored currently
     */
    public String showAddTaskSuccessMessage(Task task) {
        return "OK bro, I just added: " + task;
    }

    /**
     * Show the error message to the user.
     *
     * @param error The error message description
     */
    public String showError(String error) {
        return error;
    }

}
