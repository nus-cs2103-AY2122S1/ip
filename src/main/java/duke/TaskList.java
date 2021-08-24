package duke;

import java.util.List;

public class TaskList {

    private List<Task> tasks;
    private Storage storage;
    private boolean taskFound = false;

    public TaskList(Storage storage) {
        this.storage = storage;
        tasks = storage.readFromStorage();
    }

    public String addToList(Task newTask) {
        tasks.add(newTask);
        storage.writeToStorage(this.getList());

        return newTask.toString();
    }

    public String deleteFromList(int index) {
        Task curr = tasks.get(index - 1);
        tasks.remove(index - 1);
        storage.writeToStorage(this.getList());

        return curr.toString();
    }

    public String taskDone(int index) {
        Task curr = tasks.get(index - 1);
        curr.markAsDone();
        storage.writeToStorage(this.getList());

        return curr.toString();
    }

    public String findTask(String searchString) {
        int counter = 1;
        String result = "";
        taskFound = false;

        for (int i = 0; i < tasks.size(); i++) {
            Task curr = tasks.get(i);
            if (curr.getDescription().contains(searchString)) {
                result = result + counter + "." + curr + "\n";
                counter++;
                taskFound = true;
            }
        }

        return result.trim();
    }

    public boolean isTaskFound() {
        return taskFound;
    }

    public int taskCount() {
        return tasks.size();
    }

    public String getList() {
        int counter = 1;
        String result = "";

        for (int i = 0; i < tasks.size(); i++) {
            result = result + counter + "." + tasks.get(i) + "\n";
            counter++;
        }

        return result.trim();
    }
}
