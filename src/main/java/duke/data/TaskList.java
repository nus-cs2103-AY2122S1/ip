package duke.data;

import duke.commands.Task;
import duke.exceptions.DukeException;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> lst;

    public TaskList() {
        lst = new ArrayList<Task>();
    }

    public void iterList() {
        System.out.println("Here are the tasks in your list:");
        if (lst.size() == 0) {
            System.out.println("---- No Tasks currently ----");
        }
        int i = 1;
        for (Task s : lst) {
            System.out.println(i + "." + s);
            i++;
        }
    }

    public void add(Task text) {
        lst.add(text);
    }

    public void complete(int index) throws DukeException {
        if (index < 0 || index > lst.size() - 1) {
            throw new DukeException("Hey! Invalid Task number given.");
        }
        Task task = lst.get(index);
        task.completeTask();
    }

    public void delete(int index) throws DukeException {
        if (index < 0 || index > lst.size() - 1) {
            throw new DukeException("Hey! Invalid Task number given.");
        }
        lst.remove(index);
    }

    public ArrayList<Task> getList() {
        return lst;
    }

    public int count() {
        return lst.size();
    }

    public Task get(int index) throws DukeException {
        if (index < 0 || index > lst.size() - 1) {
            throw new DukeException("Hey! Invalid Task number given.");
        }
        Task task = lst.get(index);
        return task;
    }

    public void searchKeyword(String keyword) {
        System.out.println("Here are the matching tasks in your list:");
        int i = 1;
        for (Task task : lst) {
            if (task.findKeyword(keyword)) {
                System.out.println(i + "." + task);
                i++;
            }
        }

        if (i == 1) {
            System.out.println("---- No related tasks found ----");
        }
    }
}
