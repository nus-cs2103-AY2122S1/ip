package duke.task;

import duke.util.Storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.IntStream;

public final class TaskList {
    private final ArrayList<Task> TASKS;
    private final Storage STORAGE_FILE;
    public static final String DONE_COMMAND_REGEX = "done [0-9]{1,2}";
    public static final String DELETE_COMMAND_REGEX = "delete [0-9]{1,2}";
    public static final String FIND_COMMAND_REGEX = "find \\w+";

    //parse in Files containing Strings (line) of duke.task representation
    public static TaskList of(Storage storageFile) {
        File tasksStorageFile = storageFile.getFile();

        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner sc = new Scanner(tasksStorageFile);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                Task task = Task.parseLine(line);
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new TaskList(tasks, storageFile);
    }

    private TaskList(ArrayList<Task> tasks, Storage storageFile) {
        this.STORAGE_FILE = storageFile;
        this.TASKS = tasks;
    }

    public void addTask(Task task) {
        this.TASKS.add(task);
        this.updateStore();
    }

    public Task get(int idx) {
        return this.TASKS.get(idx);
    }

    public int length() {
        return TASKS.size();
    }

    public void toggleDone(int idx) {
        if (isValidIndex(idx, TASKS.size())) {
            TASKS.get(idx).markCompleted();
        }
    }

    public void removeTask(int idx) {
        if (isValidIndex(idx, TASKS.size())) {
            TASKS.remove(idx);
        }
        this.updateStore();
    }

    private void updateStore() {
        this.STORAGE_FILE.write(this.TASKS);
    }

    public static boolean isValidIndex(int idxFrom0, int numOfTasks) {
        if (idxFrom0 < 0 || idxFrom0 >= numOfTasks) {
            throw new IllegalArgumentException("task index passed in out of range");
        }
        return true;
    }

    /**
     * Filters the TaskList and returns all indexes of task which fulfil condition.
     * Frequent use with selectedTasks() to print tasks.
     *
     * @param predicate Function condition which must be fulfilled by task for its index to be returned
     * @return Array of integers (idx from 0) of tasks which fulfil predicate
     */
    public Integer[] filter(Function<Task, Boolean> predicate) {
        List<Integer> result = new ArrayList<>();
        int numOfTasks = this.TASKS.size();
        for (int i = 0; i < numOfTasks; i++) {
            Task currTask = this.TASKS.get(i);
            if (predicate.apply(currTask)) {
                result.add(i);
            }
        }
        Integer[] res = result.toArray(new Integer[0]);
        return res;
    }

    public String selectedTasks(Integer[] indexesFrom0) {
        StringBuilder result = new StringBuilder();
        int numOfTasks = this.TASKS.size();
        for (int i = 0; i < numOfTasks; i++) {
            List<Integer> listOfIndexesFrom1 = Arrays.asList(indexesFrom0);
            if (listOfIndexesFrom1.contains(i)) {
                result.append(String.format("%d: %s\n", i + 1, this.TASKS.get(i).toString()));
            }
        }
        return result.toString();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        int numOfTasks = this.TASKS.size();
        for (int i = 0; i < numOfTasks; i++) {
            int idxFrom1 = i + 1;
            result.append(String.format("%d: %s\n", idxFrom1, this.TASKS.get(i).toString()));
        }
        return result.toString();
    }
}
