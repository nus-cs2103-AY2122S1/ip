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

    public void executeAction(DukeTaskList list) throws NonExistentTaskNumberException {
        DukeTask task = list.getTaskByTaskNumber(this.taskNumber);
        task.markAsDone();
        this.task = task;
    }

    public DukeDoneMessage getOutputMessage() {
        return new DukeDoneMessage(task.toString());
    }
}
