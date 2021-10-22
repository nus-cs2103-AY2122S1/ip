package duke.commands;

import java.util.ArrayList;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Finds and lists events with the keywords provided by the user.
 */
public class FindCommand extends Command {
    private final String searchString;

    public FindCommand(String userInput) {
        this.searchString = userInput.substring(5);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.printMsg("You do not have any tasks right now.");
        } else {
            ArrayList<String> temp = new ArrayList<>();
            temp.add("Here are the matching tasks in your list:");

            for (int i = 0; i < tasks.size(); ++i) {
                if (tasks.get(i).getName().contains(searchString)) {
                    temp.add(String.format("%d.%s", temp.size(), tasks.get(i).toString()));
                }
            }

            ui.printMsg(temp.toArray(new String[0]));
        }
    }
}
