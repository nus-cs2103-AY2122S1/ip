package duke.commands;

import duke.exceptions.InvalidTimeStampException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.tasks.Todo;
import duke.utils.Ui;

public class AddTodoCommand extends AddTaskCommand {

    /**
     * AddTodoCommand constructor.
     *
     * @param keywords Array containing the Todo details.
     */
    public AddTodoCommand(String[] keywords) {
        super(new Todo(keywords[0]));
    }
}
