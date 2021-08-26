package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

public class FindCommand extends Command {
    public static final String COMMAND = "find";
    private String keyword;


    /**
     * Constructor for keyword
     *
     * @param keyword to search in list
     *
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes find command and search for keyword in list and print response
     *
     * @param taskList current list
     * @param ui current ui to access print responses
     * @param storage current storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printFound(taskList.findKeyword(this.keyword));
    }
}
