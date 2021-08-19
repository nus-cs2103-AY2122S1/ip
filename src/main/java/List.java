import java.util.ArrayList;
/**
 * List class: Duke HAS-A List, List HAS-A Task
 *
 * @author Timothy Wong Eu-Jin
 * @version Level-4
 */

public class List {

    //Class fields
    private ArrayList<Task> array;

    //Constructor
    public List() {
        this.array = new ArrayList<Task>();
    }

    //Add method to insert new entry into list
    public void add(Task task) {
        this.array.add(task);
        Duke.divider();
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        this.getNumOfTasks();
        Duke.divider();
    }

    //getIndex method to return a specific task
    public Task getIndex(int index) {
        return this.array.get(index - 1);
    }

    //getNumOfTasks method prints the number of tasks in the list
    public void getNumOfTasks() {
        int totalNum = this.array.size();
        if (totalNum == 1) {
            System.out.println("Now you have " + totalNum + " task in the list");
        } else {
            System.out.println("Now you have " + totalNum + " tasks in the list");
        }
    }

    //getAll method to return all entries in list
    public void getAll() {
        int count = 1;
        for (Task t : this.array) {
            System.out.println(count + ". " + t.toString());
            count += 1;
        }
    }
}
