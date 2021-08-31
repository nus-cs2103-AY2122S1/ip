package duke.commands;

import duke.tasks.Todo;

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
