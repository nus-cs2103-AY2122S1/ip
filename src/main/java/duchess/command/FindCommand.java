package duchess.command;

import duchess.main.DuchessList;
import duchess.task.Task;

public class FindCommand extends Command {

    public FindCommand(String name) {
        super(name);
    }

    /**
     * Handles the logic for finding tasks containing a keyword.
     * @param duchessList The DuchessList to read or write tasks to.
     * @return The reply from Duchess to the user.
     */
    public String handleLogic(DuchessList duchessList) {
        String invalidMessage = "There are no tasks with that keyword.";
        String keyword = getDescription();
        String results = "";
        int counter = 0;
        for (int i = 0; i < duchessList.getSize(); i++) {
            Task t = duchessList.getTask(i + 1);
            if (t.contains(keyword)) {
                results += String.format("%d. %s\n", ++counter, t);
            }
        }
        if (results.isEmpty()) {
            return invalidMessage;
        } else {
            return results;
        }
    }
}
