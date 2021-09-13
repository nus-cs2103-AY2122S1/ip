package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;
import duke.task.ToDo;

public class ToDoCommand extends Command {

    public static final String COMMAND_WORD = "todo";

    public static final String MISSING_DESCRIPTION_MESSAGE =
            "The description of the todo task cannot be empty";

    private String description;

    public ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        ToDo newTodo = new ToDo(description);
        tasks.addTask(newTodo);
        storage.save(tasks);
        return Ui.getAddedMessage(newTodo, tasks.size());
    }
}
