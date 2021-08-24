package duke;

import java.util.List;

public class TaskList {

    private List<Task> tasks;
    private Storage storage;

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
        curr.setDone();
        storage.writeToStorage(this.getList());

        return curr.toString();
    }

    public int taskCount() {
        return tasks.size();
    }

    public String getList() {
        int counter = 1;
        String result = "";

        for (int i = 0; i < tasks.size(); i++) {
            if (i < tasks.size() - 1) {
                result = result + counter + "." + tasks.get(i) + "\n";
            } else {
                result = result + counter + "." + tasks.get(i);
            }
            counter++;
        }

        return result;
    }
}
