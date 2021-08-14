import java.util.List;
import java.util.ArrayList;

public class Database {
    private List<String> db = new ArrayList<>();

    public void add(String task) {
        db.add(task);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= db.size(); i++) {
            sb.append("\n ");
            sb.append(i + ". " + db.get(i-1));
        }
        sb.deleteCharAt(0);
        return sb.toString();
    }
}
