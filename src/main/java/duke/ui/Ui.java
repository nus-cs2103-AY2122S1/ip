package duke.ui;

import java.io.IOException;
import java.util.List;

import duke.Duke;
import duke.task.Task;
import duke.task.TaskList;
import javafx.stage.Stage;

/**
 * This class encapsulates the UI of the Duke app.
 */
public class Ui {
    private static final String MAIN_WINDOW_FXML_PATH = "/view/MainWindow.fxml";
    private final MainWindow mainWindowController;

    /**
     * Initializes the GUI.
     *
     * @param stage The JavaFX stage.
     * @param duke The duke app.
     * @throws IOException Thrown if there was an error loading the GUI.
     */
    public Ui(Stage stage, Duke duke) throws IOException {
        javafx.fxml.FXMLLoader fxmlLoader =
                new javafx.fxml.FXMLLoader(duke.Main.class.getResource(MAIN_WINDOW_FXML_PATH));
        javafx.scene.layout.AnchorPane ap = fxmlLoader.load();
        javafx.scene.Scene scene = new javafx.scene.Scene(ap);
        stage.setScene(scene);
        this.mainWindowController = fxmlLoader.getController();
        this.mainWindowController.setDuke(duke);
        stage.show();
    }

    /**
     * Prints formatted error messages.
     *
     * @param string The error message.
     */
    public void printErr(String string) {
        this.mainWindowController.printDukeMessage(string);
    }

    /**
     * Prints formatted messages
     *
     * @param string The message.
     */
    public void print(String string) {
        this.mainWindowController.printDukeMessage(string);
    }

    /**
     * Prints the exit message.
     */
    public void printExitMessage() {
        this.mainWindowController.printDukeMessage("Bye. Hope to see you again soon!");
    }


    /**
     * Prints the task that was added.
     *
     * @param task The task that was added.
     */
    public void printTaskAdded(Task task) {
        String s = "Got it. I've added this task:\n"
                + "\t" + task + "\n";
        this.print(s);
    }

    /**
     * Prints the current number of task in the task list.
     *
     * @param taskList The task list.
     */
    public void printNumOfTasks(TaskList taskList) {
        List<Task> tasks = taskList.getTaskList();
        String numOfTasks = tasks.size() + " task" + (tasks.size() != 1 ? "s" : "");
        this.print("You now have " + numOfTasks + " in the list.");
    }

    /**
     * Greets the user.
     */
    public void printGreeting() {
        this.print("Hi, im Duke!\nWhat can i do for you?\n");
    }

    /**
     * Prints the task that was marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void printTaskMarkedAsDone(Task task) {
        this.print("Nice! I've marked this task as done:\n\t" + task);
    }

    /**
     * Prints the task that was deleted.
     *
     * @param task The task that was deleted.
     */
    public void printTaskDeleted(Task task) {
        this.print("Noted. I've removed this task:\n\t" + task);
    }

    /**
     * Prints the list of tasks.
     *
     * @param tasks The list of tasks.
     */
    public void printMatches(List<Task> tasks) {
        if (tasks.isEmpty()) {
            this.print("No matches!");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        printTasksInOrder(tasks, sb);
    }

    /**
     * Prints all the task from the given task list.
     *
     * @param taskList The task list to be printed from.
     */
    public void printAllTasks(TaskList taskList) {
        List<Task> tasks = taskList.getTaskList();
        if (tasks.size() == 0) {
            this.print("You have no tasks in your list.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        printTasksInOrder(tasks, sb);
    }

    private void printTasksInOrder(List<Task> tasks, StringBuilder sb) {
        for (int i = 0; i < tasks.size(); i++) {
            sb.append("\t");
            sb.append(i + 1);
            sb.append(". ");
            sb.append(tasks.get(i));
            sb.append("\n");
        }
        this.print(sb.toString());
    }

    /**
     * Prints the old task and the updated task.
     *
     * @param oldTask The old task.
     * @param updatedTask The updated task.
     */
    public void printTaskUpdated(Task oldTask, Task updatedTask) {
        String toPrint = "Your task has been updated from:\n"
                + "\t" + oldTask
                + "\nto:\n"
                + "\t" + updatedTask;
        this.print(toPrint);
    }
}
