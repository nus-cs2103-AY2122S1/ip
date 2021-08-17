import java.util.ArrayList;

/**
 * This class represents a TaskList, which is the list of tasks that Duke refers to to carry out commands provided
 * to him.
 */

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    /**
     * Adds an item to the TaskList
     *
     * @param input the item to be added to the TaskList
     */
    public void add(String input) {
        Task task = new Task(input);
        this.list.add(task);
        System.out.println("    " + "added: " + input);
    }

    /**
     * Finishes a task at a given index
     *
     * @param index index from 1 (i.e lowest index is 1, so subtract 1 to get real index)
     */
    public void finishTask(int index) {
        Task task = this.list.get(index - 1);
        task.doneTask();
    }

    /**
     * Lists out the current items in the TaskList
     */
    public void listOut() {
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println("     " + (i + 1) + "." + this.list.get(i));
        }
    }
}
