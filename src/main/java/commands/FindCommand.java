package commands;

import tasks.TaskList;
import utils.Storage;
import utils.Ui;

public class FindCommand extends Command{

    private final static String FIND_MESSAGE = "Here are the matching tasks in your list:";

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        String message = FIND_MESSAGE + "\n" + tasks.find(this.keyword);

        ui.printResponse(message);

        return true;
    }
}
