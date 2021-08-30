package dino.command;

import dino.exception.*;
import dino.task.TaskList;
import dino.util.Parser;
import dino.util.Storage;

/**
 * Represents the command for which the user wants to mark a task as done or to delete a task
 */
public class MarkCommand extends Command {

    private final String taskString;
    private final Parser.CMDTYPE markType;

    /**
     * Constructs a MarkCommand object
     *
     * @param taskString the user input command
     * @param markType the type of the command, which can only be one of "done" or "delete"
     */
    public MarkCommand(String taskString, Parser.CMDTYPE markType) {
        this.taskString = taskString;
        this.markType = markType;
    }

    /**
     * Executes the user input command to either mark a task as done or to delete a task based on the
     * index of the task given
     * @param storage the local storage file
     * @param taskList the current task list
     * @return the output message after execution
     * @throws InvalidIndexException if the index specified by the user
     * is out of the bounds of the task list
     * @throws TaskAlreadyDoneException if the user is trying to mark an already-finished task as done
     * @throws InvalidInputException if the user does not enter a number for the index
     * @throws IndexNotSpecifiedException if the user does not specify the index of
     * the task to be marked as done or to be deleted
     */
    @Override
    public String execute(Storage storage, TaskList taskList)
            throws InvalidIndexException, TaskAlreadyDoneException,
            InvalidInputException, IndexNotSpecifiedException {
        int index = getIndex(taskString, markType);
        switch (markType) {
        case DONE: {
            return taskList.markTaskDone(index);
        }
        case DELETE: {
            return taskList.deleteTask(index);
        }
        default: {
            throw new InvalidInputException();
        }
        }
    }


    /**
     * Extracts the index indicated by the done or delete command
     * @param s the input command
     * @param type the type of the task, which can only be either "done" or "delete"
     * @return the index that is extracted from the command
     * @throws IndexNotSpecifiedException if the index of the task is not specified
     * @throws InvalidInputException if the message entered after the "done" or "delete"
     * instruction is not a number but some other string
     * @throws InvalidIndexException if the index specified is out of bounds
     */
    public static int getIndex(String s, Parser.CMDTYPE type)
            throws IndexNotSpecifiedException, InvalidInputException, InvalidIndexException {
        if (s.length() < type.toString().length() + 2) {
            throw new IndexNotSpecifiedException();
        }
        String thingsAfterCmd = s.substring(type.toString().length() + 1);
        if (thingsAfterCmd.trim().length() == 0) {
            throw new IndexNotSpecifiedException();
        }
        try {
            int index = Integer.parseInt(thingsAfterCmd); //may throw NumberFormatException
            if (index < 1) {
                throw new InvalidIndexException();
            }
            return index;
        } catch (NumberFormatException e) {
            throw new InvalidInputException();
        }
    }

}


