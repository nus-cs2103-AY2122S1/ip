package duke.tasks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public String addTaskAndAnnounce(String taskType, Task t) {
        String res = "";
        tasks.add(t);
        res += "I've added your " + taskType.toLowerCase() + ":\n\t\t" + t;
        res += "\n\t " + tasks.size() + " tasks in total.";
        return res;
    }

    public ArrayList<Task> tasks() {
        return this.tasks;
    }

    public int size() {
        return tasks.size();
    }

    public String markAsDone(ArrayList<Integer> listOfTasks) {
        StringBuilder res = new StringBuilder("Nice! I've marked these tasks as done: \n\t\t");

        for (int i = 0; i < listOfTasks.size(); i++) {
            // have to decrement by one since duke.tasks ArrayList is 0-indexed,
            // but the user-provided arguments uses a 1-indexed list
            int index = listOfTasks.get(i) - 1;

            tasks.get(index).markAsDone();
            res.append(tasks.get(index));
            if (i != listOfTasks.size() - 1) {
                // do not append newline to the last item
                res.append("\n\t\t");
            }
        }
        return res.toString();
    }

    public String deleteTasks(ArrayList<Integer> listOfTasks) {
        StringBuilder res = new StringBuilder("I've removed these tasks: \n\t\t");

        // reverse-sort the tasks and remove duplicates
        Set<Integer> s = new LinkedHashSet<>(listOfTasks);
        listOfTasks.clear();
        listOfTasks.addAll(s);
        Collections.sort(listOfTasks);
        Collections.reverse(listOfTasks);

        for (int i = 0; i < listOfTasks.size(); i++) {
            // have to decrement by one since tasks ArrayList is 0-indexed,
            // but the user-provided arguments uses a 1-indexed list
            int index = listOfTasks.get(i) - 1;

            res.append(tasks.get(index));
            tasks.remove(index);

            if (i != listOfTasks.size() - 1) {
                // do not append newline to the last item
                res.append("\n\t\t");
            }
        }
        res.append("\n\t ").append(tasks.size()).append(" tasks remain.");
        return res.toString();
    }

    @Override
    public String toString() {
        // returns an indented, newlined, 1-indexed, String of the Tasks in this duke.tasks.TaskList
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
