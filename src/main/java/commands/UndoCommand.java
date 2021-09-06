package commands;

import duke.Parser;
import tasks.TaskList;
import tasks.TaskListHistory;

/**
 * A command to undo any previous changes made to the taskList by the user.
 */
public class UndoCommand extends Command {

    private final TaskListHistory taskListHistory;
    private final Parser parser;

    /**
     * Creates a new UndoCommand that undo the previous change to the taskList.
     *
     * @param taskListHistory The history of taskList changes.
     * @param parser The parser that is used to parse user's inputs.
     */
    public UndoCommand(TaskListHistory taskListHistory, Parser parser) {
        this.taskListHistory = taskListHistory;
        this.parser = parser;
    }

    @Override
    public CommandReturnStatus execute() {
        if (this.taskListHistory.hasPreviousHistory()) {
            TaskList newCurrentTaskList = this.taskListHistory.revertToPrevious();
            newCurrentTaskList.saveTaskList();
            this.parser.undoCurrentTaskList(newCurrentTaskList);
            this.setExecutionMessage("Your taskList has been reverted to the previous version.\n");
            return CommandReturnStatus.UNDO_SUCCESSFUL;
        }
        this.setExecutionMessage("Unable to undo as there is no previous task list to revert to.\n");
        return CommandReturnStatus.UNSUCCESSFUL;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    protected String getInvalidArgumentsMessage() {
        // Not needed as the undo command has no arguments
        return null;
    }
}
