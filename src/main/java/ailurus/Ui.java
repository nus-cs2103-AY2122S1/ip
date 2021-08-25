package ailurus;

import ailurus.task.Task;
import ailurus.task.TaskList;

import java.util.Scanner;

public class Ui {
    private final String CHATBOT = "Ailurus";
    private final String YOU = "You";
    private Scanner scanner = new Scanner(System.in);

    /**
     * Welcomes user to use the chatbot
     */
    public void showWelcome() {
        this.say(String.format("Hello! I'm %s. What can I do for you?", this.CHATBOT));
    }

    /**
     * Read user input
     *
     * @return user input as string
     */
    public String readCommand() {
        System.out.print(this.YOU + ": ");
        return scanner.nextLine();
    }

    /**
     * Show user the error
     *
     * @param errorMessage Error message to be shown to user
     */
    public void showError(String errorMessage) {
        this.say(errorMessage);
    }

    /**
     * Customized display for chatbot messages
     *
     * @param message display message to be printed
     */
    public void say(String message) {
        System.out.println(String.format("%s: %s", this.CHATBOT, message));
    }

    /**
     * Printing out of the list of tasks
     *
     * @param list TaskList to be said by chatbot
     */
    public void sayList(TaskList list) {
        if (list.length() == 0) {
            throw new AilurusException(AilurusException.Error.EMPTYLIST);
        }
        this.say("");
        for(int i = 0; i < list.length(); i++) {
            System.out.println(String.format("%d.%s", i + 1, list.getIndexString(i)));
        }
    }

    /**
     * Tell the user that the task is marked as done
     *
     * @param task task that is marked as done
     */
    public void sayDone(Task task) {
        this.say(String.format("Nice! I've marked this task as done:\n\t%s", task));
    }

    /**
     * Tell the user that the task is added
     *
     * @param task task that is added
     */
    public void sayAdd(Task task, int size) {
        this.say(String.format("Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.",
                task.toString(), size));
    }

    /**
     * Tell the user that the task is deleted
     *
     * @param task task that is deleted
     */
    public void sayDelete(Task task, int size) {
        this.say(String.format("Noted. I've removed this task:\n\t%s\nNow you have %d tasks in the list.",
                task, size));
    }

    /**
     * Tell the user that the command given was invalid
     */
    public void sayInvalidCommand() {
        this.say("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Tell the user that it is the end of chatting
     */
    public void sayBye() {
        this.say("Bye. Hope to see you again soon!");
    }

}
