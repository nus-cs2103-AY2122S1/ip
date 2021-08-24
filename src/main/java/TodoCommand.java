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
     * @param input The user given input.
     * @param duchess The Duchess to return the output to.
     * @return Whether to continue scanning for user input afterwards.
     */
    public boolean handleLogic(String input, Duchess duchess) {
        String task = input.split(" ", 2)[1];
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
