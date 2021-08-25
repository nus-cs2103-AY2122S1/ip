package dino.command;

import dino.util.Storage;
import dino.task.TaskList;
import dino.exception.*;

/**
 * Represents the command for which the user wants to mark a task as done or to delete a task
 */
public class MarkCommand extends Command {

    String taskString;
    CMDTYPE markType;
    int index;

    /**
     * Constructs a MarkCommand object
     *
     * @param taskString the user input command
     * @param markType the type of the command, which can only be one of "done" or "delete"
     */
    public MarkCommand(String taskString, CMDTYPE markType) {
        this.taskString = taskString;
        this.markType = markType;
    }

    /**
     * Executes the user input command to either mark a task as done or to delete a task based on the
     * index of the task given
     * @param storage the local storage file
     * @param taskList the current task list
     * @throws InvalidIndexException if the index specified by the usr is out of the bounds of the task list
     * @throws TaskAlreadyDoneException if the user is trying to mark an already-finished task as done
     * @throws InvalidInputException if the user does not enter a number for the index
     * @throws IndexNotSpecifiedException if the user does not specify the index of the task to be marked
     * as done or to be deleted
     */
    @Override
    public void execute(Storage storage, TaskList taskList) throws InvalidIndexException, TaskAlreadyDoneException, InvalidInputException, IndexNotSpecifiedException {
        this.index = getIndex(taskString, markType);
        switch (markType) {
            case DONE: {
                taskList.markTaskDone(this.index);
                break;
            }
            case DELETE: {
                taskList.deleteTask(this.index);
            }
        }
    }


    public static int getIndex(String s, CMDTYPE type) throws IndexNotSpecifiedException, InvalidInputException, InvalidIndexException {
        if (s.length() < type.toString().length() + 2) {
            throw new IndexNotSpecifiedException();
        }
        String thingsAfterCmd = s.substring(type.toString().length() + 1);
        if (thingsAfterCmd.trim().length() == 0) throw new IndexNotSpecifiedException();
        try {
            int index = Integer.parseInt(thingsAfterCmd); //may throw NumberFormatException
            if (index < 1) throw new InvalidIndexException();
            return index;
        } catch (NumberFormatException e) {
            throw new InvalidInputException();
        }
    }

}


