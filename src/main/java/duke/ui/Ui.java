package duke.ui;

import java.util.Scanner;

import duke.constant.EditType;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The Ui class encapsulates the interface that the user interacts with and what the user sees.
 */
public class Ui {
    /** The logo for Duke. */
    private static final String LOGO =
            " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

    /** A divider to separate messages displayed. */
    private static final String DIVIDER = "____________________________________________________________\n";

    /** A scanner to read the user's input on the command line. */
    private Scanner sc;

    /**
     * Constructs a UI object to enable the command line to receive user input.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Displays a welcome message to the user.
     */
    public void printStartMessage() {
        System.out.println(DIVIDER
                + LOGO
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + DIVIDER
        );
    }

    /**
     * Displays a farewell message to the user.
     */
    public void printGoodbyeMessage() {
        System.out.println(DIVIDER
                + "Bye. Hope to see you again soon!\n"
                + DIVIDER
        );
    }

    /**
     * Displays a task list to the user.
     *
     * @param taskList The task list to be displayed.
     */
    public void printTaskList(TaskList taskList) {
        System.out.println(DIVIDER
                + "Here are the tasks in your list:\n"
                + taskList.toString()
                + DIVIDER
        );
    }

    /**
     * Displays the tasks matching the keyword searched by the user, if any.
     *
     * @param taskList The task list containing the tasks that match the keyword searched.
     */
    public void printMatchingTasks(TaskList taskList) {
        if (taskList.getNumberOfTasks() < 1) {
            System.out.println(DIVIDER
                    + "No matching tasks were found in your list.\n"
                    + DIVIDER
            );
        } else {
            System.out.println(DIVIDER
                    + "Here are the matching tasks I found in your list:\n"
                    + taskList.toString()
                    + DIVIDER
            );
        }
    }

    /**
     * Displays a task that has been edited to the user. If the task was deleted,
     * the number of remaining tasks on the task list is displayed as well.
     *
     * @param editedTask The task that has been edited.
     * @param numberOfTasks The number of tasks in the user's task list.
     * @param editType The edit done to the task.
     */
    public void updateUserOnEditedTask(Task editedTask, int numberOfTasks, EditType editType) {
        switch (editType) {
        case DONE:
            System.out.println(DIVIDER
                    + "Nice! I've marked this task as done:\n"
                    + editedTask.toString() + '\n'
                    + DIVIDER
            );
            break;
        case DELETE:
            System.out.println(DIVIDER
                    + "Got it! I've removed this task from the list:\n"
                    + editedTask.toString() + '\n'
                    + "Now you have "
                    + numberOfTasks + (numberOfTasks == 1 ? " task" : " tasks") + " in the list.\n"
                    + DIVIDER
            );
            break;
        default:
            throw new DukeException("Error in updating edited task.");
        }
    }

    /**
     * Displays a newly added task to the user, along with the number of tasks in the user's task list.
     *
     * @param newTask The recently added task.
     * @param numberOfTasks The number of tasks in the user's task list.
     */
    public void updateUserOnAddedTask(Task newTask, int numberOfTasks) {
        System.out.println(DIVIDER
                + "Got it. I've added this task:\n"
                + newTask + '\n'
                + "Now you have "
                + numberOfTasks + (numberOfTasks == 1 ? " task" : " tasks") + " in the list.\n"
                + DIVIDER
        );
    }

    /**
     * Displays an error message to the user if Duke encounters one.
     *
     * @param e The Duke exception encountered.
     */
    public void printErrorMessage(DukeException e) {
        System.out.println(DIVIDER
                + e + '\n'
                + DIVIDER
        );
    }

    /**
     * Reads in user input from the command line.
     *
     * @return A string representing the user's input in the command line.
     */
    public String readUserInput() {
        return this.sc.nextLine();
    }

    /**
     * Shuts down the UI.
     */
    public void stop() {
        this.sc.close();
        printGoodbyeMessage();
    }
}
