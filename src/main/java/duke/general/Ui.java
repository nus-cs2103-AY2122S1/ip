package duke.general;

import java.util.ArrayList;
import java.util.Scanner;

import duke.error.DukeException;
import duke.task.Task;


/**
 * Deals with interactions with the user
 */
public class Ui {
    private Scanner textInput = new Scanner(System.in);
    private boolean isLoop = true;

    /**
     * Shows the welcome message to the user
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Takes in input from the user and returns a String of the input
     * @return Input by the user
     */
    public String readInput() {
        return textInput.nextLine();
    }

    /**
     * Shows response to the user for when a task is added
     * @param t Task that is being added
     * @param size Size of the tasklist
     */
    public void addResponse(Task t, int size) {
        t.addResponse(size);
    }

    /**
     * Shows response for done command
     * @param t Task to be marked as done
     */
    public void doneResponse(Task t) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t);
    }

    /**
     * Shows response for delete command
     * @param t Task to be deleted
     * @param list Tasklist to delete the task from
     */
    public void deleteResponse(Task t, Tasklist list) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(t);
        System.out.println("Now you have " + list.size() + " tasks in your list.");
    }

    /**
     * Shows the response for bye command
     */
    public void byeResponse() {
        System.out.println("Bye bye. Duke going to sleep now.");
    }

    /**
     * Shows the users what tasks in the tasklist matches their keyword.
     * If there are no tasks in the list, informs the users of not finding any matches
     *
     * @param list List of tasks that have matches
     */
    public void findResponse(ArrayList<Task> list) {
        if (list.size() > 0) {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 1; i <= list.size(); i++) {
                Task task = list.get(i - 1);
                System.out.println(i + "." + task.toString());
            }
        } else {
            System.out.println("No matching tasks have been found :(");
        }
    }

    /**
     * Displays the task list
     * @param t Tasklist
     */
    public void showList(Tasklist t) {
        try {
            if (t.size() == 0) {
                throw new DukeException("The list is empty!!");
            }
            System.out.println("Here are your tasks:");
            for (int i = 1; i <= t.size(); i++) {
                Task task = t.get(i - 1);
                System.out.println(i + "." + task.toString());
            }
        } catch (DukeException e) {
            System.out.println(e.toString().split(" ", 2)[1]);
        }
    }

    public boolean getLoop() {
        return this.isLoop;
    }

    public void setLoop() {
        this.isLoop = false;
    }

}
