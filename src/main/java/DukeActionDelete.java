public class DukeActionDelete extends DukeAction {
    private int taskNumber;
    private DukeTask task;
    private DukeTaskList list;

    public DukeActionDelete(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public static DukeActionDelete createAction(String description) throws InvalidTaskNumberException, MissingActionDescriptionException {
        // Validate before creating the action
        DukeAction.validateDescriptionNotEmpty(DukeActionTypeEnum.DELETE, description);

        return new DukeActionDelete(DukeAction.getTaskNumberFromMessage(description));
    }

    public void executeAction(DukeTaskList list) throws NonExistentTaskNumberException {
        DukeTask task = list.getTaskByTaskNumber(this.taskNumber);
        list.deleteTaskFromList(this.taskNumber);
        this.task = task;
        this.list = list;
    }

    public DukeDeletedMessage getOutputMessage() {
        return new DukeDeletedMessage(task.toString(), list.getNumberOfTasks());
    }
}
