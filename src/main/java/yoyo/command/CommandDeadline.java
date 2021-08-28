package yoyo.command;

import java.time.LocalDateTime;

import yoyo.core.Parser;
import yoyo.core.Storage;
import yoyo.core.Ui;
import yoyo.exception.YoyoException;
import yoyo.task.Deadline;
import yoyo.task.Task;
import yoyo.task.TaskList;

public class CommandDeadline extends Command {
    public CommandDeadline(String[] inputTokens) {
        super(inputTokens);
    }

    /**
     * Executes "deadline" command.
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
