package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

public class Ui {

    /**
     * Returns the welcome message of the duke chat bot.
     *
     * @return welcome message.
     */
    public static String printWelcomeMessage() {
        return "Hello! I'm Duke" + "\n" + "What can I do for you?" + "\n";
    }

    /**
     * Returns the goodbye message of the duke chat bot.
     *
     * @return goodbye message.
     */
    public static String printGoodbyeMessage() {
        return "Bye! Hope to see you soon!" + "\n";
    }

    /**
     * Returns message that contains all the tasks in the user's task list.
     *
     * @param taskList the user's task list.
     * @return string that contains all the tasks in the user's task list.
     */
    public static String printTaskList(TaskList taskList) {
        if (taskList.getNumTasks() == 0) {
            return "You have no tasks in your list!\n";
        } else {
            StringBuilder output = new StringBuilder("Here are the tasks in your list:\n");
            for (int i = 1; i <= taskList.getNumTasks(); i++) {
                Task item = taskList.getTask(i);
                output.append(String.format("    %d. %s\n", (i), item.toString()));
            }
            return output.toString();
        }
    }

    /**
     * Returns message that confirms the task has been marked done.
     *
     * @param task the task that has been completed.
     * @return string that confirms the task has been marked done.
     */
    public static String printTaskDone(Task task) {
        return "Nice! I've marked this task as done:\n"
                + String.format("    %s\n", task);
    }

    /**
     * Returns message that confirms the creation of the task.
     *
     * @param task the task taht has been created.
     * @param numTasks the total number of tasks in the user's task list.
     * @return
     */
    public static String printTaskCreated(Task task, int numTasks) {
        return "Got it. I've added this task:\n    " + task.toString() + "\n"
                + String.format("Now you have %d tasks in the list.\n", numTasks);
    }

    /**
     * Returns message that confirms the task has been deleted.
     *
     * @param task the task that has been deleted.
     * @param numTasks the total number of tasks in the task list.
     * @return string that confirms the deletion of the task.
     */
    public static String printTaskDeleted(Task task, int numTasks) {
        return "Noted. I've removed this task:\n"
                + String.format("    %s\nNow you have %d tasks in the list.\n",
                task.toString(), numTasks);
    }

    /**
     * Returns message that confirms the task has been updated.
     *
     * @param task the task that has been updated.
     * @return string that confirms the update of the task.
     */
    public static String printTaskUpdated(Task task) {
        return "Your task has been updated!\n" + task;
    }

    /**
     * Returns message that contains all the tasks that match the user's query.
     *
     * @param matchingTasks the tasks that match the user's query.
     * @return string that lists all the tasks that matches the user's query.
     */
    public static String printTasksFound(TaskList matchingTasks) {
        if (matchingTasks.getNumTasks() == 0) {
            return "You have no matching tasks in your list!\n";
        } else {
            StringBuilder output = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 1; i <= matchingTasks.getNumTasks(); i++) {
                Task item = matchingTasks.getTask(i);
                output.append(String.format("    %d. %s\n", i, item.toString()));
            }
            return output.toString();
        }
    }

    /**
     * Returns message explaining how to use the duke bot.
     *
     * @return message explaining how to use the duke bot.s
     */
    public static String printHelpMessage() {
        String helpMessage = "Try these commands: \n\n";
        helpMessage += "View help: help \n\n";
        helpMessage += "Listing all tasks: list \n\n";
        helpMessage += "Adding a todo: todo <description> \n\n";
        helpMessage += "Adding a deadline: deadline <description> /by <any input or date in format yyyy-mm-dd> \n\n";
        helpMessage += "Adding an event: event <description> /at <any input or date in format yyyy-mm-dd> \n\n";
        helpMessage += "Deleting a task: delete <task number> \n\n";
        helpMessage += "Updating a task: update <task number> <description> (/at or /by if applicable) "
                + "<any input or date in format yyyy-mm-dd> \n\n";
        helpMessage += "Finding a task: find <query> \n\n";
        helpMessage += "Exiting: exit or bye";
        return helpMessage;
    }

}
