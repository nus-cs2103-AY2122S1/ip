package duke.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    private final List<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public TaskList(List<Task> lines) {
        list = new ArrayList<>(lines);
    }

    public void add(Task t) {
        list.add(t);
    }

    public Task delete(int taskNo) {
        return list.remove(--taskNo);
    }

    public int size() {
        return list.size();
    }

    public Task get(int taskNo) {
        return list.get(--taskNo);
    }

    public boolean markAsDone(int taskNo) {
        return list.get(--taskNo).markAsDone();
    }

    public List<String> formatData() {
        return list.stream().map(Task::formatForSave).collect(Collectors.toList());
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
