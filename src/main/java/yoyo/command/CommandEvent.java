package yoyo.command;

import yoyo.core.Parser;
import yoyo.core.Storage;
import yoyo.core.Ui;
import yoyo.exception.YoyoException;
import yoyo.task.Event;
import yoyo.task.Task;
import yoyo.task.TaskList;

import java.time.LocalDateTime;

public class CommandEvent extends Command{
    public CommandEvent(String[] inputTokens) {
        super(inputTokens);
    }

    /**
     * Executes "event" command.
     *
     * @param tasks Tasks currently in the Yoyo program.
     * @param storage Storage instance of the Yoyo program.
     * @param ui Ui instance of Yoyo program.
     * @throws YoyoException
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui)
            throws YoyoException {
        checkCompleteCommand(inputTokens);
        String[] taskInfo = inputTokens[1].split(" /at ");
        if (taskInfo.length < 2 ) {
            throw new YoyoException.YoyoIncompleteCommandException("command has bad format or"
                    + " not enough information.");
        } else {
            LocalDateTime datetime = Parser.parseTimeString(taskInfo[1]);
            Task newTask = new Event(taskInfo[0], datetime);
            tasks.add(newTask);
            ui.printAddMessage(newTask, tasks);
        }
    }
}
