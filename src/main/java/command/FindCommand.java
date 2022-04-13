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
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> findResult = new ArrayList<>();
        Pattern pattern = Pattern.compile(keyword, Pattern.CASE_INSENSITIVE);
        for (int i = 0; i < taskList.amountOfTasks(); i++) {
            System.out.println("[" + taskList.getTask(i).getDescription() + "]");
            System.out.println("[" + keyword + "]");
            Matcher matcher = pattern.matcher(taskList.getTask(i).getDescription());
            System.out.println(taskList.getTask(i).getDescription() + matcher.find());
            if (matcher.matches()) {
                findResult.add(taskList.getTask(i));
            }
        }

        if (findResult.size() == 0) {
            return ui.generateDukeResponse("Sorry, no matching task found.");
        } else {
            String[] findResultString = new String[findResult.size() + 1];
            findResultString[0] = "Here are the matching task found:";
            for (int i = 0; i < findResult.size(); i++) {
                findResultString[i + 1] = "  " + (i + 1) + ". " + findResult.get(i).toString();
            }
            return ui.generateDukeResponse(findResultString);
        }
    }
}
