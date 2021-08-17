import java.util.ArrayList;

public class List {
    private ArrayList<String> list;

    public List() {
        list = new ArrayList<>();
    }

    public void add(String text) {
        list.add(text);
    }

    public void show() {
        int length = list.size();
        for(int i = 1; i <= length; i++) {
            System.out.println(i + ". " + list.get(i - 1));
        }
    }
}
