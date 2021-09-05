package duke;

import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) throws DukeException {
        if (list.isEmpty()) {
            throw new DukeException("Task list is empty!");
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

    public String findTask(String find) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task t :
                this.list) {
            if (t.toString().toLowerCase().contains(find.toLowerCase())) {
                result.add(t);
            }
        }
        if (result.size() == 0) {
            return "There are no matching task in your list!\n";
        } else {
            String listContent = "Here are the matching tasks in your list:\n";
            for (int i = 0; i < result.size(); i++) {
                listContent += (i + 1) + ". " + result.get(i).toString() + "\n";
            }
            return listContent;
        }
    }

    @Override
    public String toString() {
        if(this.list.isEmpty()) {
            return "There are no tasks in your list!\n";
        } else {
            String listContent = "Here are the tasks in your list:\n";
            for (int i = 0 ; i < list.size() ; i++) {
                listContent += (i + 1) + ". " + list.get(i).toString() + "\n";
            }
            return listContent;
        }
    }


}
