package Yoyo.command;

import Yoyo.core.Parser;
import Yoyo.core.Storage;
import Yoyo.core.Ui;
import Yoyo.exception.YoyoException;
import Yoyo.task.Deadline;
import Yoyo.task.Task;
import Yoyo.task.TaskList;

import java.time.LocalDateTime;

public class CommandDeadline extends Command {
    public CommandDeadline(String[] inputTokens) {
        super(inputTokens);
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui)
            throws YoyoException {
        checkCompleteCommand(inputTokens);
        String[] taskInfo = inputTokens[1].split(" /by ");
        if (taskInfo.length < 2) {
            throw new YoyoException.YoyoIncompleteCommandException("command has bad format or"
                    + " not enough information.");
        } else {
            LocalDateTime datetime = Parser.parseTimeString(taskInfo[1]);
            Task newTask = new Deadline(taskInfo[0], datetime);
            tasks.add(newTask);
            ui.printAddMessage(newTask, tasks);
        }

    }
}
