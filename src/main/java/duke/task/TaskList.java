package duke.task;

import duke.Storage;

import java.io.IOException;
import java.util.ArrayList;


public class TaskList extends ArrayList<Task> {

    public TaskList(ArrayList<Task> tasks) {
        this.addAll(tasks);
    }

    public TaskList() {
        super();
    }

    // all modifying operations of duke.task list will modify the db as instructed
    public boolean add(Task task, Storage storage) {
        boolean result = super.add(task);
        try {
            storage.save(task);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Task remove(int index, Storage storage) {
        // TODO: param to allow client to decide whether to refresh db
        // TODO: find and remove from file O(N)
        Task task = super.remove(index);
        try {
            storage.save(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return task;
    }

    public void markAsDone(Task task, Storage storage) {
        task.markAsDone();
        try {
            storage.save(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
