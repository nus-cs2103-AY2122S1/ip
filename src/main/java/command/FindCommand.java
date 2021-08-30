package command;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import task.Task;

public class FindCommand extends Command {
    /** The keyword to search. **/
    private final String keyword;

    /**
     * A public constructor to initialized the FindCommand.
     *
     * @param keyword
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * The method to execute this FindCommand. When the method is executed,
     * it will scan through the given TaskList to find all the tasks that
     * contain the keyword in their description. It will send the result to
     * the given Ui for printing.
     *
     * @param taskList The given Duke TaskList.
     * @param ui The given Duke Ui.
     * @param storage The given Duke Storage.
     * @throws DukeException Exception thrown when execute the FindCommand.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> findResult = new ArrayList<>();
        Pattern pattern = Pattern.compile(keyword, Pattern.CASE_INSENSITIVE);
        for (int i = 0; i < taskList.amountOfTasks(); i++) {
            Matcher matcher = pattern.matcher(taskList.getTask(i).getDescription());
            if (matcher.find()) {
                findResult.add(taskList.getTask(i));
            }
        }

        String[] findResultString = new String[findResult.size() + 1];
        findResultString[0] = "Here are the matching tasks in your list:";
        for (int i = 0; i < findResult.size(); i++) {
            findResultString[i + 1] = "  " + (i + 1) + ". " + findResult.get(i).toString();
        }
        ui.printMessage(findResultString);
    }
}
