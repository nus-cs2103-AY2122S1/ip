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
import duke.task.Event;

import java.time.LocalDateTime;

public class EventCommand extends Command {
    String action;
    public EventCommand(String action) {
        super(false);
        this.action = action;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException,
            NoTimeException, NoActionException, SaveFileException {
            if (action.trim().length() == 0) {
                throw new NoActionException("duke.command.Command 'event' requires a task action");
            }
            String[] eventInputs = action.split("/at", 2);
            if (eventInputs.length <=1){
                throw new NoTimeException(
                        "Command 'event' requires a time to be specified. Use /at to specify a time.");
            }
            LocalDateTime date = Parser.parseDate(eventInputs[1].trim());
            Task newTask = new Event(eventInputs[0].trim(), date);
            tasks.add(newTask);
            ui.showTaskAdded(newTask, tasks);
            storage.save(tasks);
    }
}
