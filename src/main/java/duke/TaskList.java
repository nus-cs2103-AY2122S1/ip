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

    /**
     *Deletes task from {@code list} based on index given
     * @param index
     */
    public void removeTask(int index) {
        this.list.remove(index);
        assert list.size() >= 0 : "List size is negative!";
    }

    /**
     * Searches for task in the {@code list} based on input String
     * @param input
     * @return {@code TaskList} containing task found
     */
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

    /**
     * Finds total number of tasks stored
     * @return Size of {@code TaskList}
     */
    public int size() {
        return list.size();
    }

    /**
     * Retrieves a specific task based on index provided
     * @param index
     * @return Task at specific index
     */
    public Task get(int index) {
        return list.get(index);
    }

    /**
     * Retrieves entire list of tasks
     * @return all tasks
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Called to provide statistics for number of tasks of each type left to do
     * @return a list of integer
     */
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

    /**
     * Calculates number of task done divided by total tasks stored in the list
     * @return String percentage
     */
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
