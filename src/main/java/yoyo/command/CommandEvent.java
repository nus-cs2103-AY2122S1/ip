package yoyo.command;

import java.time.LocalDateTime;

import yoyo.core.DialogHandler;
import yoyo.core.Parser;
import yoyo.core.Storage;
import yoyo.exception.YoyoException;
import yoyo.task.Event;
import yoyo.task.Task;
import yoyo.task.TaskList;

/**
 * A command subclass representing "event" command.
 */
public class CommandEvent extends Command {
    /**
     * Constructor for "event" command class.
     *
     * @param inputTokens Array of string tokens constructed from user input.
     */
    public CommandEvent(String[] inputTokens) {
        super(inputTokens);
    }

    /**
     * Executes "event" command.
     *
     * @param tasks         Tasks currently in the Yoyo program.
     * @param storage       Storage instance of the Yoyo program.
     * @param dialogHandler Ui instance of Yoyo program.
     * @return The result string to be shown to user.
     * @throws YoyoException
     */
    @Override
    public String execute(TaskList tasks, Storage storage, DialogHandler dialogHandler)
            throws YoyoException {
        checkTwoTokenCommand(inputTokens);
        String[] taskInfo = inputTokens[1].split(" /at ");

        if (taskInfo.length < 2) {
            throw new YoyoException.YoyoIncompleteCommandException("command has bad format or"
                    + " not enough information.");
        } else {
            LocalDateTime datetime = Parser.parseTimeString(taskInfo[1]);
            Task newTask = new Event(taskInfo[0], datetime);
            tasks.add(newTask);
            return dialogHandler.printAddMessage(newTask, tasks);
        }

    }
}
