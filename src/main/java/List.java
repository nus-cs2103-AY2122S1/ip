import java.util.ArrayList;

public class List {
    private ArrayList<Task> items;

    public List() {
        items = new ArrayList<>();
    }

    public void addItem(Task task) {
        items.add(task);
    }

    public String[] returnItems() {
        String[] itemList = new String[items.size() + 1];
        itemList[0] = "Here are the tasks in your list:";
        for (int i = 0; i < items.size(); i++) {
            itemList[i + 1] = (i + 1) + "." + items.get(i);
        }
        return itemList;
    }

    public String returnItemCount() {
        return "Now you have " + items.size() + " tasks in the list.";
    }

    public String markDone(int index) throws DukeException{
        if (index > items.size() || index < 1) {
            throw new DukeException("index");
        }
        Task t = items.get(index - 1);
        t.markDone();
        return t.toString();
    }

    public String removeTask(int index) throws DukeException{
        if (index > items.size() || index < 1) {
            throw new DukeException("index");
        }
        return items.remove(index - 1).toString();
    }
}
