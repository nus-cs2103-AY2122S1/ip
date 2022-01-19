package duchess.command;

import duchess.main.DuchessFileHandler;
import duchess.main.DuchessList;
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
     * @param duchessList The DuchessList to read or write tasks to.
     * @return The reply from Duchess to the user.
     */
    public String handleLogic(DuchessList duchessList) {
        String task = getDescription();
        // Valid input
        ToDo todo = new ToDo(task);
        if (duchessList.checkForDuplicates(todo)) {
            return "This todo already exists in your list.";
        }
        duchessList.add(todo);
        int listSize = duchessList.getSize();
        String reply = "Understood. I've added this task:\n    " + todo
                + "\nYou now have " + listSize
                + (listSize > 1 ? " tasks in the list." : " task in the list.");
        DuchessFileHandler.writeToFile(duchessList);
        assert !reply.isBlank() : "Reply should not be empty.";
        return reply;
    }

}
