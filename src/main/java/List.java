import java.util.ArrayList;

public class List {
    private ArrayList<Task> items;

    public List() {
        items = new ArrayList<>();
    }

    public void addItem(String item) {
        items.add(new Task(item));
    }

    public String returnItems() {
        String itemString = "     Here are the tasks in your list:\n";
        for (int i = 0; i < items.size(); i++) {
            Task curr = items.get(i);
            itemString += "     " + (i + 1) + ".[" + curr.getStatus() + "] " + curr.getDescription() + "\n";
        }
        return itemString;
    }

    public String markDone(int index) {
        Task t = items.get(index - 1);
        t.markDone();
        return "       [X] " + t.getDescription() + "\n";
    }
}
