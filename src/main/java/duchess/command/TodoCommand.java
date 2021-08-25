package duchess.command;

import duchess.main.Duchess;
import duchess.main.DuchessFileHandler;
import duchess.task.ToDo;

/**
 * This class contains the logic to handle the todo command.
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */

public class TodoCommand extends Command {

    /**
     * Constructs a TodoCommand.
     * @param name The description of the todo.
     */
    public TodoCommand(String name) {
        super(name);
    }

    /**
     * Handles the logic for checking and creating ToDo tasks.
     * @param duchess The Duchess to return the output to.
     * @return Whether to continue scanning for user input afterwards.
     */
    public boolean handleLogic(Duchess duchess) {
        String task = getName().split(" ", 2)[1];
        // Valid input
        ToDo todo = new ToDo(task);
        duchess.getDuchessList().add(todo);
        int listSize = duchess.getDuchessList().getSize();
        duchess.getUi().prettyPrint("Understood. I've added this task:\n    " + todo
                + "\nYou now have " + listSize
                + (listSize > 1 ? " tasks in the list." : " task in the list."));
        DuchessFileHandler.writeToFile(duchess.getDuchessList());
        return true;
    }

}
