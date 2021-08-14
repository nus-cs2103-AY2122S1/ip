public class DukeActionAdd extends DukeAction {
    private String message;
    private DukeActionTypeEnum actionType;
    private DukeTask task;
    private DukeTaskList list;

    public DukeActionAdd(String message, DukeActionTypeEnum actionType) {
        this.message = message;
        this.actionType = actionType;
    }

    public static DukeActionAdd createAction(String message, DukeActionTypeEnum actionType) {
        return new DukeActionAdd(message, actionType);
    }

    public void executeAction(DukeTaskList list)
            throws InvalidTaskTypeException, InvalidTaskTimeFormatException, MissingTaskDescriptionException {
        DukeTask task = DukeTask.createTask(this.message, this.actionType);
        list.addTaskToList(task);
        this.task = task;
        this.list = list;
    }

    public DukeAddedMessage getOutputMessage() {
        return new DukeAddedMessage(task.toString(), list.getNumberOfTasks());
    }
}
