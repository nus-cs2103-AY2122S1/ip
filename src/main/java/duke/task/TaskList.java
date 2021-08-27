package duke.task;

import duke.util.Storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// immutable principles?
public final class TaskList {
    private final ArrayList<Task> TASKS;
    private final Storage STORAGE_FILE;

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
        //TEST
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
        //TEST
        this.updateStore();
    }

    private void updateStore() {
        this.STORAGE_FILE.write(this.TASKS);
    }

    public static boolean isValidIndex(int idxFrom0, int numOfTasks) {
        if (idxFrom0 < 0 || idxFrom0 >= numOfTasks) {
            throw new IllegalArgumentException("duke.task index passed in out of range");
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        int numOfTasks = this.TASKS.size();
        for (int i = 0; i < numOfTasks; i++) {
            result.append(String.format("%d: %s\n", i + 1, this.TASKS.get(i).toString()));
        }
//        //add final duke.task without \n
//        result += String.format("%d: %s", numOfTasks, this.tasks[numOfTasks - 1].toString());
        return result.toString();
    }


}
