package duke;


import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;
import javafx.application.Platform;

/**
 * Class responsible for all UI interactions with user.
 *
 * @author Aiken Wong
 */
public class Ui {

    private Scanner scanner;
    private boolean isGui = false;
    private GraphicalUserInterface gui = null;

    /**
     * Constructor for Ui class
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Allows a GraphicalUserInterface to be added to the Ui so that appropriate GUI responses can be generated.
     *
     * @param gui The GUI to be added.
     */
    public void addGui(GraphicalUserInterface gui) {
        scanner.close();
        this.gui = gui;
        this.isGui = true;
    }

    /**
     * Displays the message of any DukeException thrown by Duke.
     *
     * @param e DukeException to be printed.
     */
    protected void showException(DukeException e) {
        String message = e.getMessage() + "\n";
        if (isGui) {
            gui.displayDukeReply(message);
            return;
        }
        displayMessage(message);
    }

    /**
     * Gives greetings to users as they log into Duke.
     */
    protected void greet() {
        String message = "Good Day Sir/Mdm, I am Duke.\nWhat can I do for you?\n";
        if (isGui) {
            gui.displayDukeReply(message);
            return;
        }
        displayMessage(message);
    }

    /**
     * Bids users goodbye as they exit the app.
     */
    public void end() {
        String message = "Goodbye Sir/Mdm. Hope to serve you again soon!\n";
        if (isGui) {
            gui.displayDukeReply(message);
            Platform.exit();
            return;
        }
        scanner.close();
        displayMessage(message);
    }

    /**
     * Gives confirmation upon user's command to add task.
     *
     * @param task  The given task to be added.
     * @param tasks The task list that the task is added to.
     */
    public void add(Task task, TaskList tasks) {
        String message = "Understood Sir/Mdm, I have added the indicated task: " + "\n   " + task + "\n"
            + "Now you have " + tasks.size() + (tasks.size() == 1 ? " task." : " tasks.") + "\n";
        if (isGui) {
            gui.displayDukeReply(message);
            return;
        }
        displayMessage(message);
    }

    /**
     * Shows the list of current tasks to the user.
     *
     * @param tasks The current tasks.
     */
    public void showList(TaskList tasks) {
        String message = "Here are your tasks Sir/Mdm:\n" + list(tasks) + "\n";
        if (isGui) {
            gui.displayDukeReply(message);
            return;
        }
        displayMessage(message);
    }

    /**
     * Gives confirmation to the user that a task is marked as done.
     *
     * @param task The task to be marked as done.
     */
    public void markDone(Task task) {
        String message = "Good job Sir/Mdm! I shall mark this task as complete:\n   "
            + task + "\n";
        if (isGui) {
            gui.displayDukeReply(message);
            return;
        }
        displayMessage(message);
    }

    /**
     * Gives confirmation that a task has been deleted.
     *
     * @param task  The task to be deleted.
     * @param tasks The user's current tasks.
     */
    public void delete(Task task, TaskList tasks) {
        String message = "Much obliged Sir/Mdm! I shall delete this task:\n   "
            + task + "\n" + "Now you have " + tasks.size()
            + (tasks.size() == 1 ? " task." : " tasks.") + "\n";

        if (isGui) {
            gui.displayDukeReply(message);
            return;
        }
        displayMessage(message);
    }

    /**
     * Produces a string enumerating the tasks of the given task list.
     *
     * @param tasks The given tasks list to enumerate.
     * @return
     */
    public String list(TaskList tasks) {
        String list = "";
        for (int i = 0; i < tasks.size(); i++) {
            list = list + (i == 0 ? "" : "\n") + (i + 1) + ". " + tasks.get(i);
        }
        return list;
    }

    /**
     * Informs user of all tasks found by date.
     *
     * @param foundTasks The tasks that have been found by date.
     */
    public void findByDate(TaskList foundTasks) {
        String message = "Here are the deadlines and events that match the date Sir/Mdm:\n"
            + list(foundTasks) + "\n";
        if (isGui) {
            gui.displayDukeReply(message);
            return;
        }
        displayMessage(message);
    }

    /**
     * Informs user of all tasks found by matching words.
     *
     * @param foundTasks The tasks that have been found by matching words.
     */
    public void findByDescription(TaskList foundTasks) {
        String message = "Here are the results of the search Sir/Mdm:\n"
            + list(foundTasks) + "\n";
        if (isGui) {
            gui.displayDukeReply(message);
            return;
        }
        displayMessage(message);
    }

    /**
     * Reads the user's input from the Command Line Interface.
     *
     * @return The user's input.
     * @throws DukeException
     */
    protected String readCommand() throws DukeException {
        if (isGui) {
            throw new DukeException("Something wrong happened: Cannot read command from GUI");
        }
        return scanner.nextLine();
    }


    private static void displayMessage(String message) {
        System.out.println(message);
    }

}
