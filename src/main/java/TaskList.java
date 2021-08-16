import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> tasklist = new ArrayList<>();

    /**
     * Add task to the list.
     * @param task the name of the task.
     */
    public void add(Task task) {
        this.tasklist.add(task);
    }

    /**
     * Retrieves a specific task from the list.
     * @param index the task number.
     * @return Task.
     */
    public Task getTask(int index) {
        return tasklist.get(index);
    }

    /**
     * Retrieve the size of the task list.
     * @return the size of the task list.
     */
    public int getLength() {
        return this.tasklist.size();
    }

    /**
     * Remove task from the list.
     * @param index index of task to be removed.
     */
    public void removeTask(int index) {
        this.tasklist.remove(index);
    }

    /**
     * Display all items in the list.
     */
    public void displayAllItems() {
        System.out.println("    * * * * * * * * * * * * * * * * * * * *");
        System.out.println("    Here are the tasks in your list: ");
        for (int i = 0; i < tasklist.size(); i++) {
            String item = "    " + (i + 1) + "." +  this.getTask(i);
            System.out.println(item);
        }
        System.out.println("    * * * * * * * * * * * * * * * * * * * *\n");
    }


}
