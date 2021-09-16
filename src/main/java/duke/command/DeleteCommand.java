package duke.command;

import duke.exceptions.DucIncompleteException;
import duke.exceptions.DucSyntaxErrorException;
import duke.main.TaskList;

public class DeleteCommand extends Command {

    private static final String REPLY_DELETE_ALL =
            "Consider it done! The list is now as empty as your pathetic life";
    private static final String REPLY_DELETE =
            "Noted! I've deleted the following task: \n";
    private final String description;
    private final TaskList taskList;

    /**
     * Constructor for the Delete Command class
     * @param description description of what to delete
     * @param taskList task list to be modified
     */
    public DeleteCommand(String description, TaskList taskList) {
        this.description = description;
        this.taskList = taskList;
    }

    @Override
    public String reply() {
        if (description.length() <= 0) {
            throw new DucIncompleteException();
        } else if (description.equalsIgnoreCase("all")) {
            taskList.deleteAll();
            return REPLY_DELETE_ALL;
        }
        int index;
        try {
            index = Integer.parseInt(description);
            return REPLY_DELETE + taskList.delete(index);
        } catch (NumberFormatException e) {
            throw new DucSyntaxErrorException(description);
        }
    }
}
