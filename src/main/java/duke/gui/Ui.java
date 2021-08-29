package duke.gui;

import duke.tasks.Task;
import duke.tasks.TaskList;

public class Ui {

    private enum Message {
        WELCOME_MSG("Hello! I am Stoner!\nWhat can I do for you?"),
        EXIT_MSG("Good Bye. Don't have a good day... Have a great day!!!"),
        DISPLAY_LIST_MSG("There are the tasks you have:"),
        TASK_DONE_MSG("Well done! You have completed: \n"),
        TASK_ADDED_MSG("Got it. I've added this task: \n"),
        TASK_DELETED_MSG("Got it. I've deleted this task: \n"),
        CHECK_TASK_COUNT_MSG(" tasks are in your list now!"),
        NONE_MATCH_MSG("Sorry, I can't find anything that matches the keyword.");


        private final String msg;

        Message(String msg) {
            this.msg = msg;
        }
    }


    public static String greetUser() {
        return Message.WELCOME_MSG.msg;
    }

    public static String exit() {
        return Message.EXIT_MSG.msg;
    }

    public static String printTaskCompleteMessage(Task task) {
        return Message.TASK_DONE_MSG.msg + task;
    }

    /**
     * Displays the contents in the list when the user asked for it.
     *
     * @param taskList The list containing the tasks
     * @return A String representing the contents in the list.
     */
    public static String displayList(TaskList taskList) {
        StringBuilder content = new StringBuilder(Message.DISPLAY_LIST_MSG.msg);

        for (int i = 0; i < taskList.getTaskCount(); i++) {
            Task item = taskList.getTask(i);
            String itemDetails = String.format("\n%s.%s", (i + 1), item);

            content.append(itemDetails);
        }

        return content.toString();
    }

    public static String printNoneMatchMessage() {
        return Message.NONE_MATCH_MSG.msg;
    }

    /**
     * Prints the message to be shown when the user deletes a task from the list.
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
     * Prints the message to be shown when the user adds a task to the list.
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

    public static String notifyError(String errorMsg) {
        return errorMsg;
    }




}
