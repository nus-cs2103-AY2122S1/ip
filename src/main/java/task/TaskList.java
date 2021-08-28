package task;

import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> tasks;

    public enum TaskType {
        TODO, DEADLINE, EVENT
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void remove(int index) {
        tasks.remove(index);
    }

    public Task get(int index) throws IndexOutOfBoundsException{
        return tasks.get(index);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public int length() {
        return tasks.size();
    }

    public static void addTaskByType(TaskList tasklist, TaskType type, boolean isDone, String description, String time) {
        switch (type) {
            case TODO:
                tasklist.add(new Todo(description, isDone));
                break;
            case EVENT:
                tasklist.add(new Event(description, isDone, time));
                break;
            case DEADLINE:
                tasklist.add(new Deadline(description, isDone, time));
                break;
            default:
                throw new IllegalArgumentException("addTask Unsuccessful");
        }
    }

    @Override
    public String toString() {
        StringBuilder tasksDialog = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            tasksDialog.append("    ").append(i + 1).append(".").append(tasks.get(i).toString()).append("\n");
        }
        return tasksDialog.toString();
    }
}
