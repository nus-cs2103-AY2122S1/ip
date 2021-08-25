import java.util.ArrayList;

// immutable principles?
public final class TaskList {
    private final ArrayList<Task> tasks;

    //only objects of class Task go into array
//    @SafeVarargs
    public static TaskList of(ArrayList<Task> tasks) {
        return new TaskList(tasks);
    }
    private TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task get(int idx) {
        return this.tasks.get(idx);
    }

    public int length() {
        return tasks.size();
    }

    public void toggleDone(int idx) {
        if (idx < 0 || idx >= this.tasks.size()) {
            throw new IllegalArgumentException("task index passed in out of range");
        }
        this.tasks.get(idx).markCompleted();
    }

    public void removeTask(int idx) {
        if (idx < 0 || idx >= this.tasks.size()) {
            throw new IllegalArgumentException("task index passed in out of range");
        }
        this.tasks.remove(idx);
    }

    @Override
    public String toString() {
        String result = "";
        int numOfTasks = this.tasks.size();
        for (int i = 0; i < numOfTasks; i++) {
            result += String.format("%d: %s\n", i+1, this.tasks.get(i).toString());
        };
//        //add final task without \n
//        result += String.format("%d: %s", numOfTasks, this.tasks[numOfTasks - 1].toString());
        return result;
    }


}
