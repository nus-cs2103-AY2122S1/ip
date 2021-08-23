import yoyoexception.YoyoException;

import static yoyoexception.YoyoException.YoyoIncompleteCommandException;

import java.time.LocalDateTime;

public class CommandEvent extends Command{
    CommandEvent(String[] inputTokens) {
        super(inputTokens);
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui)
            throws YoyoException {
        checkCompleteCommand(inputTokens);
        String[] taskInfo = inputTokens[1].split(" /at ");
        if (taskInfo.length < 2 ) {
            throw new YoyoIncompleteCommandException("command has bad format or"
                    + " not enough information.");
        } else {
            LocalDateTime datetime = Parser.parseTimeString(taskInfo[1]);
            Task newTask = new Event(taskInfo[0], datetime);
            tasks.add(newTask);
            ui.printAddMessage(newTask, tasks);
        }
    }
}
