import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<String> tasklist = new ArrayList<>();

    /**
     * Add task to the list.
     * @param item the name of the task.
     */
    public void add(String item) {
        this.tasklist.add(item);
    }

    /**
     * Display all items in the list.
     */
    public void displayAllItems() {
        System.out.println("    * * * * * * * * * * * * * * * * * * * *");
        for (int i = 0; i < tasklist.size(); i++) {
            String item = "    " + (i + 1) + ". " + tasklist.get(i);
            System.out.println(item);
        }
        System.out.println("    * * * * * * * * * * * * * * * * * * * *\n");
    }


}
