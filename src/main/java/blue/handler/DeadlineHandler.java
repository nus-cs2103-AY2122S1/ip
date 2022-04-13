package blue.handler;

import blue.BlueException;
import blue.TaskList;
import blue.task.Deadline;

/**
 * Handles the deadline command.
 */
public class DeadlineHandler extends CommandHandler {
    public DeadlineHandler(TaskList taskList) {
        super(taskList);
    }

    /**
     * Handles the user input.
     *
     * @param input User input.
     * @return Response.
     * @throws BlueException If the time of deadline is not specified.
     */
    @Override
    public String handle(String input) throws BlueException {
        if (input.contains(" /by ")) {
            int indexSpace = input.indexOf(" ");
            int indexBy = input.indexOf(" /by ");
            String title = input.substring(indexSpace + 1, indexBy).strip();
            String by = input.substring(indexBy + 5).strip();

            Deadline deadline = new Deadline(title, by);
            taskList.add(deadline);
            String response = "Got it. I've added this task:\n" + deadline + "\n";
            response += "Now you have " + taskList.getSize() + " tasks in the list.";
            return response;
        } else {
            throw new BlueException("☹ OOPS!!! The time of a deadline cannot be empty.");
        }
    }
}
