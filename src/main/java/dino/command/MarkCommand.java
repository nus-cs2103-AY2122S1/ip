package dino.command;

import dino.util.Storage;
import dino.task.TaskList;
import dino.exception.*;

public class MarkCommand extends Command {

    String taskString;
    CMDTYPE markType;
    int index;

    public MarkCommand(String taskString, CMDTYPE markType) {
        this.taskString = taskString;
        this.markType = markType;
    }

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


