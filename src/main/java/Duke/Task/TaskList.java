package Duke.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> list = new ArrayList<>();

    public void add(Task newTask) {
        this.list.add(newTask);
    }

    public Task get(int taskIndex) {
        return this.list.get(taskIndex);
    }

    public int size() {
       return this.list.size();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < this.list.size(); i++) {
           builder.append(String.format("%d. %s\n", i + 1, this.list.get(i).toString()));
        }
        return builder.toString();
    }
}
