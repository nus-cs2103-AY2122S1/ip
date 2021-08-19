import java.util.ArrayList;

/**
 * List class: Duke HAS-A List
 *
 * @author Timothy Wong Eu-Jin
 * @version Level-3
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
        System.out.println("\t" + "added: "  + task.getDescription());
    }

    //getIndex method to return a specific task
    public Task getIndex(int index) {
        return array.get(index - 1);
    }

    //getAll method to return all entries in list
    public void getAll() {
        int count = 1;
        for (Task t : this.array) {
            System.out.println("\t" + count + ": " + t.toString());
            count += 1;
        }
    }
}
