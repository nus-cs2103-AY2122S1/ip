package duke.command;

import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;

import duke.exception.InvalidInputException;
import duke.exception.NoActionException;
import duke.exception.NoTimeException;
import duke.exception.SaveFileException;

import duke.util.Parser;

import duke.task.Task;
import duke.task.Deadline;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    String action;
    public DeadlineCommand(String action) {
        super(false);
        this.action = action;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException,
            NoActionException, NoTimeException, SaveFileException {
        String[] deadlineInputs = action.split("/by", 2);
        if (deadlineInputs[0].length() == 0) {
            throw new NoActionException("duke.command.Command 'deadline' requires a task action");
        }
        if (deadlineInputs.length <=1){
            throw new NoTimeException(
                    "Command 'deadline' requires a deadline to be specified. Use /by to specify a deadline.");
        }
        LocalDateTime deadline = Parser.parseDate(deadlineInputs[1].trim());
        Task newTask = new Deadline(deadlineInputs[0].trim(), deadline);
        tasks.add(newTask);
        ui.showTaskAdded(newTask, tasks);
        storage.save(tasks);
    }
}
