package jwbot.command;

import jwbot.data.TaskList;
import jwbot.data.exception.JwBotException;
import jwbot.data.task.Task;
import jwbot.storage.Storage;
import jwbot.ui.Ui;

public class DoneCommand extends Command {

    public DoneCommand(String input) {
        super(input);
        assert input.startsWith("done");
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
        try {
            Task task = processTask(tasks);
            task.markAsDone();
            storage.write(tasks);
            return ui.showDoneSuccessMessage(task);
        } catch (Exception e) {
            throw new JwBotException("Sorry bro, I think you chose an incorrect index number to mark done!");
        }
    }

    @Override
    protected Task processTask(TaskList tasks) {
        String[] separated = input.split(" ");
        int index = Integer.parseInt(separated[1]);
        Task task = tasks.getTask(index - 1);
        return task;
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
