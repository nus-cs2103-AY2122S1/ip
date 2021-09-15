package duke;

import java.util.ArrayList;

/**
 * ArrayList of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds task to TaskList.
     *
     * @param task The task that is going to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes task in TaskList.
     *
     * @param deleteIndex Index of the task that is going to be deleted.
     * @return The deleted task.
     */
    public Task delete(int deleteIndex) {
        return tasks.remove(deleteIndex);
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The number of tasks in the TaskList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns specific tasks by index.
     *
     * @param index The task index.
     * @return The task.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Finds the tasks by keywords.
     *
     * @param keyWord The key word that is going to be searched on.
     * @return TaskList contains all the tasks that has the key word.
     */
    public TaskList find(String keyWord) {
        TaskList results = new TaskList(new ArrayList<Task>());
        for (int i = 0; i < this.size(); i++) {
            Task t = this.get(i);
            if (t.getDescription().contains(keyWord)) {
                results.add(t);
            }
        }
        return results;
    }

    public ArrayList<Task> getReminderList(){
        ArrayList<Task> result = new ArrayList<>();
        for(Task task : tasks){
            if(task.hasReminder()){
                result.add(task);
            }
        }
        return result;
    }
}
