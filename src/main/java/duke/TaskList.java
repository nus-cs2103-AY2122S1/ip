package duke;

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

    public Task getTask(int index) throws DukeException {
        Task task;
        try {
            task = list.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Sorry, there are only " + list.size() + " tasks.");
        }
        return task;
    }

    public Task deleteTask(int index) throws DukeException{
        Task task = getTask(index);
        list.remove(index);
        return task;
    }

    public Task doneTask(int index) throws DukeException{
        Task task = getTask(index);
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
