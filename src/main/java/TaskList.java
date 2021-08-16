import java.util.ArrayList;

public class TaskList {
    private ArrayList<ListItem> listItems;

    /**
     * Empty TaskList constructor.
     */
    public TaskList() {
        this.listItems = new ArrayList<>();
    }

    /**
     * TaskList constructor with tasks.
     */
    public TaskList(ArrayList<ListItem> listItems) {
        this.listItems = listItems;
    }

    public TaskList add(ListItem item) {
        ArrayList<ListItem> newList = new ArrayList<>(listItems);
        newList.add(item);
        return new TaskList(newList);
    }

    @Override
    public String toString() {
        String str = "";
        int i = 1;
        for (ListItem item : listItems) {
            str += (i + ". " + item + '\n');
            i++;
        }
        return str;
    }
}
