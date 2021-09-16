package jwbot.command;

import java.util.ArrayList;
import java.util.List;

import jwbot.data.TaskList;
import jwbot.data.exception.JwBotException;
import jwbot.data.task.Task;
import jwbot.storage.Storage;
import jwbot.ui.Ui;



public class FindCommand extends Command {

    public FindCommand(String input) {
        super(input);
        assert input.startsWith("find");
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JwBotException {
        List<Task> results = new ArrayList<>();
        String keyword = input.split(" ", 2)[1];
        for (Task task : tasks.getItems()) {
            if (task.includedOrNot(keyword)) {
                results.add(task);
            }
        }
        return ui.showSearchList(results);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
