package duke.task;

import duke.storage.Storage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private static final String EMPTY_LIST_MESSAGE = "Hooray! You have no tasks currently.";
    private static final String CANNOT_FIND_TASK_MESSAGE = "Cannot find task with number %d.";
    private final Storage<Task> storage;
    private final List<Task> list;

    public TaskList(Storage<Task> storage) throws IOException {
        this.storage = storage;
        this.list = this.storage.load();
    }

    public void add(Task newTask) {
        assert newTask != null;
        this.list.add(newTask);
        this.save();
    }

    public Task get(int taskIndex) throws InvalidTaskException {
        try {
            return this.list.get(taskIndex - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskException(String.format(CANNOT_FIND_TASK_MESSAGE, taskIndex));
        }
    }

    public Task remove(int taskIndex) throws InvalidTaskException {
        Task task = this.get(taskIndex);
        this.list.remove(task);
        this.save();
        return task;
    }

    public void markAsDone(int taskIndex) throws InvalidTaskException {
        this.get(taskIndex).markAsDone();
        this.save();
    }

    public List<Task> findByKeyword(String keyword) {
       List<Task> results = new ArrayList<>();
       for (Task task : this.list) {
           if (task.getDescription().contains(keyword)) {
               results.add(task);
           }
       }
       return results;
    }

    public int size() {
        return this.list.size();
    }

    private void save() {
        try {
            this.storage.save(this.list);
        } catch (IOException e) {
            throw new IllegalStateException("Error saving tasks to file", e);
        }
    }

    @Override
    public String toString() {
        if (this.size() == 0) {
            return EMPTY_LIST_MESSAGE;
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < this.list.size(); i++) {
            builder.append(String.format("%d. %s\n", i + 1, this.list.get(i).toString()));
        }
        return builder.toString();
    }
}
