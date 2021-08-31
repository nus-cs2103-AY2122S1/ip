package ui;

import task.Task;

import java.util.List;

/**
 * The Ui class provides dependency injection for the classes to show the response from a user's command line input.
 * @author  Hoon Darren
 * @version 1.0
 * @since   2021-08-21
 */
public class Ui {
    private static final String WELCOME_MESSAGE = "Hello! I'm Duke\n" + "What can I do for you?";
    private static final String BORDERLINE = "\n____________________________________________________________\n";
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";


    /**
     * Displays message input with horizontal borderlines on top and bottom.
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


    public String welcomeMessageGui() {
        return WELCOME_MESSAGE;
    }

    /**
     * Displays goodbye message when user inputs "bye".
     */
    public void goodbyeMessage() {
        this.displayText(GOODBYE_MESSAGE);
    }


    public String goodbyeMessageGui() {
        return GOODBYE_MESSAGE;
    }

    /**
     * Displays the message for a task marked as Done.
     * @param taskMarked the task that was marked to be done.
     */
    public void markDoneMessage(String taskMarked) {
        String message = String.format("Nice! I've marked this task as done:\n  %s", taskMarked);
        this.displayText(message);
    }


    public String markDoneMessageGui(String taskMarked) {
        String message = String.format("Nice! I've marked this task as done:\n  %s", taskMarked);
        return message;
    }

    /**
     * Message displayed when a task is deleted.
     * @param taskString the task deleted from the list.
     * @param listSize size of the task list after deletion.
     */
    public void taskDeleteMessage(String taskString, int listSize) {
        String message = "Noted. I've removed this task:\n" + taskString + "\n";
        message += String.format("Now you have %d tasks in the list.", listSize);
        this.displayText(message);
    }

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

    public String listEmptyMessageGui() {
        return "Your List is Empty";
    }

    /**
     * Message displayed when a task is added.
     * @param taskString the task added.
     * @param listSize size of the task list after the addition.
     */
    public void taskAddMessage(String taskString, int listSize) {
        String message = "Got it. I've added this task:\n" + taskString + "\n";
        message += String.format("Now you have %d tasks in the list.", listSize);
        this.displayText(message);
    }

    public String taskAddMessageGui(String taskString, int listSize) {
        String message = "Got it. I've added this task:\n" + taskString + "\n";
        message += String.format("Now you have %d tasks in the list.", listSize);
        return message;
    }

    /**
     * Displays all items and their completion status.
     * @param taskList the list containing Task objects.
     */
    public void displayListTasks(List<Task> taskList) {

        //shown when there are no tasks in the list
        if (taskList.isEmpty()) {
            this.listEmptyMessage();
            return;
        }

        System.out.println(BORDERLINE);

        for (int i = 0; i < taskList.size(); i++) {

            //displays the current task's status
            String inputMessage = String.format("%d. %s", i + 1, taskList.get(i).toString());
            System.out.println(inputMessage);
        }

        System.out.println(BORDERLINE);
    }

    public String displayListTasksGui(List<Task> taskList) {

        //shown when there are no tasks in the list
        if (taskList.isEmpty()) {
            return this.listEmptyMessageGui();
        }

        String message = "";

        for (int i = 0; i < taskList.size(); i++) {

            //displays the current task's status
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

    public String wrongDateInputMessageGui() {
        return"The input should be in the form of yyyy-dd-mm";
    }

    /**
     * Displays all the tasks with the keywords.
     * @param taskList the list containing filtered tasks.
     */
    public void displayFilteredTasks(List<Task> taskList) {

        //shown when there are no tasks in the list
        if (taskList.isEmpty()) {
            this.listEmptyMessage();
            return;
        }

        System.out.println(BORDERLINE);
        System.out.println("Here are the matching tasks in your list:");

        for (int i = 0; i < taskList.size(); i++) {

            //displays the current task's status
            String inputMessage = String.format("%d. %s", i + 1, taskList.get(i).toString());
            System.out.println(inputMessage);
        }

        System.out.println(BORDERLINE);
    }

    public String displayFilteredTasksGui(List<Task> taskList) {

        //shown when there are no tasks in the list
        if (taskList.isEmpty()) {
            return this.listEmptyMessageGui();
        }

        String message = "Here are the matching tasks in your list:\n";

        for (int i = 0; i < taskList.size(); i++) {

            //displays the current task's status
            String inputMessage = String.format("%d. %s\n", i + 1, taskList.get(i).toString());
            message += inputMessage;
        }
        return message;
    }


}
