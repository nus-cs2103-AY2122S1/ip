import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// immutable principles?
public final class TaskList {
    private final ArrayList<Task> TASKS;
    private Storage storageFile;

    //DELETE CLASS?
    //only objects of class Task go into array
    //@SafeVarargs
//    public static TaskList of(ArrayList<Task> tasks) {
//        return new TaskList(tasks);
//    }

    //parse in Files containing Strings (line) of task representation
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
        this.storageFile = storageFile;
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
        if (isValidIdx(idx)) {
            this.TASKS.get(idx).markCompleted();
        }
    }

    public void removeTask(int idx) {
        if (isValidIdx(idx)) {
            this.TASKS.remove(idx);
        }
        //TEST
        this.updateStore();
    }

    private void updateStore() {
        this.storageFile.write(this.TASKS);
    }

    private boolean isValidIdx(int idx) {
        if (idx < 0 || idx >= this.TASKS.size()) {
            throw new IllegalArgumentException("task index passed in out of range");
        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        String result = "";
        int numOfTasks = this.TASKS.size();
        for (int i = 0; i < numOfTasks; i++) {
            result += String.format("%d: %s\n", i+1, this.TASKS.get(i).toString());
        };
//        //add final task without \n
//        result += String.format("%d: %s", numOfTasks, this.tasks[numOfTasks - 1].toString());
        return result;
    }


}
