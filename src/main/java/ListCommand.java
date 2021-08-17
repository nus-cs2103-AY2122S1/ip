import java.util.ArrayList;

public class ListCommand implements Command{
    @Override
    public void run() {
        if (TaskStorage.getInstance().getSize() == 0) {
            Message.print("No task added yet.");
            return;
        }
        ArrayList<Task> tasks = TaskStorage.getInstance().getAll();
        String[] messages = new String[TaskStorage.getInstance().getSize() + 1];
        messages[0] = "Here are the tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            messages[i + 1] = String.format("%d. %s", i + 1, tasks.get(i).toString());
        }
        Message.print(messages);
    }
}
