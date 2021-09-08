package duke.command;

import duke.exceptions.DukeIncompleteException;
import duke.exceptions.DukeSyntaxErrorException;
import duke.main.TaskList;

public class DoneCommand extends Command {
    private static final String REPLY_DONE_ALL =
            "Nice! I've marked all tasks in your list as done!";
    private static final String REPLY_DONE =
            "Nice! I've marked this task as done: \n";
    private final String description;
    private final TaskList taskList;

    /**
     * Constructor for Done Command
     * @param description command in task list to be marked as completed
     * @param taskList task list to be modified
     */
    public DoneCommand(String description, TaskList taskList) {
        this.description = description;
        this.taskList = taskList;
    }

    @Override
    public String reply() {
        if (description.length() == 0) {
            throw new DukeIncompleteException();
        } else if (description.equalsIgnoreCase("all")) {
            taskList.doneAll();
            return REPLY_DONE_ALL;
        }
        try {
            int taskIndex = Integer.parseInt(description);
            return REPLY_DONE + taskList.done(taskIndex);
        } catch (NumberFormatException e) {
            throw new DukeSyntaxErrorException(description);
        }
    }
}
