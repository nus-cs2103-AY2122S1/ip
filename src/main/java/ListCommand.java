import java.util.ArrayList;

public class ListCommand implements Command{
    @Override
    public String run() throws DukeException {
        if (TaskList.getInstance().getSize() == 0) {
            Message.print("No task added yet.");
            return "";
        }
        ArrayList<Task> tasks = TaskList.getInstance().getAll();
        String message = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            message += String.format("\t\t%d. %s\n", i + 1, tasks.get(i).toString());
        }
        return message;
    }
}
