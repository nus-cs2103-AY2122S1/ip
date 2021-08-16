import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> tasklist = new ArrayList<>();

    /**
     * Add task to the list.
     * @param item the name of the task.
     */
    public void add(String item) {
        this.tasklist.add(new Task(item));
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
     * Display all items in the list.
     */
    public void displayAllItems() {
        System.out.println("    * * * * * * * * * * * * * * * * * * * *");
        System.out.println("    Here are the tasks in your list: ");
        for (int i = 0; i < tasklist.size(); i++) {
            String item = "    " + (i + 1) + "." +  this.getTask(i).getStatusMessage();
            System.out.println(item);
        }
        System.out.println("    * * * * * * * * * * * * * * * * * * * *\n");
    }


}
