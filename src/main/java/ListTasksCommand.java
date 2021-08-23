public class ListTasksCommand extends Command {

    @Override
    public String runAndGenerateDescription(TaskList taskList, Ui ui, Storage storage) {
        return taskList.listTasks();
    }
}
