package duke.ui;

import duke.constant.EditType;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

/**
 * The Ui class encapsulates the interface that the user interacts with and what the user sees.
 */
public class Ui {
    /**
     * The logo for Duke.
     */
    private static final String LOGO =
            " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * A divider to separate messages displayed.
     */
    private static final String DIVIDER = "____________________________________________________________\n";

    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void printStartMessage() {
        System.out.println(DIVIDER
                + LOGO
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + DIVIDER
        );
    }

    public void printGoodbyeMessage() {
        System.out.println(DIVIDER
                + "Bye. Hope to see you again soon!\n"
                + DIVIDER
        );
    }

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

    public void updateUserOnAddedTask(Task newTask, int numberOfTasks) {
        System.out.println(DIVIDER
                + "Got it. I've added this task:\n"
                + newTask + '\n'
                + "Now you have "
                + numberOfTasks + (numberOfTasks == 1 ? " task" : " tasks") + " in the list.\n"
                + DIVIDER
        );
    }

    public void printErrorMessage(DukeException e) {
        System.out.println(DIVIDER
                + e + '\n'
                + DIVIDER
        );
    }

    public String readUserInput() {
        return this.sc.nextLine();
    }

    public void stop() {
        this.sc.close();
        printGoodbyeMessage();
    }
}
