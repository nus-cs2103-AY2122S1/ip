package duke.command;

import duke.task.Task;
import duke.TaskList;
import java.util.List;

/**
 * Dealing with responding user's input. Return corresponding response
 */
public class Response {
    private final TaskList taskList;
    private StringBuilder builder;

    /**
     * Constructor of Ui.
     *
     * @param taskList The taskList which stores data of the user's event.
     */
    public Response(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Stores welcome message in a StringBuilder and return it.
     *
     * @return StringBuilder that contains the welcome message.
     */
    public StringBuilder getWelcomeMessage() {
        builder = new StringBuilder();
        builder.append("Hello! I'm Duke.\n");
        builder.append("What can I do for you?\n");
        return builder;
    }

    public StringBuilder getAddNewTaskMessage() {
        builder = new StringBuilder();
        builder.append("Got it. I've added this task:\n");
        builder.append(taskList.get(taskList.size() - 1).toString()).append("\n");
        builder.append("Now you have ").append(taskList.size()).append(" tasks in the list.");
        return builder;
    }

    /**
     * Returns a StringBuilder that contains new message when a task is marked as done.
     *
     * @param itemDone The number of the item that user want to mark as done.
     * @return return a StringBuilder contains the message that a task is marked as done.
     */
    public StringBuilder getMarkTaskDoneMessage(int itemDone) {
        builder = new StringBuilder();
        builder.append("Nice! I've marked this task as done:").append('\n');
        builder.append(taskList.get(itemDone - 1).toString());
        return builder;
    }

    /**
     * Returns a StringBuilder that contains Goodbye message.
     *
     * @return return a StringBuilder contains the message that the programme has end.
     */
    public StringBuilder getGoodByeMessage() {
        builder = new StringBuilder();
        builder.append("Bye. Hope to see you again soon!");
        return builder;
    }

    /**
     * Stores tasks information in a StringBuilder and return it.
     *
     * @return Return a StringBuilder that contains information of the current tasks stored in the list.
     */
    public StringBuilder getListMessage() {
        builder = new StringBuilder();
        builder.append("Here are the tasks in your list:").append('\n');
        for (int i = 1; i <= taskList.size(); i++) {
            builder.append(i).append(". ").append(taskList.get(i - 1).toString()).append('\n');
        }
        return builder;
    }

    /**
     * Stores new message when user want to delete a task in a StringBuilder and return it.
     *
     * @param deletedTask The task that user want to delete
     * @return Return a StringBuilder that contains the delete task message.
     */
    public StringBuilder getDeleteMessage(Task deletedTask) {
        builder = new StringBuilder();
        builder.append("Noted. I've removed this task:").append('\n');
        builder.append(deletedTask.toString()).append('\n');
        builder.append("Now you have ").append(taskList.size()).append(" tasks in the list.");
        return builder;
    }

    /**
     * Stores new message of found lists in a StringBuilder and return it.
     *
     * @param nums A List containing numbers of the found tasks.
     * @return Return a StringBuilder that contains the found lists.
     */
    public StringBuilder getFoundTasks(List<Integer> nums) {
        builder = new StringBuilder();
        if (nums.size() > 0) {
            builder.append("Here are the matching tasks in your list:\n");
        } else {
            builder.append("Sorry, I cannot find any matching task from the list :(\n");
        }
        for (int i = 1; i <= nums.size(); i++) {
            int num = nums.get(i - 1);
            builder.append(i).append(". ").append(taskList.get(num).toString()).append('\n');
        }
        return builder;
    }
}
