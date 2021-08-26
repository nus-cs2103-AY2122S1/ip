public class DeadlineCommand extends Command{
    private String[] fields;

    public DeadlineCommand(String[] fields) {
        this.fields = fields;
    }

    @Override
    public void execute(TaskList taskList) {
        taskList.addTask(taskType.DEADLINE, fields);
        Ui.showAddedTask(taskList);
        Ui.showTaskCount(taskList);
    }

    @Override
    public String getType() {
        return "deadline";
    }

}
