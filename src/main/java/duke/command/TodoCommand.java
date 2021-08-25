package duke.command;

import duke.Duke;
import duke.DukeException;
import duke.Parser;
import duke.Ui;
import duke.task.Task;
import duke.task.Todo;

public class TodoCommand extends Command {
    private static final String COMMAND_WORD = "todo";

    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public void run(Duke duke, Parser parser) throws DukeException {
        parser.parseTask();
        Task task = new Todo(parser.getTaskName());
        duke.getList().add(task);
        Ui.addTaskMessage(task, duke.getList().size());
    }
}
