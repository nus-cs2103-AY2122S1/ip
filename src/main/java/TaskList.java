import java.util.ArrayList;

public class TaskList {
    private ArrayList<TaskItem> arrayList;

    public TaskList() {
        this.arrayList = new ArrayList<>();
    }

    public TaskList(ArrayList<TaskItem> arrayList) {
        this.arrayList = arrayList;
    }

    public void add(TaskItem task) {
        arrayList.add(task);
    }

    public int size() {
        return arrayList.size();
    }

    public TaskItem get(int index) {
        return arrayList.get(index);
    }

    public void remove(int index) {
        this.arrayList.remove(index);

    }


}
