import java.util.ArrayList;

public class List {
    private ArrayList<String> items;

    public List() {
        items = new ArrayList<>();
    }

    public void addItem(String item) {
        items.add(item);
    }

    public String returnItems() {
        String itemString = "";
        for (int i = 0; i < items.size(); i++) {
            itemString += "     " + (i + 1) + ". " + items.get(i) + "\n";
        }
        return itemString;
    }
}
