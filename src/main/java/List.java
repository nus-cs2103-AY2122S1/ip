import java.util.ArrayList;

public class List {
    private ArrayList<Task> list;

    public List() {
        list = new ArrayList<>();
    }

    public void add(String text) {
        list.add(new Task(text));
    }

    public void setIndexDone(int index) {// starts from 1
        list.get(index - 1).setDone();
        System.out.println(index + ".[" + list.get(index - 1).getStatusIcon() + "] " + list.get(index - 1).getDescription());
    }

    public void show() {
        int length = list.size();
        for(int i = 1; i <= length; i++) {
            System.out.println(i + ".[" + list.get(i - 1).getStatusIcon() + "] " + list.get(i - 1).getDescription());
        }
    }
}
