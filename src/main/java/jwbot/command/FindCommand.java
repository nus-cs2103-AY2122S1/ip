package jwbot.command;

import jwbot.data.TaskList;
import jwbot.data.exception.JWBotException;
import jwbot.data.task.Task;
import jwbot.storage.Storage;
import jwbot.ui.Ui;

import java.util.ArrayList;
import java.util.List;

public class FindCommand extends Command {

    public FindCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JWBotException {
        List<Task> results = new ArrayList<>();
        String keyword = input.split(" ", 2)[1];
        for (Task task : tasks.getItems()) {
            if (task.includedOrNot(keyword)) {
                results.add(task);
            }
        }
        ui.showSearchList(results);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
