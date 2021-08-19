import java.util.ArrayList;
import java.util.Arrays;

// immutable principles?
public final class ToDoList {
    private final Task[] tasks;

    //only objects of class Task go into array
    @SafeVarargs
    public static ToDoList of(Task... tasks) {
        return new ToDoList(tasks);
    }
    private ToDoList(Task[] tasks) {
        this.tasks = tasks;
    }

    public ToDoList addTask(Task task) {
        int newLength = tasks.length + 1;
        Task[] newTasks = new Task[newLength];
        for (int i = 0; i < tasks.length; i++) {
            newTasks[i] = this.tasks[i];
        }
        newTasks[newLength - 1] = task;
        return new ToDoList(newTasks);
    }

    public Task get(int idx) {
        return this.tasks[idx];
    }

    @Override
    public String toString() {
        String result = "";
        int numOfTasks = this.tasks.length;
        for (int i = 0; i < numOfTasks - 1; i++) {
            result += String.format("%d: %s\n", i+1, this.tasks[i].toString());
        };
        //add final task without \n
        result += String.format("%d: %s", numOfTasks, this.tasks[numOfTasks - 1].toString());
        return result;
//      return Arrays.toString(this.tasks);
    }
}
