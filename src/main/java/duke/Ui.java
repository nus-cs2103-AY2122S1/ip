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

    private Scanner scanner = null;
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
        if (isGui) {
            gui.displayDukeReply(e.getMessage() + "\n");
            return;
        }
        System.out.println(e.getMessage());
        System.out.println();
    }

    /**
     * Gives greetings to users as they log into Duke.
     */
    protected void greet() {
        if (isGui) {
            gui.displayDukeReply("Good Day Sir/Mdm, I am Duke.\nWhat can I do for you?\n");
            return;
        }
        System.out.println("Good Day Sir/Mdm, I am Duke.\nWhat can I do for you?\n");
    }

    /**
     * Bids users goodbye as they exit the app.
     */
    public void end() {
        if (isGui) {
            gui.displayDukeReply("Goodbye Sir/Mdm. Hope to serve you again soon!\n");
            Platform.exit();
            return;
        }
        scanner.close();
        System.out.println("Goodbye Sir/Mdm. Hope to serve you again soon!\n");
    }

    /**
     * Gives confirmation upon user's command to add task.
     *
     * @param task The given task to be added.
     * @param tasks The task list that the task is added to.
     */
    public void add(Task task, TaskList tasks) {
        if (isGui) {
            gui.displayDukeReply("Understood Sir/Mdm, I have added the indicated task: " + "\n   " + task + "\n"
                + "Now you have " + tasks.size() + (tasks.size() == 1 ? " task." : " tasks.") + "\n");
            return;
        }


        System.out.println("Understood Sir/Mdm, I have added the indicated task: " + "\n   " + task);
        System.out.println("Now you have " + tasks.size() + (tasks.size() == 1 ? " task." : " tasks.") + "\n");
    }

    /**
     * Shows the list of current tasks to the user.
     *
     * @param tasks The current tasks.
     */
    public void showList(TaskList tasks) {
        if (isGui) {
            gui.displayDukeReply("Here are your tasks Sir/Mdm:\n" + list(tasks) + "\n");
            return;
        }


        System.out.println("Here are your tasks Sir/Mdm:");
        System.out.println(list(tasks));
        System.out.println();
    }

    /**
     * Gives confirmation to the user that a task is marked as done.
     *
     * @param task The task to be marked as done.
     */
    public void markDone(Task task) {
        if (isGui) {
            gui.displayDukeReply("Good job Sir/Mdm! I shall mark this task as complete:\n   "
                + task + "\n");
            return;
        }

        System.out.println("Good job Sir/Mdm! I shall mark this task as complete:\n   "
            + task + "\n");
    }

    /**
     * Gives confirmation that a task has been deleted.
     *
     * @param task The task to be deleted.
     * @param tasks The user's current tasks.
     */
    public void delete(Task task, TaskList tasks) {
        if (isGui) {
            gui.displayDukeReply("Much obliged Sir/Mdm! I shall delete this task:\n   "
                + task + "\n" + "Now you have " + tasks.size()
                + (tasks.size() == 1 ? " task." : " tasks.") + "\n");
            return;
        }
        System.out.println("Much obliged Sir/Mdm! I shall delete this task:\n   "
            + task + "\n" + "Now you have " + tasks.size()
            + (tasks.size() == 1 ? " task." : " tasks.") + "\n");

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
            + list(foundTasks);

        if (isGui) {
            gui.displayDukeReply(message + "\n");
            return;
        }

        System.out.println(message);
        System.out.println();
    }

    /**
     * Informs user of all tasks found by matching words.
     *
     * @param foundTasks The tasks that have been found by matching words.
     */
    public void findByDescription(TaskList foundTasks) {
        String message = "Here are the results of the search Sir/Mdm:\n"
            + list(foundTasks);

        if (isGui) {
            gui.displayDukeReply(message + "\n");
            return;
        }

        System.out.println(message);
        System.out.println();
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

}
