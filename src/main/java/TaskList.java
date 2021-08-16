import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private final List<String> list = new ArrayList<>(List.of("Test1", "Test2"));

    public void addTask(String task) {
        list.add(task);
    }

    @Override
    public String toString() {
        int len = list.size();
        if (len == 0) return "";
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < len; i++) {
            result.append(String.format("%s. %s\n", i, list.get(i - 1)));
        }
        result.append(String.format("%s. %s", len, list.get(len - 1)));
        return result.toString();
    }
}
