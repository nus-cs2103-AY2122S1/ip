package botto.util;

import java.util.List;

import botto.task.Task;
import botto.ui.DialogBox;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * This class deals with the Botto bot's interactions with the user
 */
public class Dialog {
    private static final String BOT_NAME = "Botto";

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/human.png"));
    private Image bottoImage = new Image(this.getClass().getResourceAsStream("/images/botto.png"));
    private VBox dialogContainer;

    /**
     * set up dialog container
     * @param dialogContainer dialog container
     */
    public void setUp(VBox dialogContainer) {
        this.dialogContainer = dialogContainer;
    }

    /**
     * print the bot's welcome message
     */
    public void showWelcome() {
        String greet = "Hello! I'm " + BOT_NAME + ".\n"
                + "What can I do for you?";

        dialogContainer.getChildren().add(
                DialogBox.getBottoDialog(greet, bottoImage)
        );
    }


    /**
     * print the user's tasks
     *
     * @param list list of tasks to be printed
     * @param header header message to be printed before printing the tasks
     */
    public void showTasks(List<Task> list, String header) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(header).append("\n");

        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            stringBuilder.append(i + 1).append(". ").append(task).append("\n");
        }
        dialogContainer.getChildren().add(
                DialogBox.getBottoDialog(stringBuilder.toString(), bottoImage)
        );
    }

    /**
     * print a response when a new task is added
     *
     * @param task new task to be added
     * @param size total number of the user's tasks after addition
     */
    public void respondAdd(Task task, int size) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Got it! I've added this task:\n").append("  ")
                .append(task).append("\n").append("Now you have ")
                .append(size).append(" tasks in the list.");

        dialogContainer.getChildren().add(
                DialogBox.getBottoDialog(stringBuilder.toString(), bottoImage)
        );
    }

    /**
     * print a response when a tasks is deleted
     *
     * @param task deleted task
     * @param size total number of the user's tasks after deletion
     */
    public void respondDelete(Task task, int size) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Noted. I've removed this task:\n").append("  ")
                .append(task).append("\n").append("Now you have ")
                .append(size).append(" tasks in the list.");

        dialogContainer.getChildren().add(
                DialogBox.getBottoDialog(stringBuilder.toString(), bottoImage)
        );
    }

    /**
     * print a response when a task is marked as done
     *
     * @param task the task which is just marked as done
     */
    public void respondDone(Task task) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Nice! I've marked this task as done:\n").append("  ").append(task);

        dialogContainer.getChildren().add(
                DialogBox.getBottoDialog(stringBuilder.toString(), bottoImage)
        );
    }

    /**
     * print a goodbye message
     */
    public void sayGoodBye() {
        dialogContainer.getChildren().add(
                DialogBox.getBottoDialog("Bye. Hope to see you again soon!", bottoImage)
        );
    }

    /**
     * print a error message
     *
     * @param message error message
     */
    public void showError(String message) {
        dialogContainer.getChildren().add(
                DialogBox.getBottoDialog(message, bottoImage)
        );
    }

}
