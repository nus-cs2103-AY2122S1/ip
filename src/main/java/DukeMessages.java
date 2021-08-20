package main.java;

import java.util.List;

public class DukeMessages {
    private final String welcomeMessage = "Hello! I'm Duke\n" + "What can I do for you?";
    private final String borderLine = "\n____________________________________________________________\n";
    private final String goodbyeMessage = "Bye. Hope to see you again soon!";


    /**
     * Displays message input with horizontal borderlines on top and bottom.
     * @param input String message to be displayed to user
     */
    public void displayText(String input) {
        System.out.println(borderLine + input + borderLine);
    }

    /**
     * Displays welcome message when Duke program starts.
     */
    public void welcomeMessage() {
        this.displayText(welcomeMessage);
    }

    /**
     * Displays goodbye message when user inputs "bye".
     */
    public void goodbyeMessage() {
        this.displayText(goodbyeMessage);
    }

    /**
     * Displays the message for a task marked as Done.
     * @param taskMarked the task that was marked to be done.
     */
    public void markAsDoneMessage(String taskMarked) {
        String message = String.format("Nice! I've marked this task as done:\n  %s", taskMarked);
        this.displayText(message);
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

    /**
     * Displays when list is empty.
     */
    public void listEmptyMessage() {
        this.displayText("Your List is Empty");
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

    /**
     * Displays all items and their completion status.
     * @param taskList the list containing Task objects.
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
            String inputMessage = String.format("%d. %s", i + 1, taskList.get(i).toString());
            System.out.println(inputMessage);
        }

        System.out.println(borderLine);
    }


}
