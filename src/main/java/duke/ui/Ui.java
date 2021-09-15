package duke.ui;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Ui create messages that are being display to user.
 */
public class Ui {

    /**
     * Returns exit message when user exit the programs.
     *
     * @return Exit message when user exit the programs.
     */
    public String createExitMessage() {
        String bye = "Bye. Hope to see you again soon!";
        return bye;
    }

    /**
     * Returns task added to the list.
     *
     * @param task Task that is added.
     * @param taskList The list of tasks.
     * @return Task added to the list.
     */
    public String createAddTaskMessage(Task task, TaskList taskList) {
        assert task != null;
        assert taskList != null;

        String addTemplate = "Got it. I've added this task:\n%s\nNow you have %d task(s) in the list.";
        String addTask = String.format(addTemplate, task.toString(), taskList.getTotalTask());
        return addTask;
    }

    /**
     * Returns task that is deleted from the list.
     *
     * @param task Task that is deleted.
     * @param taskList The list of tasks.
     * @return Task that is deleted from the list.
     */
    public String createDeleteTaskMessage(Task task, TaskList taskList) {
        assert task != null;
        assert taskList != null;

        String deleteTemplate = "Noted. I've removed this task:\n%s\nNow you have %d task(s) in the list.";
        String deleteTask = String.format(deleteTemplate, task.toString(), taskList.getTotalTask());
        return deleteTask;
    }

    /**
     * Returns task that is marked as done.
     *
     * @param task Task that is marked as done.
     * @return Task that is marked as done.
     */
    public String createDoneMessage(Task task) {
        assert task != null;

        String doneTemplate = "Nice! I've marked this task as done:\n%s";
        String done = String.format(doneTemplate, task.toString());
        return done;
    }

    /**
     * Returns list of tasks that are stored.
     *
     * @param taskList The list of tasks that are stored.
     * @return List of tasks that are stored.
     * @throws DukeException If task list has error.
     */
    public String createListMessage(TaskList taskList) throws DukeException {
        assert taskList != null;

        int numTask = taskList.getTotalTask();
        String task = "";

        for (int taskNumber = 1; taskNumber <= numTask; taskNumber++) {
            task = task + "\n" + taskNumber + ". " + taskList.getTask(taskNumber).toString();
        }

        String listStatement = "Here are the tasks in your list:";
        String output = listStatement + task;
        return output;
    }

    /**
     * Tells user that Duke can't understand the input.
     *
     * @return Lost message that tells user that Duke can't understand the input.
     */
    public String createLostMessage() {
        String lost = "OOPS!!! I'm sorry, but I don't know what that means :-(";
        return lost;
    }

    /**
     * Tells user that there is an error loading the file.
     *
     * @return Loading error message.
     */
    public String creatingLoadingErrorMessage() {
        return "OOPS!!! There is a loading error.";
    }

    /**
     * Show user a list of tasks matching the key word.
     *
     * @param tasks List of tasks that match the key word.
     * @return List of tasks matching the key word.
     */
    public String createFindTaskMessage(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            String noMatch = "Sorry, no match found";
            return noMatch;
        }

        int numTask = tasks.size();
        String task = "";

        for (int taskNumber = 1; taskNumber <= numTask; taskNumber++) {
            task = task + "\n" + taskNumber + ". " + tasks.get(taskNumber - 1).toString();
        }

        String taskList = "Here are the matching tasks in your list:" + task;

        return taskList;
    }

    /**
     * Show that the task has been tagged.
     *
     * @param tag The tag that is added.
     * @param task The task that is tagged.
     * @return Task has been tagged.
     */
    public String createTagMessage(String tag, Task task) {
        String tagTemplate = "%s has been tagged to this task:\n%s";
        String tagDone = String.format(tagTemplate, tag, task.toString());
        return tagDone;
    }

}
