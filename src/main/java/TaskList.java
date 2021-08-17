import java.util.*;

public class TaskList {
    public List<String> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public String add(String item) {
        this.taskList.add(item);
        return "added: " + item;
    }

    @Override
    public String toString() {
        StringBuilder s =new StringBuilder("");
        for (int i = 0; i < taskList.size(); i++) {
            s.append(String.format("%d. %s", i + 1, this.taskList.get(i)));
            if (i == taskList.size() - 1) continue;
            s.append("\n    ");
        }
        String finalString = s.toString();
        return finalString;
    }
}
