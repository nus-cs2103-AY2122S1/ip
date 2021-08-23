package jwbot.command;

import jwbot.data.TaskList;
import jwbot.data.exception.JWBotException;
import jwbot.data.task.Task;
import jwbot.storage.Storage;
import jwbot.ui.Ui;

public class DeleteCommand extends Command {

    public DeleteCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JWBotException {
        try {
            String[] separated = input.split(" ");
            int index = Integer.parseInt(separated[1]);
            Task task = tasks.remove(index - 1);
            storage.write(tasks);
            ui.showDeleteSuccessMessage(task, tasks.getSize());
        } catch (Exception e) {
            throw new JWBotException("Sorry bro, I think you chose an incorrect index number to delete!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
