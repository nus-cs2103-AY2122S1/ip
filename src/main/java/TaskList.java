import java.util.*;

public class TaskList {
    public List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public String add(Task item) {
        this.taskList.add(item);
        return "added: " + item;
    }

    public String display() {
        return String.format("Here are the tasks in your list:%s", this);
    }

    public String completeTask(int index) {
        Task t = taskList.get(index - 1);
        t.markAsDone();
        return String.format("Nice! I've marked this task as done:\n      %s", t);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("");
        for (int i = 0; i < taskList.size(); i++) {
            s.append("\n    ");
            s.append(String.format("%d. %s", i + 1, this.taskList.get(i)));
//            if (i == taskList.size() - 1) continue;

        }
        String finalString = s.toString();
        return finalString;
    }
}
