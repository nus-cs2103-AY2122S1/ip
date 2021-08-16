import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public String printSize() {
        return ("There are " + tasks.size() + " tasks in your list");
    }

    public void add(String input) {
        Task result = new Task(input);
        tasks.add(result);
    }

    public void displayList() {
        if (tasks.size() == 0) {
            System.out.println(Display.OUTPUT_DISPLAY + "There is nothing to display! :angery:");
        } else {
            System.out.println(Display.OUTPUT_DISPLAY + "Displaying List:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(Display.OUTPUT_SPACES + (i + 1) + "." + tasks.get(i));
            }
        }
    }
}
