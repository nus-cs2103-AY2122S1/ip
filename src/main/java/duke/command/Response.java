package duke.command;

import duke.task.Task;

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
        builder.append("If you don't know how can I help you, please enter 'help' in the command :)");
        return builder;
    }

    /**
     * Stores command introduction message in a StringBuilder and return it.
     *
     * @return StringBuilder that contains the command introduction message.
     */
    public StringBuilder getHelpMessage() {
        builder = new StringBuilder();
        builder.append("In order to let me help you to store your tasks, ");
        builder.append("please enter your command in this format:\n");
        builder.append("Add Todo task: todo xxx\n");
        builder.append("Add Event task: event xxx /at xxxx-xx-xx xx:xx to xx:xx\n");
        builder.append("Add Deadline task: deadline xxx /by xxxx-xx-xx xx:xx\n");
        builder.append("Show List: list\n");
        builder.append("Mark item as done: done x\n");
        builder.append("delete item: delete x\n");
        builder.append("find item: find xxxx\n");
        builder.append("Show the command list: help\n");
        builder.append("exit the program: bye");
        return builder;
    }

    /**
     * Returns a StringBuilder that contains message to tell user that task was successfully added.
     *
     * @return StringBuilder that contains message to tell user that task was successfully added.
     */
    public StringBuilder getAddNewTaskMessage() {
        builder = new StringBuilder();
        builder.append("Got it. I've added this task:\n");
        builder.append(taskList.getTask(taskList.size() - 1).toString()).append("\n");
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
        builder.append(taskList.getTask(itemDone - 1).toString());
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
        if (taskList.size() == 0) {
            return new StringBuilder("There is no task in your List yet!");
        }
        builder = new StringBuilder();
        builder.append("Here are the tasks in your list:").append('\n');
        for (int i = 1; i <= taskList.size(); i++) {
            if (i > 1) {
                builder.append("\n");
            }
            builder.append(i).append(". ").append(taskList.getTask(i - 1).toString());
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
            builder.append(i).append(". ").append(taskList.getTask(num).toString()).append('\n');
        }
        return builder;
    }
}
