package duke;



import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * The Duke class.
 */
public class Duke {
    private static boolean active;
    private static TaskList taskList = new TaskList();
    private static int listIndex = 0;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/IMG_5401.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/IMG_4596.png"));
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        taskList = new TaskList(Storage.loadData());
        return Parser.getResponse(input);
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * awaken() awakens duke.Duke and allows one to input commands to duke.Duke.
     */
    public static void awaken() {
        taskList = new TaskList(Storage.loadData());
        Duke.active = true;
        Ui.sendStartMessage();
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        while (active) {
            String input = scanner.nextLine();
            boolean cont = Parser.interpretCommand(input);
            if (!cont) {
                break;
            }
        }
    }

    /**
     * deletes a task from the list that duke.Duke creates.
     * @param taskToBeDeleted the index of the task to be deleted.
     * @throws DukeException An exception stemming from incorrect or awkward input to duke.Duke.
     */
    public static String deleteTask(String taskToBeDeleted) throws DukeException {
        int taskNumber = Integer.parseInt(taskToBeDeleted);

        if (taskList.get(taskNumber - 1) == null) {
            throw new DukeException(
                    "____________________________________________________________\n"
                            + "☹ OOPS!!! The task you chose to delete does not exist."
                            + " Use the 'list' command to check the items in your list.\n"
                            + "____________________________________________________________"
            );
        }
        TaskItem removedTask = taskList.get(taskNumber - 1);
        taskList.remove(taskNumber - 1);
        int taskListSize = taskList.size();
        return Ui.deletedTaskMessage(removedTask, taskListSize);
    }


    /**
     * markAsFinished marks items on duke.Duke's list as completed. Completed tasks will have a checkbox "[X]".
     * @param taskItemNumber the index of the task to be marked as finished.
     * @throws DukeException An exception stemming from incorrect or awkward input to duke.Duke.
     */
    public static String markAsFinished(String taskItemNumber) throws DukeException {
        int taskNumber = Integer.parseInt(taskItemNumber);
        assert taskNumber >= 0;
        if (taskList.get(taskNumber - 1) == null) {
            throw new DukeException(
                    "____________________________________________________________\n"
                            + "☹ OOPS!!! The task you chose to delete does not exist."
                            + " Use the 'list' command to check the items in your list.\n"
                            + "____________________________________________________________"
            );
        }
        taskList.get(taskNumber - 1).completeTask();
        return Ui.completedTaskMessage(taskList.get(taskNumber - 1));
    }

    /**
     * addToList adds a taskItem of type duke.TaskItem to the list.
     * @param taskItem a duke.TaskItem that is to be added to duke.Duke's list.
     */
    public static String addToList(TaskItem taskItem) {
        Duke.taskList.add(taskItem);
        Duke.listIndex = taskList.size();
        assert Duke.listIndex >= 0;
        String body = "";
        if (listIndex == 1) {
            body = "Now you have " + 1 + " task in the list.\n";
        }
        if (listIndex > 1) {
            body = "Now you have " + (Duke.listIndex) + " tasks in your list.\n";
        }
        return "____________________________________________________________\n"
                + "Got it. I've added this task:\n"
                + taskItem.toString() + "\n"
                + body
                + "____________________________________________________________";
    }

    /**
     * prints the list upon inputting the 'list' command.
     */
    public static String printList() {
        int number = 1;
        StringBuilder output = new StringBuilder("____________________________________________________________\n"
                + "Here are the tasks in your list:\n");

        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i) != null) {
                output.append(number).append(".").append(taskList.get(i).toString()).append("\n");
                number++;
            }
        }
        return output.toString();
    }

    /**
     * Finds tasks based on the keyword the user inputs and prints them to the user
     * @param keyword keyword used to find matching tasks
     * @return a TaskList with matching tasks to be printed.
     */
    public static TaskList findTask(String keyword) {
        return taskList.findTask(keyword);
    }

    /**
     * main method.
     * @param args input from the user to control Duke.
     */
    public static void main(String[] args) {
        Duke.awaken();
    }

    /**
     * Returns a boolean to indicate whether Duke is still active
     * @return a boolean active.
     */
    public static boolean isActive() {
        return active;
    }

    /**
     * Makes Duke inactive.
     */
    public static void makeInactive() {
        Duke.active = false;
    }

    /**
     * Returns the taskList.
     * @return Duke's taskList.
     */
    public static TaskList getTaskList() {
        return Duke.taskList;
    }
}
