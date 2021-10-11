package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.list.add(task);
    }

    public void removeTask(int i) {
        this.list.remove(i);
        assert list.size() >= 0 : "List size is negative!";
    }

    public TaskList findTask(String input) {
        TaskList searchResult = new TaskList();
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            if (task.toString().contains(input)) {
                searchResult.addTask(task);
            }
        }
        return searchResult;
    }

    public int size() {
        return list.size();
    }

    public Task get(int i) {
        return list.get(i);
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public List<Integer> taskDistribution() {
        List<Integer> counter = new ArrayList<>();
        counter.add(0);
        counter.add(0);
        counter.add(0);
        for (Task task: this.list) {
            if (task instanceof ToDo) {
                counter.set(0, counter.get(0) + 1);
            } else if (task instanceof Deadline) {
                counter.set(1, counter.get(1) + 1);
            } else if (task instanceof Event) {
                counter.set(2, counter.get(2) + 1);
            }
        }
        return counter;
    }

    public String taskCompetedPercentage() {
        double totalTasks = list.size();
        double completedTasks = 0;
        double res;
        for (Task task: this.list) {
            if (task.isDone) {
                completedTasks++;
            }
        }
        res = Math.round(completedTasks / totalTasks * 100);
        System.out.println(completedTasks / totalTasks);
        return Double.toString(res);
    }

}
