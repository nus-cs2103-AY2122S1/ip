import java.util.ArrayList;

class TaskList {

    private ArrayList<Task> tasks;

    TaskList() {
        this(new ArrayList<Task>());
    }

    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    void delete(int index) {
        tasks.remove(index);
    }

    void add(Task t) {
        tasks.add(t);
    }

    Task get(int index) {
        return tasks.get(index);
    }

    int size() {
        return tasks.size();
    }
}