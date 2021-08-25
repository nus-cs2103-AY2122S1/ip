package duke.command;

import duke.Duke;
import duke.DukeException;
import duke.Parser;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Task;

public class DeadlineCommand extends Command {
    private static final String COMMAND_WORD = "deadline";

    /**
     * Returns the command word for a deadline.
     *
     * @return "deadline" command representing a deadline.
     */
    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    /**
     * Creates a deadline task and adds it to the task list.
     *
     * @param duke   Duke instance that the command is called from.
     * @param parser Parser with the user's input
     * @throws DukeException If input is invalid.
     */
    @Override
    public void run(Duke duke, Parser parser) throws DukeException {
        parser.parseTask();
        parser.parseDate();
        Task task = new Deadline(parser.getTaskName(), parser.getDate());
        duke.getList().add(task);
        Ui.addTaskMessage(task, duke.getList().size());
    }
}
