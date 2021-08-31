package duke;

import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) throws DukeException {
        if (list.isEmpty()) {
            throw new DukeException("Task list is empty");
        }
        this.list = list;
    }

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.list.add(task);
    }

    public void removeTask(int index) {
        this.list.remove(index);
    }

    public void markAsDone(int index) {
        this.list.get(index).markAsDone();
    }

    public Task getTask(int index) {
        return this.list.get(index);
    }

    public int size() {
        return this.list.size();
    }

    public void showTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i).toString());
        }
    }

    public void findTask(String find) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task t :
                this.list) {
            if (t.description.contains(find)) {
                result.add(t);
            }
        }
        if (result.size() == 0) {
            System.out.println("There are no matching task in your list!");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < result.size(); i++) {
                System.out.println((i + 1) + ". " + result.get(i).toString());
            }
        }
    }

}
