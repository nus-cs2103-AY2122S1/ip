import java.util.List;

public class ListTasks extends TaskOperation {

    public ListTasks(List<Task> taskList) {
        super(taskList);
    }

    @Override
    void performOperation() {
        String description = listTasks(getTasks());
        setDescription(description);
    }

    protected String listTasks(List<Task> taskList) {
        if (taskList.isEmpty()) {
            return getTasksOverview();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            sb.append(String.format("%d.%s\n", i + 1, taskList.get(i).toString()));
        }
        return sb.toString();
    }
}
