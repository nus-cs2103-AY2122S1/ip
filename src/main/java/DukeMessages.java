package main.java;

import java.util.List;

public class DukeMessages {
    private final String welcomeMessage = "Hello! I'm Duke\n" + "What can I do for you?";
    private final String borderLine = "\n____________________________________________________________\n";
    private final String goodbyeMessage = "Bye. Hope to see you again soon!";


    /**
     * Displays the message input given by the user with horizontal borderlines on both the top and bottom.
     * @param input String message to be displayed to user
     */
    public void displayText(String input) {
        System.out.println(borderLine + input + borderLine);
    }

    /**
     * Displays the welcome message when the Duke program starts
     */
    public void welcomeMessage() {
        this.displayText(welcomeMessage);
    }

    /**
     * Displays the goodbye message when the user inputs "bye"
     */
    public void goodbyeMessage() {
        this.displayText(goodbyeMessage);
    }

    /**
     * Displays the message for a task marked as Done
     * @param taskMarked the task that was marked to be done
     */
    public void markAsDoneMessage(String taskMarked) {
        String message = String.format("Nice! I've marked this task as done:\n  %s", taskMarked);
        this.displayText(message);
    }

    /**
     * Message displayed when a task is deleted from the Task List
     * @param taskString the task deleted from the list
     * @param listSize size of the task list
     */
    public void taskDeleteMessage(String taskString, int listSize) {
        String message = "Noted. I've removed this task:\n" + taskString + "\n";
        message += String.format("Now you have %d tasks in the list.", listSize);
        this.displayText(message);
    }

    /**
     * Displayed when the user inputs "list" and the list is empty
     */
    public void listEmptyMessage() {
        this.displayText("Your List is Empty");
    }

    /**
     * Message displayed when a task is added to the Task List
     * @param taskString the task added to the list
     * @param listSize size of the task list
     */
    public void taskAddMessage(String taskString, int listSize) {
        String message = "Got it. I've added this task:\n" + taskString + "\n";
        message += String.format("Now you have %d tasks in the list.", listSize);
        this.displayText(message);
    }

    /**
     * Displays all the items in the taskList and their completion status.
     * @param taskList the list containing Task objects
     */
    public void displayListItems(List<Task> taskList) {

        //shown when there are no tasks in the list
        if (taskList.isEmpty()) {
            this.listEmptyMessage();
            return;
        }

        System.out.println(borderLine);

        for (int i = 0; i < taskList.size(); i++) {

            //displays the current task's status
            String inputMessage = String.format("%d. %s", i+1, taskList.get(i).toString());
            System.out.println(inputMessage);
        }

        System.out.println(borderLine);
    }


}
