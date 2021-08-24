package duke.command;

import duke.Duke;
import duke.DukeException;
import duke.Parser;
import duke.task.Deadline;
import duke.task.Task;
import duke.Ui;

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
        Ui.addTaskMessage(task, duke.getList().size());
    }
}
