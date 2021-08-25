import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks = new ArrayList<>();

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void remove(Task task) {
        tasks.remove(task);
    }

    public Task getTask(int i) {
        return tasks.get(i);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }

    /**
     * Mark a task as done
     * @param taskNo Task Number (starting from index 1)
     */
    public void markAsDone(int taskNo) {
        Task taskCompleted = getTask(taskNo - 1);
        taskCompleted.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + taskCompleted);
    }
    public void printList() {
        int i = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task task: tasks) {
            System.out.print(i + ". ");
            System.out.println(task);
            i++;
        }
    }
}
