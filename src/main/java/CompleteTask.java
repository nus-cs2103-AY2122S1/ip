public class CompleteTask extends Command {
    // zero indexed
    private int taskNum;

    // taskNum is number in task list, one indexed
    public CompleteTask(int taskNum) {
        this.taskNum = taskNum - 1;
    }

    @Override
    void execute(ToDo taskList, Ui ui, Storage storage) throws KermitException {
        try {
            Task task = taskList.completeTask(taskNum);
            ui.showCompleteTaskMessage(task);
            storage.save(taskList);
        } catch (IndexOutOfBoundsException e) {
            throw new KermitException("That is an invalid task!");
        }
    }

    @Override
    boolean isExit() {
        return false;
    }
}