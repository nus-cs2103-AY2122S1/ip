public class ToDoCommand extends Command{
    private String description;

    public ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList) {
        String[] fields = new String[] {description};
        taskList.addTask(taskType.TODO, fields);
        Ui.showAddedTask(taskList);
        Ui.showTaskCount(taskList);
    }

    @Override
    public String getType() {
        return "todo";
    }
}

