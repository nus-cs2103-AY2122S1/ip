package jwbot.command;

import jwbot.data.TaskList;
import jwbot.data.exception.JwBotException;
import jwbot.data.task.Deadline;
import jwbot.storage.Storage;
import jwbot.ui.Ui;

public class AddDeadlineCommand extends Command {

    public AddDeadlineCommand(String input) {
        super(input);
        assert input.startsWith("deadline");
    }

    /**
     * The method that executes the command
     *
     * @param tasks list of the tasks
     * @param ui the ui object responsible for user interaction
     * @param storage the storage object responsible for writing and reading txt file
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JwBotException {
        assert tasks != null;
        assert ui != null;
        assert storage != null;
        try {
            tasks.backupTasks();
            Deadline deadline = processTask(tasks);
            tasks.addTask(deadline);
            storage.write(tasks);
            return ui.showAddTaskSuccessMessage(deadline);
        } catch (Exception e) {
            throw new JwBotException("Sorry bro, I think you made an error with the deadline format!");
        }
    }

    @Override
    protected Deadline processTask(TaskList tasks) {
        String content = input.split(" ", 2)[1];
        String[] separated = content.split(" /by ");
        Deadline deadline = new Deadline(separated[0], separated[1]);
        return deadline;
    }

    /**
     * The method that checks if the bot should be stopped.
     *
     * @return the boolean false to not stop the bot
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
