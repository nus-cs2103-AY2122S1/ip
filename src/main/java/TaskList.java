import java.util.ArrayList;
import java.util.Collections;

public class TaskList {
    private ArrayList<Task> tasks;

    public String add(String description, Task.TaskTypes type) {
        String res = "";
        Task t;
        switch(type) {
            case TODO:
                t = new TodoTask(description);
                tasks.add(t);
                res += "I've added your todo: \n\t\t" + t;
                break;
            default:
                return "Invalid task type.";
        }
        res += "\n\t " + tasks.size() + " tasks in total.";
        return res;
    }
    public String add(String description, Task.TaskTypes type, String time) {
        String res = "";
        Task t;
        switch(type) {
            case EVENT:
                t = new EventTask(description, time);
                tasks.add(t);
                res += "I've added your event: \n\t\t" + t;
                break;
            case DEADLINE:
                t = new DeadlineTask(description, time);
                tasks.add(t);;
                res += "I've added your deadline: \n\t\t" + t;
                break;
            default:
                return "Invalid task type";
        }
        res += "\n\t " + tasks.size() + " tasks in total.";
        return res;
    }

    public String markAsDone(ArrayList<Integer> args) {
        StringBuilder res = new StringBuilder("Nice! I've marked these tasks as done: \n\t\t");

        for (int i = 0; i < args.size(); i++) {
            // have to decrement by one since tasks ArrayList is 0-indexed,
            // but the user-provided arguments uses a 1-indexed list
            int index = args.get(i) - 1;

            tasks.get(index).markAsDone();
            res.append(tasks.get(index));
            if (i != args.size() - 1) {
                // do not append newline to the last item
                res.append("\n\t\t");
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
        StringBuilder res = new StringBuilder("Here are your tasks:\n\t ");
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
