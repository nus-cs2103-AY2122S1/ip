package ailurus;

import ailurus.task.Task;
import ailurus.task.TaskList;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Scanner;

public class Ui {
    private final String CHATBOT = "Ailurus";
    private final String YOU = "You";
    private Scanner scanner = new Scanner(System.in);

    private Image ailurus = new Image(this.getClass().getResourceAsStream("/images/Ailurus.jpg"));
    private Image user = new Image(this.getClass().getResourceAsStream("/images/Pixel.png"));

    /**
     * Welcomes user to use the chatbot
     */
    public String showWelcome() {
        return this.say(String.format("Hello! I'm %s. What can I do for you?", this.CHATBOT));
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
    public String showError(String errorMessage) {
        return this.say(errorMessage);
    }

    /**
     * Customized display for chatbot messages
     *
     * @param message display message to be printed
     */
    public String say(String message) {
        System.out.println(String.format("%s: %s", this.CHATBOT, message));
        return message;
    }


    /**
     * Printing out of the list of tasks
     *
     * @param list TaskList to be said by chatbot
     */
    public String sayList(TaskList list) {
        if (list.length() == 0) {
            throw new AilurusException(AilurusException.Error.EMPTYLIST);
        }
        this.say("");
        String message = "";
        for(int i = 0; i < list.length(); i++) {
            message += String.format("%d.%s\n", i + 1, list.getIndexString(i));

        }
        System.out.println(message);
        return message;
    }

    /**
     * Tell the user that the task is marked as done
     *
     * @param task task that is marked as done
     */
    public String sayDone(Task task) {
        return this.say(String.format("Nice! I've marked this task as done:\n\t%s", task));
    }

    /**
     * Tell the user that the task is added
     *
     * @param task task that is added
     * @param size size of task list
     */
    public String sayAdd(Task task, int size) {
        return this.say(String.format("Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.",
                task.toString(), size));
    }

    /**
     * Tell the user that the task is deleted
     *
     * @param task task that is deleted
     * @param size size of task list
     */
    public String sayDelete(Task task, int size) {
        return this.say(String.format("Noted. I've removed this task:\n\t%s\nNow you have %d tasks in the list.",
                task, size));
    }

    /**
     * Tell the user that there are matching tasks, and the list of them
     *
     * @param tasks list of tasks that are matching
     */
    public String sayFind(TaskList tasks) {
        String header = this.say("Here are the matching tasks in your list:");
        String list = this.sayList(tasks);
        return header + "\n" + list;
    }

    /**
     * Tell the user that the command given was invalid
     */
    public String sayInvalidCommand() {
        return this.say("(!) OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Tell the user that it is the end of chatting
     */
    public String sayBye() {
        return this.say("Bye. Hope to see you again soon!");
    }

}
