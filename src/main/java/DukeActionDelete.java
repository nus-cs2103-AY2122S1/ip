public class DukeActionDelete extends DukeAction {
    private int taskNumber;
    private DukeTask task;
    private DukeTaskList list;

    public DukeActionDelete(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public static DukeActionDelete createAction(String message) throws InvalidTaskNumberException {
        return new DukeActionDelete(DukeAction.getTaskNumberFromMessage(message));
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
