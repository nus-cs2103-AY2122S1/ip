package blue.handler;

import blue.BlueException;
import blue.TaskList;
import blue.task.ToDo;

/**
 * Handles the todo command.
 */
public class ToDoHandler extends CommandHandler {
    public ToDoHandler(TaskList taskList) {
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
        if (input.contains(" ")) {
            int index = input.indexOf(" ");
            String title = input.substring(index + 1).strip();

            ToDo toDo = new ToDo(title);
            taskList.add(toDo);
            String response = "Got it. I've added this task:\n" + toDo + "\n";
            response += "Now you have " + taskList.getSize() + " tasks in the list.";
            return response;
        } else {
            throw new BlueException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }
}
