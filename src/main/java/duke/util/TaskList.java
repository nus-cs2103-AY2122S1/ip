package duke.util;

import duke.date.Date;
import duke.exception.NoSuchTaskException;
import duke.task.DatedTask;
import duke.task.Task;

import java.util.ArrayList;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public void add(Task task) {
        list.add(task);
    }

    public int size() {
        return list.size();
    }

    public Task getTask(int index) throws NoSuchTaskException {
        try {
            return list.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchTaskException();
        }
    }

    public Task deleteTask(int index) throws NoSuchTaskException {
        try {
            return list.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchTaskException();
        }

    }

    public String[] toStringArray() {
        String[] copy = list.stream().map(Object::toString).toArray(String[]::new);
        for (int i = 0; i < size(); i++) {
            copy[i] = (i + 1) + "." + copy[i];
        }
        return copy;
    }

    public String[] toStringArray(Date date) {
        return list.stream()
                .filter((task) -> task instanceof DatedTask)
                .filter((task) -> ((DatedTask) task).getDate().equals(date))
                .map(Object::toString)
                .toArray(String[]::new);
    }
  
    public void forEach(Consumer<Task> consumer) {
        for (int i = 0; i < size(); i++) {
            consumer.accept(list.get(i));
        }
    }

    /**
     * Returns a TaskList containing Task that satisfy the supplied predicate.
     *
     * @param predicate The predicate to test the Task with.
     * @return A TaskList containing Task that satisfies the given predicate.
     */
    public TaskList filter(Function<Task, Boolean> predicate) {
        ArrayList<Task> res = new ArrayList<>();
        for (int i = 0; i < size(); i++) {
            if (predicate.apply(list.get(i))) {
                res.add(list.get(i));
            }
        }
        return new TaskList(res);
    }
}
