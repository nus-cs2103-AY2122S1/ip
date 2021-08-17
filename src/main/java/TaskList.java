import java.util.ArrayList;

class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void add(Task task) {
        this.list.add(task);
    }

    public Task get(int index) {
        return this.list.get(index);
    }
    public int size() {
        return this.list.size();
    }
    public void display() {
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println(String.format("%s. %s", i+1, this.list.get(i).toString()));
        }
    }

    public void delete(int index) {
        this.list.remove(index - 1);
    }
}

