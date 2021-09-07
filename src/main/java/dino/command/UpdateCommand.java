package dino.command;

import dino.exception.*;
import dino.task.TaskList;
import dino.util.Parser;
import dino.util.Storage;

/**
 * Represents the command for which the user wants to make an update to the existing task list,
 * actions supported including marking a task as done, deleting a task, and editing a task
 */
public class UpdateCommand extends Command {

    private final String taskString;
    private final Parser.CMDTYPE cmdType;

    /**
     * Constructs an UpdateCommand object
     *
     * @param taskString the user input command
     * @param cmdType the type of the command, which can only be one of "done" or "delete"
     */
    public UpdateCommand(String taskString, Parser.CMDTYPE cmdType) {
        this.taskString = taskString;
        this.cmdType = cmdType;
    }

    /**
     * Executes the user input command to either mark a task as done, delete a task, or edit a task
     * based on the index of the task given
     * @param storage the local storage file
     * @param taskList the current task list
     * @return the output message after execution
     */
    @Override
    public String execute(Storage storage, TaskList taskList) {
        try {
            int index = getIndex(taskString, cmdType);
            switch (cmdType) {
            case DONE: {
                return taskList.markTaskDone(index);
            }
            case DELETE: {
                return taskList.deleteTask(index);
            }
            case EDIT: {
                String newTask = getNewTask(taskString);
                return taskList.editTask(newTask, index);
            }
            default: {
                throw new InvalidInputException();
            }
            }
        } catch (DinoException e) {
            return e.getMessage();
        }
    }

    /**
     * Extracts the index indicated by the done, delete, or update command
     * @param s the input command
     * @param type the type of the task, which can only be either "done", "delete", or "edit"
     * @return the index that is extracted from the command
     * @throws IndexNotSpecifiedException if the index of the task is not specified
     * @throws InvalidInputException if the message entered after the "done", "delete", or "edit"
     * instruction is not a number but some other string
     */
    public static int getIndex(String s, Parser.CMDTYPE type) throws
            IndexNotSpecifiedException, InvalidInputException {
        if (s.length() < type.toString().length() + 2) {
            throw new IndexNotSpecifiedException();
        }
        String thingsAfterCmd = s.substring(type.toString().length() + 1);
        if (thingsAfterCmd.trim().length() == 0) {
            throw new IndexNotSpecifiedException();
        }
        try {
            if (s.contains("/")) {
                return Integer.parseInt(s.substring(5, s.indexOf("/")));
            } else {
                return Integer.parseInt(thingsAfterCmd);
            }
        } catch (NumberFormatException e) {
            throw new InvalidInputException();
        }
    }

    /**
     * @param cmd the input command
     * @return the task after edition
     */
    public static String getNewTask(String cmd){
        boolean isValidTask = cmd.contains("todo") || cmd.contains("deadline") || cmd.contains("event");
        try {
            if (isValidTask && cmd.contains("/")) {
                return cmd.substring(cmd.indexOf("/") + 1);
            } else {
                throw new InvalidInputException();
            }
        } catch (InvalidInputException e) {
            return e.getMessage();
        }
    }

}


