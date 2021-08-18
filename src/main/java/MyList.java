import java.util.ArrayList;
import java.util.List;

public class MyList {

    private final List<String> items;

    MyList() {
        this.items = new ArrayList<String>(100);
    }

    protected String addItem(String item) {
        this.items.add(item);
        return "added: " + item;
    }

    protected String getAllItems() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.items.size(); i++) {
            String itemNum = Integer.toString(i + 1) + ". ";
            sb.append(itemNum);
            sb.append(this.items.get(i));
            sb.append("\n");
        }
        return sb.toString();
    }
}
