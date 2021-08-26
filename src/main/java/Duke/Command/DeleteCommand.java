package Duke.Command;

import Duke.DukeException.DukeIncompleteException;
import Duke.DukeException.DukeOutOfBoundException;
import Duke.DukeException.DukeSyntaxErrorException;
import Duke.Main.TaskList;
import Duke.Task.Task;

public class DeleteCommand extends Command {

    private String description;
    private TaskList taskList;
    public DeleteCommand(String description, TaskList taskList) {
        super(description, taskList);
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
                return "Noted! I've deleted the following task: \n" +
                        taskList.delete(index);
            }
        } catch (NumberFormatException e) {
            throw new DukeSyntaxErrorException(description);
        }
    }
}
