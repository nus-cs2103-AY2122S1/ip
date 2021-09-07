package bobcat.model;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import bobcat.exception.InvalidOpsException;
import bobcat.model.task.Task;

public class TaskList {
    private final ArrayList<Task> storage;
    private int index;

    private static class OrderedTask implements Comparable<OrderedTask> {
        private final Task t;
        private final int ordering;

        OrderedTask(Task t, int o) {
            this.t = t;
            this.ordering = o;
        }

        public Task getTask() {
            return t;
        }

        @Override
        public int compareTo(OrderedTask orderedTask) {
            return this.ordering - orderedTask.ordering;
        }
    }

    /**
     * Constructor for a task list
     */
    public TaskList() {
        storage = new ArrayList<>();
        index = 0;
    }

    /**
     * Adds a <code>Task</code> to the end of a <code>TaskList</code>
     * @param t task to be added
     * @return task to be added
     */
    public Task push(Task t) {
        assert !Objects.equals(t, null);
        storage.add(t);
        index += 1;
        return storage.get(index - 1);
    }

    /**
     * Marks a given task as completed
     * @param id ID of the task to be marked
     * @return the Task to be marked
     */
    public Task markDone(int id) {
        if (id >= index || id < 0) {
            throw new InvalidOpsException("Provided Index is out of bounds! Given index is " + (
                    id + 1) + " but there are " + index + " elements in the list");
        }
        storage.get(id).markDone();
        return storage.get(id);
    }

    public Task[] getAllTasks() {
        return storage.toArray(Task[]::new);
    }

    /**
     * Deletes a task by its given id
     * @param id ID of the task to be deleted
     * @return Task to be deleted
     */
    public Task deleteTaskByIdx(int id) {
        if (id >= index || id < 0) {
            throw new InvalidOpsException("Provided Index is out of bounds!");
        }
        index -= 1;
        return storage.remove(id);
    }

    /**
     * Returns an array of <code>Task</code> whose descriptions contain the given <i>name</i>, or whose descriptions
     * are similar to the given <i>name</i>. The similarity approach will be executed only if no description contains
     * the given <i>name</i>.
     * @param name String to be searched in the description of <code>Task</code> in <code>TaskList</code>
     */
    public Task[] findByName(String name) {
        assert !Objects.equals(name, null);
        CompletableFuture<Task[]> naive = CompletableFuture.supplyAsync(() -> findByNameNaive(name));
        CompletableFuture<Task[]> advanced = CompletableFuture.supplyAsync(() -> findByNameAdvanced(name));

        return naive.thenApply(taskArr -> taskArr.length == 0 ? advanced : naive).join().join();
    }

    public int numTasks() {
        return index;
    }

    private Task[] findByNameNaive(String name) {
        return storage.stream().parallel().filter(x -> x.getDescription().toLowerCase().contains(name.toLowerCase()))
                .toArray(Task[]::new);
    }

    private int getOrdering(String source, String reference) { // Measures how close source is to reference
        int relevantLength = Math.min(source.length(), reference.length());
        return naiveLevenshtein(reference, source.substring(0, relevantLength), 0, 0);
    }

    private Task[] findByNameAdvanced(String name) {
        return storage.stream()
                .parallel()
                .map(x -> new OrderedTask(x,
                        getOrdering(x.getDescription().toLowerCase(Locale.ROOT),
                                name.toLowerCase(Locale.ROOT))))
                .sorted()
                .limit(5)
                .map(OrderedTask::getTask)
                .toArray(Task[]::new);
    }

    // Can be optimised by DP
    private int naiveLevenshtein(String s1, String reference, int idx1, int idx2) {
        if (s1.length() <= idx1) {
            return reference.length();
        } else if (reference.length() <= idx2) {
            return s1.length();
        } else if (s1.charAt(idx1) == reference.charAt(idx2)) {
            return naiveLevenshtein(s1, reference, idx1 + 1, idx2 + 1);
        } else {
            return 1 + Math.min(naiveLevenshtein(s1, reference, idx1 + 1, idx2 + 1),
                    Math.min(
                        naiveLevenshtein(s1, reference, idx1 + 1, idx2),
                        naiveLevenshtein(s1, reference, idx1, idx2 + 1)
                    ));
        }
    }

}
