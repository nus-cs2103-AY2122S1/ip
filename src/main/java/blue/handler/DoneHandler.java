package blue.handler;

import blue.BlueException;
import blue.Parser;
import blue.TaskList;
import blue.task.Task;

/**
 * Handles the done command.
 */
public class DoneHandler extends CommandHandler {
    public DoneHandler(TaskList taskList) {
        super(taskList);
    }

    /**
     * Handles the user input.
     * 
     * @param input User input.
     * @return Response.
     * @throws BlueException If the user input is invalid.
     */
    @Override
    public String handle(String input) throws BlueException {
        String[] arguments = Parser.getArguments(input);
        if (arguments.length > 0) {
            try {
                int index = Integer.parseInt(arguments[0]);
                if (1 <= index && index <= taskList.size()) {
                    Task task = taskList.get(index);
                    task.markDone();
                    return "Nice! I've marked this task as done:\n" + task;
                } else
                    throw new BlueException("☹ OOPS!!! No task found at index " + index + ".");
            } catch (NumberFormatException e) {
                throw new BlueException("☹ OOPS!!! Index must be a number.");
            }
        } else {
            throw new BlueException("☹ OOPS!!! The index of done cannot be empty.");
        }
    }
}
