package Duke.Command;

import Duke.DukeException.DukeException;
import Duke.DukeException.DukeIncompleteException;
import Duke.DukeException.DukeOutOfBoundException;
import Duke.DukeException.DukeSyntaxErrorException;
import Duke.Main.TaskList;

public class DoneCommand extends Command {

    private String description;
    private TaskList taskList;
    public DoneCommand(String description, TaskList taskList) {
        super(description, taskList);
        this.description = description;
        this.taskList = taskList;
    }

    @Override
    public String reply() {
        if (description.length() == 0) {
            throw new DukeIncompleteException();
        }
        try {
            if (description.equalsIgnoreCase("all")) {
                taskList.doneAll();
                return "Nice! I've marked all tasks in your list as done!";
            } else {
                int index = Integer.parseInt(description);
                return "Nice! I've marked this task as done: \n" +
                        taskList.done(index);
            }
        } catch (NumberFormatException e) {
            throw new DukeSyntaxErrorException(description);
        }
    }
}
