package duke.views.cli.strategies.commands;

import duke.domain.Task;
import duke.domain.TaskList;
import duke.domain.Todo;

public class AddTodoCommand extends TaskCommand {
    private static Command singleInstance;

    public AddTodoCommand(TaskList taskList) {
        super(taskList);
    }

    public static Command getInstance(TaskList taskList) {
        if (singleInstance == null) {
            singleInstance = new AddTodoCommand(taskList);
        }
        return singleInstance;
    }

    @Override
    public String produce(String query) {
        assert query != null;
        Task task = new Todo(query.substring(todo.length()).strip());
        boolean isUnique = userTasks.add(task);
        if (!isUnique) {
            return getDuplicateMessage();
        }
        return formatAdd(task);
    }
}
