package duke.command;

import duke.Duke;
import duke.DukeException;
import duke.Parser;
import duke.Ui;
import duke.task.Task;
import duke.task.Todo;

public class TodoCommand extends Command {
    private static final String COMMAND_WORD = "todo";

    /**
     * Returns the command word for a to-do.
     *
     * @return "todo" command representing a to-do.
     */
    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    /**
     * Creates a to-do task and adds it to the task list.
     *
     * @param duke   Duke instance that the command is called from.
     * @param parser Parser with the user's input
     * @throws DukeException If input is invalid.
     */
    @Override
    public void run(Duke duke, Parser parser) throws DukeException {
        parser.parseTask();
        Task task = new Todo(parser.getTaskName());
        duke.getList().add(task);
        Ui.addTaskMessage(task, duke.getList().size());
    }
}
