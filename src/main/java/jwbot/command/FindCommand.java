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
        List<Task> result = processSearchResult(tasks);
        return ui.showSearchList(result);
    }

    private List<Task> processSearchResult(TaskList tasks) {
        List<Task> result = new ArrayList<>();
        String keyword = input.split(" ", 2)[1];
        for (Task task : tasks.getItems()) {
            if (task.isRelevant(keyword)) {
                result.add(task);
            }
        }
        return result;
    }

    @Override
    protected Task processTask(TaskList tasks) {
        return null;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
