package Duke.Command;

import Duke.Duke;
import Duke.DukeException;
import Duke.Parser;
import Duke.Task.Task;
import Duke.Task.Todo;

public class TodoCommand extends Command{
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
        Duke.formatAndPrint(String.format("Got it. I've added this task:\n%s\nNow you have %d %s in your list.",
                task,
                duke.getList().size(),
                duke.getList().size() == 1 ? "task" : "tasks"));
    }
}
