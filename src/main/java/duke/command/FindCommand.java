package duke.command;

import duke.ResponseFormatter;
import duke.Storage;
import duke.TaskList;
import duke.Ui;


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
     *  @param taskList current list
     * @param ui current ui to access print responses
     * @param storage current storage
     * @return
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printFound(taskList.findKeyword(this.keyword));
    }

    /**
     * Executes find command and search for keyword in list and returns response
     *
     * @param taskList current list
     * @param rf Response Formatter
     * @param storage current storage
     * @return formatted response
     */
    @Override
    public String execute(TaskList taskList, ResponseFormatter rf, Storage storage) {
        return rf.formatFound(taskList.findKeyword(this.keyword));
    }
}
