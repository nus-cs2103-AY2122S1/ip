package commands;

import viper.Storage;
import viper.TaskList;
import viper.Ui;

/**
 * Command that search through task list and returns a new task list with items that match the keyword
 */
public class FindCommand extends Command {
    
    protected String keyword;
    
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList findResult = tasks.findTask(keyword);
        ui.showList(findResult, keyword);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
