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

    /**
     * Delete a task
     * @param taskNo Task Number (starting from index 1)
     */
    public void deleteTask(int taskNo) {
        if (taskNo > tasks.size() || taskNo < 1) {
            System.out.println("Cannot find task! Use list command to see available tasks");
            return;
        }
        Task taskToDelete = tasks.get(taskNo - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + taskToDelete);
        tasks.remove(taskToDelete);
        System.out.println("Now you have " + tasks.size() + " tasks in the list." );
    }

    /**
     * Creates a todo task
     * @param description description of task
     */
    public void createToDo(String description) {
        Task newToDo = new ToDo(description);
        tasks.add(newToDo);
        System.out.println("Got it. I've added this task:\n  " + newToDo + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Creates an event task
     * @param description description of task
     * @param at time period of Event (start-end)
     */
    public void createEvent(String description, String at) {
        Task newEvent = new Event(description, at);
        tasks.add(newEvent);
        System.out.println("Got it. I've added this task:\n  " + newEvent + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.");
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
