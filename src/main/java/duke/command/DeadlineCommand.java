package duke.command;

import java.time.LocalDateTime;

import duke.exception.InvalidInputException;
import duke.exception.NoActionException;
import duke.exception.NoTimeException;
import duke.exception.SaveFileException;
import duke.task.Deadline;
import duke.task.Task;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class DeadlineCommand extends Command {
    private String action;
    public DeadlineCommand(String action) {
        super(false);
        this.action = action;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException,
            NoActionException, NoTimeException, SaveFileException {
        String[] deadlineInputs = action.split("/by", 2);
        if (deadlineInputs[0].length() == 0) {
            throw new NoActionException("Command 'deadline' requires a task action");
        }
        if (deadlineInputs.length <= 1) {
            throw new NoTimeException(
                    "Command 'deadline' requires a deadline to be specified. Use /by to specify a deadline.");
        }
        LocalDateTime deadline = Parser.parseDate(deadlineInputs[1].trim());
        Task newTask = new Deadline(deadlineInputs[0].trim(), deadline);
        tasks.add(newTask);
        ui.showTaskAdded(newTask, tasks);
        storage.save(tasks);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DeadlineCommand) {
            DeadlineCommand otherDeadlineCommand = (DeadlineCommand) obj;
            return otherDeadlineCommand.action.equals(this.action);
        }
        return false;
    }
}
