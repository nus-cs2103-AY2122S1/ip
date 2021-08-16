import java.util.ArrayList;

public class TaskList {
    private final ArrayList<String> itemList;

    public TaskList() {
        itemList = new ArrayList<>();
    }

    public int size() {
        return itemList.size();
    }

    public void add(String input) {
        itemList.add(input);
    }

    public void displayList() {
        for (int i = 0; i < itemList.size(); i++){
            System.out.println(Display.OUTPUT_SPACES + (i+1) + "." + itemList.get(i));
        }
    }
}
