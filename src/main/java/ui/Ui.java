package ui;

import java.util.List;

import task.Task;


/**
 * The Ui class shows the response from a user's command line input.
 */
public class Ui {
    private static final String WELCOME_MESSAGE = "Hello! I'm Duke\n" + "What can I do for you?";
    private static final String BORDERLINE =
            "\n____________________________________________________________\n";
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";


    /**
     * Displays message input with horizontal borderlines on top and bottom.
     *
     * @param input String message to be displayed to user
     */
    public void displayText(String input) {
        System.out.println(BORDERLINE + input + BORDERLINE);
    }

    public String displayTextGui(String input) {
        return input;
    }

    /**
     * Displays welcome message when Duke program starts.
     */
    public void welcomeMessage() {
        this.displayText(this.WELCOME_MESSAGE);
    }

    /**
     * Displays welcome message when Duke program starts.
     *
     * @return string welcome message.
     */
    public String welcomeMessageGui() {
        return WELCOME_MESSAGE;
    }

    /**
     * Displays goodbye message when user inputs "bye".
     */
    public void goodbyeMessage() {
        this.displayText(GOODBYE_MESSAGE);
    }

    /**
     * Displays goodbye message when user inputs "bye".
     *
     * @return string goodbye message.
     */
    public String goodbyeMessageGui() {
        return GOODBYE_MESSAGE;
    }

    /**
     * Displays the message for a task marked as Done.
     *
     * @param taskMarked the task that was marked to be done.
     */
    public void markDoneMessage(String taskMarked) {
        String message = String.format("Nice! I've marked this task as done:\n  %s", taskMarked);
        this.displayText(message);
    }

    /**
     * Displays the message for a task marked as Done.
     *
     * @param taskMarked the task that was marked to be done.
     * @return response informing user that task has been marked as done.
     */
    public String markDoneMessageGui(String taskMarked) {
        String message = String.format("Nice! I've marked this task as done:\n  %s", taskMarked);
        return message;
    }

    /**
     * Message displayed when a task is deleted.
     *
     * @param taskString the task deleted from the list.
     * @param listSize size of the task list after deletion.
     */
    public void taskDeleteMessage(String taskString, int listSize) {
        String message = "Noted. I've removed this task:\n" + taskString + "\n";
        message += String.format("Now you have %d tasks in the list.", listSize);
        this.displayText(message);
    }

    /**
     * Message displayed when a task is deleted.
     *
     * @param taskString the task deleted from the list.
     * @param listSize size of the task list after deletion.
     * @return response informing user that task has been deleted.
     */
    public String taskDeleteMessageGui(String taskString, int listSize) {
        String message = "Noted. I've removed this task:\n" + taskString + "\n";
        message += String.format("Now you have %d tasks in the list.", listSize);
        return message;
    }

    /**
     * Displays when list is empty.
     */
    public void listEmptyMessage() {
        this.displayText("Your List is Empty");
    }

    /**
     * Displays when list is empty.
     *
     * @return response informing user that the list is empty.
     */
    public String listEmptyMessageGui() {
        return "Your List is Empty";
    }

    /**
     * Replies with a message to inform users that no item match their find.
     */
    public void noMatchingResult() {
        this.displayText("No item in your list fits your find criteria");
    }

    /**
     * Replies with a message to inform users that no item match their find.
     *
     * @return string informing user that their find yield no result.
     */
    public String noMatchingResultGui() {
        return "No item in your list fits your find criteria";
    }

    /**
     * Message displayed when a task is added.
     *
     * @param taskString the task added.
     * @param listSize size of the task list after the addition.
     */
    public void taskAddMessage(String taskString, int listSize) {
        String message = "Got it. I've added this task:\n" + taskString + "\n";
        message += String.format("Now you have %d tasks in the list.", listSize);
        this.displayText(message);
    }

    /**
     * Message displayed when a task is added.
     *
     * @param taskString the task added.
     * @param listSize size of the task list after the addition.
     * @return response informing user that the task has been added to the list.
     */
    public String taskAddMessageGui(String taskString, int listSize) {
        String message = "Got it. I've added this task:\n" + taskString + "\n";
        message += String.format("Now you have %d tasks in the list.", listSize);
        return message;
    }

    /**
     * Displays all items and their completion status.
     *
     * @param taskList the list containing Task objects.
     */
    public void displayListTasks(List<Task> taskList) {
        if (taskList.isEmpty()) {
            this.listEmptyMessage();
            return;
        }

        String message = "";
        for (int i = 0; i < taskList.size(); i++) {
            String inputMessage = String.format("%d. %s", i + 1, taskList.get(i).toString());
            message += inputMessage + "\n";
        }

        this.displayText(message);

    }

    /**
     * Displays all items and their completion status.
     *
     * @param taskList the list containing Task objects.
     * @return response showing all the tasks in the list.
     */
    public String displayListTasksGui(List<Task> taskList) {
        if (taskList.isEmpty()) {
            return this.listEmptyMessageGui();
        }

        String message = "";

        for (int i = 0; i < taskList.size(); i++) {
            String inputMessage = String.format("%d. %s\n", i + 1, taskList.get(i).toString());
            message += inputMessage;
        }

        return message;
    }

    /**
     * Displays the date format to be used.
     */
    public void wrongDateInputMessage() {
        this.displayText("The input should be in the form of yyyy-dd-mm");
    }

    /**
     * Displays the date format to be used.
     * @return response informing user that the wrong date format has been inputted.
     */
    public String wrongDateInputMessageGui() {
        return "The input should be in the form of yyyy-dd-mm";
    }

    /**
     * Displays all the tasks with the keywords.
     *
     * @param taskList the list containing filtered tasks.
     */
    public void displayFilteredTasks(List<Task> taskList) {
        if (taskList.isEmpty()) {
            this.noMatchingResult();
            return;
        }

        String message = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            String inputMessage = String.format("%d. %s", i + 1, taskList.get(i).toString());
            message += inputMessage;
        }

        this.displayText(message);
    }

    /**
     * Displays all the tasks with the keywords.
     *
     * @param taskList the list containing filtered tasks.
     * @return response showing all the tasks in the list that fit the filter criteria.
     */
    public String displayFilteredTasksGui(List<Task> taskList) {
        if (taskList.isEmpty()) {
            return this.noMatchingResultGui();
        }

        String message = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            String inputMessage = String.format("%d. %s\n", i + 1, taskList.get(i).toString());
            message += inputMessage;
        }

        return message;
    }
}
