package commands;

import tasks.TaskList;

/**
 * A command to delete a task from Duke's taskList.
 */
public class DeleteCommand extends TaskListIndexCommand {

    private final TaskList taskList;

    /**
     * Creates a DeleteCommand to delete a task from the taskList.
     *
     * @param input The input by the user that triggers this command.
     * @param taskList The taskList to remove the task from.
     */
    public DeleteCommand(String input, TaskList taskList) {
        super(input);
        this.taskList = taskList;
    }

    @Override
    protected String executeOnTaskList(int... listOfIndex) {
        StringBuilder message = new StringBuilder();
        for (int i : listOfIndex) {
            message.append(this.taskList.removeTask(i)).append("\n");
            this.offsetIndex(listOfIndex, i);
        }
        return message.append(this.taskList.getTaskListStatus()).toString();
    }

    /**
     * Offsets the input index after an item is deleted. After an item is deleted,
     * the tasks in the list would no longer align with the index that was initially
     * given by the user. Since Duke allows multiple deletes in one command, after
     * a task is deleted, this function will scan through the list of indexes and
     * decrement those that are after the item deleted.
     *
     * @param indexes The list of indexes provided by the user.
     * @param deletedIndex The previously deleted index.
     */
    private void offsetIndex(int[] indexes, int deletedIndex) {
        for (int i = 0; i < indexes.length; i++) {
            if (deletedIndex < indexes[i]) {
                indexes[i]--;
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getInvalidArgumentsMessage() {
        return "Invalid argument to the \"delete\" function.\n";
    }

}
