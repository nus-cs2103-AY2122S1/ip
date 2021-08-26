import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// immutable principles?
public final class TaskList {
    private final ArrayList<Task> tasks;

    //only objects of class Task go into array
    //@SafeVarargs
    public static TaskList of(ArrayList<Task> tasks) {
        return new TaskList(tasks);
    }

    //parse in Files containing Strings (line) of task representation
    public static TaskList of(File tasksStorageFile) {
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
        return TaskList.of(tasks);
    }



    private TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        //TEST

    }

    public Task get(int idx) {
        return this.tasks.get(idx);
    }

    public int length() {
        return tasks.size();
    }

    public void toggleDone(int idx) {
        if (isValidIdx(idx)) {
            this.tasks.get(idx).markCompleted();
        }
    }

    public void removeTask(int idx) {
        if (isValidIdx(idx)) {
            this.tasks.remove(idx);
        }
    }

    private updateStore() {
        Storage.write()
    }

    private boolean isValidIdx(int idx) {
        if (idx < 0 || idx >= this.tasks.size()) {
            throw new IllegalArgumentException("task index passed in out of range");
        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        String result = "";
        int numOfTasks = this.tasks.size();
        for (int i = 0; i < numOfTasks; i++) {
            result += String.format("%d: %s\n", i+1, this.tasks.get(i).toString());
        };
//        //add final task without \n
//        result += String.format("%d: %s", numOfTasks, this.tasks[numOfTasks - 1].toString());
        return result;
    }


}
