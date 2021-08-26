public class DoneCommand extends Command{
    private int TaskNo;

    public DoneCommand(int taskNo) {
        this.TaskNo = taskNo;
    }

    @Override
    public void execute(TaskList taskList) throws DukeException{
        Task completedTask = taskList.doneTask(TaskNo); //throws DukeException
        Ui.showDoneTask(completedTask);
    }
    @Override
    public String getType() {
        return "done";
    }
}
