package blue.handler;

import blue.TaskList;

/**
 * Handles the list command.
 */
public class ListHandler extends CommandHandler {
    public ListHandler(TaskList taskList) {
        super(taskList);
    }

    /**
     * Handles the user input.
     *
     * @param input User input.
     * @return Response.
     */
    @Override
    public String handle(String input) {
        String response = "Here are the tasks in your list:\n";
        String[] lines = new String[taskList.getSize()];
        for (int i = 1; i <= taskList.getSize(); i++) {
            lines[i - 1] = i + ". " + taskList.get(i);
        }
        response += String.join("\n", lines);
        return response;
    }
}
