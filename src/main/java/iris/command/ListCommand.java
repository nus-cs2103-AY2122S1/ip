package iris.command;

import iris.TaskList;

public class ListCommand extends Command {
    @Override
    public String run(TaskList tasks) {
        assert tasks != null;

        if (tasks.getCount() == 0) {
            return "You do not have any tasks yet.\nUse todo, event or deadline to create a new task.";
        }

        StringBuilder response = new StringBuilder();
        response.append("Here's your list of tasks:\n");
        for (int i = 0; i < tasks.getCount(); i++) {
            response.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
        }
        return response.toString();
    }
}
