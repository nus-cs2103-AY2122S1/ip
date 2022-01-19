package duchess.command;

import duchess.main.DuchessList;
import duchess.task.Task;

/**
 * This class contains the logic to handle the find command.
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */
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
        String reply = "";
        int counter = 0;
        for (int i = 0; i < duchessList.getSize(); i++) {
            Task t = duchessList.getTask(i + 1);
            if (t.contains(keyword)) {
                reply += String.format("%d. %s\n", ++counter, t);
            }
        }
        if (reply.isEmpty()) {
            return invalidMessage;
        } else {
            return reply;
        }
    }
}
