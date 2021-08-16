public class DukeActionAdd extends DukeAction {
    private String description;
    private DukeActionTypeEnum actionType;
    private DukeTask task;
    private DukeTaskList list;

    public DukeActionAdd(String description, DukeActionTypeEnum actionType) {
        this.description = description;
        this.actionType = actionType;
    }

    public static DukeActionAdd createAction(String description, DukeActionTypeEnum actionType)
            throws MissingActionDescriptionException {
        // Validate before creating the action
        DukeAction.validateDescriptionNotEmpty(actionType, description);

        return new DukeActionAdd(description, actionType);
    }

    public void executeAction(DukeTaskList list) throws InvalidTaskTypeException, InvalidTaskTimeFormatException {
        DukeTask task = DukeTask.createTask(this.description, this.actionType);
        list.addTaskToList(task);
        this.task = task;
        this.list = list;
    }

    public DukeAddedMessage getOutputMessage() {
        return new DukeAddedMessage(task.toString(), list.getNumberOfTasks());
    }
}
