import java.util.ArrayList;
import java.util.Collections;

public class TaskList {
    private ArrayList<Task> tasks;

    public void add(Task t) {
        tasks.add(t);
    }

    public String markAsDone(ArrayList<Integer> args) {
        StringBuilder res = new StringBuilder("Nice! I've marked these tasks as done: \n\t    ");

        for (int i = 0; i < args.size(); i++) {
            // have to decrement by one since tasks ArrayList is 0-indexed,
            // but the user-provided arguments uses a 1-indexed list
            int index = args.get(i) - 1;

            tasks.get(index).markAsDone();
            res.append(tasks.get(index));
            if (i != args.size() - 1) {
                // do not append newline to the last item
                res.append("\n\t     ");
            }
        }

        return res.toString();
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    @Override
    public String toString() {
        // returns an indented, newlined, 1-indexed, String of the Tasks in this TaskList
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            res.append(i+1).append(". ").append(tasks.get(i));
            if (i != tasks.size() - 1) {
                // do not append a newline to the last item
                res.append("\n\t ");
            }
        }
        return res.toString();
    }
}
