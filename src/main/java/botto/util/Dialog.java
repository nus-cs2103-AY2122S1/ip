package botto.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import botto.task.Deadline;
import botto.task.Event;
import botto.task.Task;
import botto.task.Todo;
import botto.ui.DialogBox;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * This class deals with the Botto bot's interactions with the user.
 */
public class Dialog {
    private static final String BOT_NAME = "Botto";
    private static final String GREETING = "Hello! I'm " + BOT_NAME
            + ".\n What can I do for you?";

    private final Image bottoImage = new Image(this.getClass().getResourceAsStream("/images/botto.png"));
    private VBox dialogContainer;

    /**
     * Set up dialog container.
     *
     * @param dialogContainer dialog container.
     */
    public void setUp(VBox dialogContainer) {
        this.dialogContainer = dialogContainer;
    }

    /**
     * Print the bot's welcome message.
     */
    public void showWelcome() {
        addToDialog(GREETING);
    }

    /**
     * Print the name of the bot and supported command.
     */
    public void showHelp() {
        String message = "Name: " + BOT_NAME + "\n"
                + "Supported commands: " + Arrays.toString(Parser.COMMANDS);
        addToDialog(message);
    }

    /**
     * Print the user's tasks.
     *
     * @param list list of tasks to be printed.
     * @param header header message to be printed before printing the tasks.
     */
    public void showTasks(List<Task> list, String header) {
        String taskMessage = processTaskMessage(list, header);
        addToDialog(taskMessage);
    }

    /**
     * Process the message that shows the tasks.
     *
     * @param list list of tasks.
     * @param header header header message to be printed before printing the tasks.
     * @return formatted message that shows the user's tasks.
     */
    private String processTaskMessage(List<Task> list, String header) {
        List<Todo> todos = new LinkedList<>();
        List<Deadline> deadlines = new LinkedList<>();
        List<Event> events = new LinkedList<>();

        categoriseTasks(list, todos, deadlines, events);
        Collections.sort(deadlines);
        Collections.sort(events);

        return convertTasksToString(list, header, todos, deadlines, events);
    }

    private void categoriseTasks(List<Task> list, List<Todo> todos, List<Deadline> deadlines, List<Event> events) {
        for (Task task: list) {
            if (task instanceof Todo) {
                todos.add((Todo) task);
            } else if (task instanceof Deadline) {
                deadlines.add((Deadline) task);
            } else if (task instanceof Event) {
                events.add((Event) task);
            } else {
                assert false;
            }
        }
    }

    private String convertTasksToString(List<Task> list, String header,
                                               List<Todo> todos, List<Deadline> deadlines, List<Event> events) {
        StringBuilder taskMessage = new StringBuilder();
        taskMessage.append(header).append("\n\n");

        taskMessage.append("------------ Todo ------------\n");
        for (Todo todo: todos) {
            taskMessage.append(list.indexOf(todo) + 1).append(". ").append(todo).append("\n");
        }
        taskMessage.append("\n");

        taskMessage.append("---------- Deadline ----------\n");
        for (Deadline deadline: deadlines) {
            taskMessage.append(list.indexOf(deadline) + 1).append(". ").append(deadline).append("\n");
        }
        taskMessage.append("\n");

        taskMessage.append("------------ Event ------------\n");
        for (Event event: events) {
            taskMessage.append(list.indexOf(event) + 1).append(". ").append(event).append("\n");
        }
        return taskMessage.toString();
    }

    /**
     * print a response when a new task is added.
     *
     * @param task new task to be added.
     * @param size total number of the user's tasks after addition.
     */
    public void respondAdd(Task task, int size) {
        String addMessage = processAddMessage(task, size);

        addToDialog(addMessage);
    }

    /**
     * Process the response message for add command.
     *
     * @param task newly added task.
     * @param size total number of the user's tasks after addition.
     * @return response message for add command.
     */
    private String processAddMessage(Task task, int size) {
        return "Got it! I've added this task:\n" + "  "
                + task + "\n" + "Now you have "
                + size + " tasks in the list.";
    }

    /**
     * Print a response when a tasks is deleted.
     *
     * @param task deleted task.
     * @param size total number of the user's tasks after deletion.
     */
    public void respondDelete(Task task, int size) {
        String deleteMessage = processDeleteMessage(task, size);
        addToDialog(deleteMessage);
    }

    /**
     * Process the response message for delete command.
     *
     * @param task deleted task.
     * @param size total number of the user's tasks after deletion.
     * @return response message for delete command.
     */
    private String processDeleteMessage(Task task, int size) {
        return "Noted. I've removed this task:\n"
                + "  " + task + "\n"
                + "Now you have " + size + " tasks in the list.";
    }

    /**
     * Print a response when a task is marked as done.
     *
     * @param task the task which is just marked as done.
     */
    public void respondDone(Task task) {
        String doneMessage = processDoneMessage(task);

        addToDialog(doneMessage);
    }

    /**
     * Process the response message for done command.
     *
     * @param task the task which is just marked as done.
     * @return response message for done command.
     */
    private String processDoneMessage(Task task) {
        return "Nice! I've marked this task as done:\n"
                + "  " + task;
    }

    /**
     * Print a goodbye message.
     */
    public void sayGoodBye() {
        addToDialog("Bye. Hope to see you again soon!");
    }

    /**
     * Print a error message.
     *
     * @param message error message.
     */
    public void showError(String message) {
        addToDialog(message);
    }

    /**
     * Add message to the dialog.
     *
     * @param message message to be added to the dialog.
     */
    private void addToDialog(String message) {
        dialogContainer.getChildren().add(
                DialogBox.getBottoDialog(message, bottoImage)
        );
    }

}
