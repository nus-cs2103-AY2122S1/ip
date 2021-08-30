package lania.command;

import lania.Storage;
import lania.Ui;
import lania.task.TaskList;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        TaskList temp = tasks.find(keyword);
        ui.showListMessage(temp);
    }
}
