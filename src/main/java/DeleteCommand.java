public class DeleteCommand extends Command{
    private int TaskNo;

    public DeleteCommand(int taskNo) {
        this.TaskNo = taskNo;
    }

    @Override
    public void execute(TaskList taskList) throws DukeException{
        Task deletedTask = taskList.deleteTask(TaskNo); //throws Duke Exception
        Ui.showDeletedTask(deletedTask);
        Ui.showTaskCount(taskList);
    }

    @Override
    public String getType() {
        return "delete";
    }
}