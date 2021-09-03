package duke.views.cli.strategies.commands;

import duke.domain.TaskList;

public class ListCommand extends TaskCommand {
    private static Command singleInstance;

    public ListCommand(TaskList taskList) {
        super(taskList);
    }

    public static Command getInstance(TaskList taskList) {
        if (singleInstance == null) {
            singleInstance = new ListCommand(taskList);
        }
        return singleInstance;
    }

    @Override
    public String produce(String _query) {
        return listTasksWithMessage("Here are the tasks in your list:",
                userTasks, "You're clear (for now)");
    }
}
