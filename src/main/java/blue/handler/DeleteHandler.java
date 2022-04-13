package blue.handler;

import blue.BlueException;
import blue.Parser;
import blue.TaskList;
import blue.task.Task;

/**
 * Handles the delete command.
 */
public class DeleteHandler extends CommandHandler {
    public DeleteHandler(TaskList taskList) {
        super(taskList);
    }

    /**
     * Handles the user input.
     *
     * @param input User input.
     * @return Response
     * @throws BlueException If the input is invalid.
     */
    @Override
    public String handle(String input) throws BlueException {
        String[] arguments = Parser.getArguments(input);
        if (arguments.length == 0) {
            throw new BlueException("☹ OOPS!!! The index of delete cannot be empty.");
        }
        try {
            int index = Integer.parseInt(arguments[0]);
            Task task = taskList.remove(index);
            String response = "Noted. I've removed this task:\n" + task + "\n";
            response += "Now you have " + taskList.getSize() + " tasks in the list.";
            return response;
        } catch (NumberFormatException e) {
            throw new BlueException("☹ OOPS!!! Index must be a number.");
        } catch (IndexOutOfBoundsException e) {
            throw new BlueException("☹ OOPS!!! No task found at the specified index.");
        }
    }
}
