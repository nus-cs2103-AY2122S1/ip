package duke.gui;

import duke.tasks.Task;
import duke.tasks.TaskList;

public class Ui {

    private enum Message {
        WELCOME_MSG("Hello! I am Iron Bot!\nWhat can I do for you?"),
        EXIT_MSG("Good Bye. Don't have a good day... Have a great day!!!"),
        DISPLAY_LIST_MSG("These are the task(s) you have:"),
        EMPTY_LIST_MSG("There is current no task in your list!"),
        TASK_DONE_MSG("Well done! You have completed: \n"),
        TASK_ADDED_MSG("Roger. I've added this task: \n"),
        TASK_DELETED_MSG("Roger. I've deleted this task: \n"),
        CHECK_TASK_COUNT_MSG(" task(s) are in your list now!"),
        NONE_MATCH_MSG("Sorry, I can't find anything that matches the keyword."),
        UPDATE_TASK_MSG("Roger! I have updated this task: \n");

        private final String msg;

        Message(String msg) {
            this.msg = msg;
        }
    }

    /**
     * Returns the welcome message when the chat bot is started.
     *
     * @return A String representing the welcome message.
     */
    public static String greetUser() {
        return Message.WELCOME_MSG.msg;
    }

    /**
     * Returns the goodbye message when the chat bot is terminated.
     *
     * @return A String representing the goodbye message.
     */
    public static String exit() {
        return Message.EXIT_MSG.msg;
    }

    /**
     * Returns the message when the user has completed a task.
     *
     * @param task The task completed
     * @return A String representing the task completed message to be displayed.
     */
    public static String printTaskCompleteMessage(Task task) {
        return Message.TASK_DONE_MSG.msg + task;
    }

    /**
     * Returns a String containing the contents in the list when the user asked for it.
     *
     * @param taskList The list containing the tasks
     * @return A String representing the contents in the list.
     */
    public static String displayList(TaskList taskList) {
        if (taskList.isListEmpty()) {
            return Message.EMPTY_LIST_MSG.msg;
        }

        return listToString(taskList);
    }

    private static String listToString(TaskList taskList) {
        StringBuilder content = new StringBuilder(Message.DISPLAY_LIST_MSG.msg);

        for (int i = 0; i < taskList.getTaskCount(); i++) {
            Task item = taskList.getTask(i);
            String itemDetails = String.format("\n%s.%s", (i + 1), item);

            content.append(itemDetails);
        }

        return content.toString();
    }

    /**
     * Returns a String indicating that there is nothing in the list that matches the input.
     *
     * @return A String representing the none matched message to be displayed.
     */
    public static String printNoneMatchMessage() {
        return Message.NONE_MATCH_MSG.msg;
    }

    /**
     * Returns the message to be shown when the user deletes a task from the list.
     *
     * @param task The task to be deleted
     * @param count Number of task in the list.
     * @return A String representing the message from the chat Bot.
     */
    public static String printDeleteTaskMessage(Task task, int count) {
        String deletedTaskInfo = Message.TASK_DELETED_MSG.msg + task;
        String updatedListInfo = String.format("%d %s", count, Message.CHECK_TASK_COUNT_MSG.msg);

        return deletedTaskInfo + "\n" + updatedListInfo;
    }

    /**
     * Returns the message to be shown when the user adds a task to the list.
     *
     * @param task The task to be deleted
     * @param count Number of task in the list.
     * @return A String representing the message from the chat Bot.
     */
    public static String printAddTaskMessage(Task task, int count) {
        String addedTaskInfo = Message.TASK_ADDED_MSG.msg + task;
        String updatedListInfo = String.format("%d %s", count, Message.CHECK_TASK_COUNT_MSG.msg);

        return addedTaskInfo + "\n" + updatedListInfo;
    }

    /**
     * Returns the error message when an error has occurred.
     *
     * @param errorMsg The message to be shown when error occurred.
     * @return A String representing the error message.
     */
    public static String notifyError(String errorMsg) {
        return errorMsg;
    }

    /**
     * Returns the message when a task is updated successfully.
     *
     * @param taskList The list containing the task
     * @param id The id of the task that was updated
     * @return A String representing hte message to be shown.
     */
    public static String printUpdateMessage(TaskList taskList, int id) {
        String message = Message.UPDATE_TASK_MSG.msg + taskList.getTask(id);
        return message;
    }


}
