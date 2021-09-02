package duke.command;

import duke.exceptions.DukeIncompleteException;
import duke.exceptions.DukeOutOfBoundException;
import duke.exceptions.DukeSyntaxErrorException;
import duke.main.TaskList;

public class DeleteCommand extends Command {

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
            throw new DukeIncompleteException();
        }
        try {
            if (description.equalsIgnoreCase("all")) {
                taskList.deleteAll();
                return "Noted! I've deleted all tasks and reset your list";
            } else {
                int index = Integer.parseInt(description);
                if (index > taskList.size() || index <= 0) {
                    throw new DukeOutOfBoundException();
                }
                return "Noted! I've deleted the following task: \n"
                        + taskList.delete(index);
            }
        } catch (NumberFormatException e) {
            throw new DukeSyntaxErrorException(description);
        }
    }
}
