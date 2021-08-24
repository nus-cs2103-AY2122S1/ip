package duke.commands;

import duke.utils.Storage;
import duke.utils.TaskList;
import duke.tasks.Todo;
import duke.utils.Ui;

public class AddTodoCommand extends AddTaskCommand {

    private String[] keyWords;
    public AddTodoCommand(String[] keywords) {
        super(new Todo(keywords[0]));
    }
}
