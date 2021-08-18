import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
       this.list = new ArrayList<>();
    }

    public void addToList(String input) {
        list.add(new Task(input));
    }

    public Task getItem(int index) {
        if (this.list.size() < index || index < 0) {
            return null;
        }
        return this.list.get(index);
    }

    @Override
    public String toString() {
        String l = "Here are the tasks on your list:\n";
        for (Task s : list) {
            l += (list.indexOf(s) + 1) + ". " + s + "\n";
        }
        return l;
    }
}
