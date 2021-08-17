import java.util.ArrayList;
import java.util.List;

class TaskList {
    private final List<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void add(Task t) {
        list.add(t);
    }

    public Task get(int taskNo) {
        return list.get(--taskNo);
    }

    public boolean markAsDone(int taskNo) {
        return list.get(--taskNo).markAsDone();
    }

    @Override
    public String toString() {
        if (list.isEmpty()) {
            return "You have no tasks!";
        } else {
            StringBuilder sb = new StringBuilder("Current tasks:\n");
            for (int i = 0; i < list.size(); i++) {
                sb.append(String.format("%d.%s\n", i + 1, list.get(i).toString()));
            }
            sb.append("Total: ").append(list.size()).append(" tasks");
            return sb.toString();
        }
    }
}
