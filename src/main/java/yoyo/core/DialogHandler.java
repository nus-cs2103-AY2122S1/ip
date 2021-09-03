package yoyo.core;

import yoyo.task.Task;
import yoyo.task.TaskList;

public class DialogHandler {
    public DialogHandler() {
    }

    /**
     * Say goodbye to users.
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
            String res = "Here are the tasks you currently have:\n";
            for (int i = 0; i < currListLength; i++) {
                String toAdd = i + 1 + "." + tasks.get(i).showStatus() + "\n";
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
            res += "Here are the matching tasks in your list:\n";
            for (int i = 0; i < currListLength; i++) {
                String toAdd = i + 1 + "." + tasks.get(i).showStatus() + "\n";
                res += toAdd;
            }
            return res;
        }
    }

    /**
     * Prints a message indicating selected task has been marked as done.
     *
     * @param tasks TaskList of the program.
     * @param taskIndex Index of the Task to be marked as done.
     */
    public String printMarkTaskMessage(TaskList tasks, int taskIndex) {
        String res = "Nice! I've marked this task as done:\n"
                + tasks.get(taskIndex).showStatus() + "\n";
        return res;
    }

    /**
     * Prints a message indicating selected task has been removed.
     *
     * @param toRemove The Task that has been removed.
     * @param tasks    TaskList of the program.
     */
    public String printRemoveTaskMessage(Task toRemove, TaskList tasks) {
        String res = "Noted. I've removed this task:\n"
                + toRemove.showStatus()
                + "\nNow you have "
                + tasks.size()
                + " tasks in the list.\n";
        return res;
    }

    /**
     * Prints success message for adding task.
     *
     * @param newTask The task that has been created.
     * @param tasks   Task list.
     */
    public String printAddMessage(Task newTask, TaskList tasks) {
        String res = "Got it. I've added this task:\n   "
                + newTask.showStatus()
                + "\nNow you have "
                + tasks.size()
                + " tasks in the list.\n";
        return res;
    }

}
