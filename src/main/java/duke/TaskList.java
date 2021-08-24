package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> list = new ArrayList<>();
    public void addToList(Task t) {
        list.add(t);
    }
    public String numOfTasks() {
        return "Now you have " + list.size() + " task" + (list.size() != 1 ? "s" : "") + " in the list";
    }
    public String printList() {
        StringBuilder res = new StringBuilder();
        for (int counter = 1; counter<=list.size(); counter++) {
            res.append(counter).append(". ").append(list.get(counter - 1));
            if (counter != list.size()) {
                res.append("\n     ");
            }
        }
        return res.toString();
    }

    public String[] taskAddedMessage(Task t) {
        return new String[]{
                "Got it, I've added this task:",
                t.toString(),
                numOfTasks()
        };
    }

    public String[] taskCompletedMessage(Task t) {
        return new String[]{
                "Nice! I've marked this task as done:",
                t.toString(),
                numOfTasks()
        };
    }

    public String[] taskDeletedMessage(Task t) {
        return new String[]{
                "Noted. I've removed this task:",
                t.toString(),
                numOfTasks()
        };
    }

    public int size() {
        return list.size();
    }

    public Task getTask(int index) {
        return list.get(index);
    }

    public void removeTask(int index) {
        list.remove(index);
    }

}
