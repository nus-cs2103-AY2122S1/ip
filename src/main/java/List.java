import java.util.ArrayList;

public class List {
    private ArrayList<String> list = new ArrayList<String>();

    public void addItem(String item) {
        this.list.add(item);
    }

    public ArrayList<String> getList() {
        return this.list;
    }

    public int getLength() {
        return this.list.size();
    }

    public String getItemAtIndex(int i) {
        return list.get(i);
    }
}
