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
    public String showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        return "Hello from\n" + logo;
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
     * @return Add response message
     */
    public String addResponse(Task t, int size) {
        assert(t != null) : "Unable to print response for null task";
        return t.addResponse(size);
    }

    /**
     * Shows response for done command
     * @param t Task to be marked as done
     * @return Done response message
     */
    public String doneResponse(Task t) {
        assert(t != null);
        return "Nice! I've marked this task as done:\n" + t;
    }

    /**
     * Shows response for delete command
     * @param t Task to be deleted
     * @param list Tasklist to delete the task from
     * @return Delete response message
     */
    public String deleteResponse(Task t, Tasklist list) {
        return "Noted. I've removed this task:\n" + t.toString() + "\n"
                + "Now you have " + list.size() + " tasks in your list.";
    }

    /**
     * Shows the response for bye command
     * @return Bye response message
     */
    public String byeResponse() {
        return "Bye bye. duke.Duke going to sleep now.";
    }

    /**
     * Shows the users what tasks in the tasklist matches their keyword.
     * If there are no tasks in the list, informs the users of not finding any matches
     *
     * @param list List of tasks that have matches
     * @return Find response message - a list of tasks that match the keyword
     */
    public String findResponse(ArrayList<Task> list) {
        if (list.size() > 0) {
            StringBuilder output = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 1; i <= list.size(); i++) {
                Task task = list.get(i - 1);
                String line = i + "." + task.toString();
                output.append(line).append("\n");
            }
            return output.toString();
        } else {
            return "No matching tasks have been found :(";
        }
    }

    /**
     * Displays the task list
     * @param t Tasklist
     * @return Message containing all tasks in the list
     */
    public String showList(Tasklist t) throws DukeException {
        if (t.size() == 0) {
            throw new DukeException("The list is empty!!");
        }
        StringBuilder output = new StringBuilder("Here are your tasks:\n");
        for (int i = 1; i <= t.size(); i++) {
            Task task = t.get(i - 1);
            String line = i + "." + task.toString();
            output.append(line).append("\n");
        }
        return output.toString();
    }

    public boolean getLoop() {
        return this.isLoop;
    }

    public void setLoop() {
        this.isLoop = false;
    }

}
