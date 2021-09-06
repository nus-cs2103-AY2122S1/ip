package commands;

import tasks.TaskList;

import java.util.ArrayList;

/**
 * A command to marks a task in Duke's taskList as done.
 */
public class DoneCommand extends TaskListIndexCommand {

    private final TaskList taskList;
    private final ArrayList<Integer> doneIndexes = new ArrayList<>();

    /**
     * Creates a DoneCommand to mark a task in the taskList as completed.
     *
     * @param input The input by the user that triggers this command.
     * @param taskList The taskList that contains the task to be mark as done.
     */
    public DoneCommand(String input, TaskList taskList) {
        super(input);
        this.taskList = taskList;
    }

    @Override
    protected String executeOnTaskList(int... listOfIndex) {
        StringBuilder message = new StringBuilder();
        for (int ofIndex : listOfIndex) {
            message.append(this.taskList.markTaskAsDone(ofIndex, this.doneIndexes)).append("\n");
        }
        setUndo();
        return message.append(this.taskList.getTaskListStatus()).toString();
    }

    @Override
    protected void setUndo() {
        this.setUndoFunction(() -> {
            for (int i : this.doneIndexes) {
                this.taskList.revertTaskToUndone(i);
            }
        });
    }


    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getInvalidArgumentsMessage() {
        return "Invalid argument to the \"done\" function.\n";
    }
}
