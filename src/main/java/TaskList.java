import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public void addTask(Task task) {
        list.add(task);
    }

    public Task deleteTask(int index) throws DukeException{
        Task t;
        try {
            t = list.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Sorry, there are only " + list.size() + " tasks.");
        }
        list.remove(index);
        return t;
    }

    public Task doneTask(int index) throws DukeException{
        Task task;
        try {
            task = list.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Sorry, there are only " + list.size() + " tasks.");
        }
        task.setStatus(true);
        return task;
    }

    public String allTasks() {
        int i = 1;
        String result = "";
        for (Task item : list) {
            result += i + ". " + item.getTaskType() + item.getStatusIcon() + " " + item.getDescription() + "\n";
            i++;
        }
        return result;
    }

    public int size() {
        return list.size();
    }

    @Override
    public String toString() {
        String data = "";
        for (Task task : list) {
            data += task.toString() + "\n";
        }
        return data;
    }
}
