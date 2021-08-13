import java.util.ArrayList;

public class List {
    private ArrayList<Task> items;

    public List() {
        items = new ArrayList<>();
    }

    public void addItem(Task task) {
        items.add(task);
    }

    public String returnItems() {
        StringBuilder str = new StringBuilder();
        str.append("     Here are the tasks in your list:\n");
        for (int i = 0; i < items.size(); i++) {
            str.append("     ").append(i + 1).append(".").append(items.get(i)).append("\n");
        }
        return str.toString();
    }

    public String returnItemCount() {
        return "     Now you have " + items.size() + " tasks in the list.";
    }

    public String markDone(int index) {
        Task t = items.get(index - 1);
        t.markDone();
        return t.toString();
    }

    public String removeTask(int index) {
        return items.remove(index - 1).toString();
    }

    public int getSize() {
        return items.size();
    }
}
