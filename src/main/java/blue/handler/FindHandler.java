package blue.handler;

import java.util.ArrayList;
import java.util.List;

import blue.BlueException;
import blue.Parser;
import blue.TaskList;
import blue.task.Task;

/**
 * Handles the find command.
 */
public class FindHandler extends CommandHandler {
    private static final String RESULT_START = "Here are the matching tasks in your list:\n";

    public FindHandler(TaskList taskList) {
        super(taskList);
    }

    @Override
    public String handle(String input) throws BlueException {
        List<Task> foundTasks = new ArrayList<>();
        String keyword = Parser.getArguments(input)[0];
        taskList.getAll()
                .stream()
                .filter((task) -> task.getTitle().contains(keyword))
                .forEach(foundTasks::add);
        StringBuilder response = new StringBuilder(RESULT_START);
        for (int i = 0; i < foundTasks.size(); i++) {
            String line = (i + 1) + "." + foundTasks.get(i) + "\n";
            response.append(line);
        }
        return response.toString();
    }
}
