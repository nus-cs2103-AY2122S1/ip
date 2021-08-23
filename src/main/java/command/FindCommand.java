package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import dukeException.DukeException;
import task.Task;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindCommand extends Command {
    public String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> findResult = new ArrayList<>();
        Pattern pattern = Pattern.compile(keyword, Pattern.CASE_INSENSITIVE);
        for (int i = 0; i < taskList.amountOfTasks(); i++) {
            Matcher matcher = pattern.matcher(taskList.getTask(i).description);
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
