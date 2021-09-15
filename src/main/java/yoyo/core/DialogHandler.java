package yoyo.core;

import static yoyo.utility.Constant.NEWLINE_CHAR;

import yoyo.task.Task;
import yoyo.task.TaskList;

/**
 * Dialog handler class that represents the component of Yoyo program that handles dialog.
 */
public class DialogHandler {
    /**
     * Says goodbye to users.
     */
    public String sayGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints all tasks in the current TaskList.
     *
     * @param tasks TaskList of the program.
     */
    public String printTaskList(TaskList tasks) {
        int currListLength = tasks.size();
        if (currListLength == 0) {
            return "You have no task at the moment.";
        } else {
            assert currListLength != 0;

            String res = "Here are the tasks you currently have:" + NEWLINE_CHAR;
            for (int i = 0; i < currListLength; i++) {
                String toAdd = i + 1 + "." + tasks.get(i).showStatus() + NEWLINE_CHAR;
                res += toAdd;
            }
            return res;
        }
    }

    /**
     * Prints all tasks that matches find query.
     *
     * @param tasks TaskList of the program.
     */
    public String printMatchingTaskList(TaskList tasks) {
        int currListLength = tasks.size();
        if (currListLength == 0) {
            return "Sorry :-(. Yoyo has not found any matching tasks.";
        } else {
            String res = "";
            res += "Here are the matching tasks in your list:" + NEWLINE_CHAR;
            for (int i = 0; i < currListLength; i++) {
                String toAdd = i + 1 + "." + tasks.get(i).showStatus() + NEWLINE_CHAR;
                res += toAdd;
            }
            return res;
        }
    }

    /**
     * Prints a message indicating selected task has been marked as done.
     *
     * @param task Task marked done.
     */
    public String printMarkTaskMessage(Task task) {
        String res = "Nice! I've marked this task as done:"
                + NEWLINE_CHAR
                + task.showStatus() + NEWLINE_CHAR;
        return res;
    }

    /**
     * Prints a message indicating new tags have been added to selected task.
     *
     * @param task Task tagged.
     */
    public String showAddTagMessage(Task task) {
        String res = "Nice! I've added the new tags to this task:"
                + NEWLINE_CHAR
                + task.showStatus() + NEWLINE_CHAR;
        return res;
    }

    /**
     * Prints a message indicating selected task has been removed.
     *
     * @param toRemove The Task that has been removed.
     * @param tasks    TaskList of the program.
     */
    public String printRemoveTaskMessage(Task toRemove, TaskList tasks) {
        String res = "Noted. I've removed this task:"
                + NEWLINE_CHAR
                + toRemove.showStatus()
                + NEWLINE_CHAR
                + "Now you have "
                + tasks.size()
                + " tasks in the list."
                + NEWLINE_CHAR;
        return res;
    }

    /**
     * Prints success message for adding task.
     *
     * @param newTask The task that has been created.
     * @param tasks   Task list.
     */
    public String printAddMessage(Task newTask, TaskList tasks) {
        String res = "Got it. I've added this task:"
                + NEWLINE_CHAR
                + newTask.showStatus()
                + NEWLINE_CHAR
                + "Now you have "
                + tasks.size()
                + " tasks in the list."
                + NEWLINE_CHAR;
        return res;
    }
}
