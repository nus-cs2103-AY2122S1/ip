package command;

import task.Task;

/**
 * AddCommand represents a Command that adds a Task.
 *
 * @author Ho Wen Zhong
 */
public abstract class AddCommand extends Command {

    /**
     * Returns false to indicate AddCommand does not exit the program.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Returns a response to the user.
     *
     * @param task Task to add.
     * @param numOfTasks Number of tasks in TaskList.
     * @return String response.
     */
    public String respond(Task task, int numOfTasks) {
        String response = "Got it. I've added this task:\n"
                + task.toString() + "\n"
                + "Now you have " + numOfTasks
                + " tasks in the list.";
        return response;
    }
}
