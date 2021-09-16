package duke.commands;

import duke.exceptions.DuplicateTaskException;
import duke.tasks.ToDo;
import duke.utils.TaskList;

public class AddTodo extends Command {
    private String taskName;

    public AddTodo(String taskName) {
        System.out.println(taskName);
        this.taskName = taskName;
    }

    @Override
    public TaskList execute(TaskList taskList) throws DuplicateTaskException {
        ToDo toDo = new ToDo(this.taskName);
        taskList.add(toDo);
        return taskList;
    }
}
