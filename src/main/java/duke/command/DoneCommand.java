package duke.command;

import duke.exceptions.DucIncompleteException;
import duke.exceptions.DucSyntaxErrorException;
import duke.main.TaskList;

public class DoneCommand extends Command {
    private static final String REPLY_DONE_ALL =
            "Wonderful! You've completed everything in your list!";
    private static final String REPLY_DONE =
            "Okay, I've marked the following task as done: \n";
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
            throw new DucIncompleteException();
        } else if (description.equalsIgnoreCase("all")) {
            taskList.doneAll();
            return REPLY_DONE_ALL;
        }
        try {
            int taskIndex = Integer.parseInt(description);
            return REPLY_DONE + taskList.done(taskIndex);
        } catch (NumberFormatException e) {
            throw new DucSyntaxErrorException(description);
        }
    }
}
