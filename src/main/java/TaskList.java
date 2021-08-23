import java.util.ArrayList;

public class TaskList extends ArrayList<Task>{

    public TaskList(ArrayList<Task> taskList) throws DukeException {
        if (taskList.size() == 0) {
            throw DukeException.emptyList();
        }

        for (Task task : taskList) {
            this.add(task);
        }
    }

    public TaskList() {

    }
}
