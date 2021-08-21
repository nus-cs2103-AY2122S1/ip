package action;

import entity.list.DukeTask;
import entity.list.DukeTaskList;
import entity.message.DoneMessage;
import exception.ErrorAccessingFile;
import exception.InvalidTaskNumberException;
import exception.MissingActionDescriptionException;
import exception.NonExistentTaskNumberException;
import type.DukeActionTypeEnum;

public class DukeActionDone extends DukeAction {
    private int taskNumber;
    private DukeTask task;

    public DukeActionDone(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public static DukeActionDone createAction(String description)
            throws InvalidTaskNumberException, MissingActionDescriptionException {
        // Validate before creating the action
        DukeAction.validateDescriptionNotEmpty(DukeActionTypeEnum.DONE, description);

        return new DukeActionDone(DukeAction.getTaskNumberFromMessage(description));
    }

    public void executeAction(DukeTaskList list) throws NonExistentTaskNumberException, ErrorAccessingFile {
        this.task = list.markTaskAsDone(taskNumber);
    }

    public DoneMessage getOutputMessage() {
        return new DoneMessage(task.toString());
    }
}
