package Duke;

import java.io.Serializable;
import java.util.ArrayList;

public class TaskList implements Serializable {
    private ArrayList<Task> tasks;
    private int numTask;

    public TaskList(){
        this.tasks = new ArrayList<>();
        this.numTask = 0;
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.numTask = tasks.size();
    }

    public void addCustom(Task task) {
        tasks.add(task);
        this.numTask++;
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + task);
        System.out.printf("Now you have %d %s in the list.\n", this.numTask, this.numTask == 1 ? "task" : "tasks");
    }


    public void list() {
        for (int i = 0; i < this.numTask; i++) {
            System.out.println((i + 1)+ ". " + tasks.get(i));
        }
    }

    public void done(int taskNumber) {
        tasks.get(taskNumber - 1).complete();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("  " + tasks.get(taskNumber - 1));
    }

    public void delete(int taskNumber) {
        Task task = tasks.get(taskNumber - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        tasks.remove(taskNumber - 1);
        this.numTask--;
        System.out.printf("Now you have %d %s in the list.\n", this.numTask, this.numTask == 1 ? "task" : "tasks");
    }

    public boolean isEmpty() throws Duke.DukeException {
        if (this.numTask == 0) {
            if (!this.tasks.isEmpty()) {
                throw new Duke.DukeException("TaskList is empty without any items");
            }
            return true;
        }
        return false;
    }
}