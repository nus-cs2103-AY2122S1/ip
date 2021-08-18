import java.util.ArrayList;
import java.util.List;

class MyList {

    private final List<Task> items;

    MyList() {
        this.items = new ArrayList<Task>(100);
    }

    protected String addItem(String item) {
        this.items.add(new Task(item));
        return "new entry in list: \n" + item;
    }

    protected String getAllItems() {
        StringBuilder sb = new StringBuilder();
        if (this.items.size() == 0) {
            sb.append("There are no items on your list!");
            return sb.toString();
        }
        sb.append("Your list contains: \n");
        for (int i = 0; i < this.items.size(); i++) {
            String itemNum = Integer.toString(i + 1) + ". ";
            sb.append(itemNum);
            sb.append(this.items.get(i).toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
