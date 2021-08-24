package Duke.Command;

import Duke.Duke;
import Duke.DukeException;
import Duke.Parser;
import Duke.Task.Deadline;
import Duke.Task.Task;

public class DeadlineCommand extends Command{
    private static final String COMMAND_WORD = "deadline";

    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public void run(Duke duke, Parser parser) throws DukeException {
        parser.parseTask();
        parser.parseDate();
        Task task = new Deadline(parser.getTaskName(), parser.getDate());
        duke.getList().add(task);
        Duke.formatAndPrint(String.format("Got it. I've added this task:\n%s\nNow you have %d %s in your list.",
                task,
                duke.getList().size(),
                duke.getList().size() == 1 ? "task" : "tasks"));
    }
}
