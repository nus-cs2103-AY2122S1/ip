import java.util.List;
import java.util.ArrayList;

public class Database {
    private List<Task> db = new ArrayList<>();

    public void add(Task task) {
        db.add(task);
    }

    public Task get(int index) {
        return db.get(index);
    }

    public int size() {
        return db.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (db.size() == 0) return " You have no tasks!";
        for (int i = 1; i <= db.size(); i++) {
            sb.append("\n ");
            sb.append(i + "." + db.get(i-1));
        }
        sb.deleteCharAt(0);
        return sb.toString();
    }
}
