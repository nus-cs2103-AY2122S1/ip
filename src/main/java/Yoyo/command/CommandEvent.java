package Yoyo.command;

import Yoyo.core.Parser;
import Yoyo.core.Storage;
import Yoyo.core.Ui;
import Yoyo.exception.YoyoException;
import Yoyo.task.Event;
import Yoyo.task.Task;
import Yoyo.task.TaskList;

import java.time.LocalDateTime;

public class CommandEvent extends Command{
    public CommandEvent(String[] inputTokens) {
        super(inputTokens);
    }

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
