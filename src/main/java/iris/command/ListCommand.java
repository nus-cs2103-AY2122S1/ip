package iris.command;

import iris.TaskList;

public class ListCommand extends Command {
    @Override
    public String run(TaskList tasks) {
        assert tasks != null;
        StringBuilder response = new StringBuilder();
        for (int i = 0; i < tasks.getCount(); i++) {
            response.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
        }
        return response.toString();
    }
}
