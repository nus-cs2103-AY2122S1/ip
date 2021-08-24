import Exceptions.InvalidTaskNumberException;
import Tasks.Task;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    private Task getTask(int i) throws InvalidTaskNumberException {
        if (i == 0 || i > this.tasks.size()) {
            throw new InvalidTaskNumberException(this.tasks.size());
        } else {
            return this.tasks.get(i - 1);
        }
    }

    public int getSize() {
        return tasks.size();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void showList(Ui ui) {
        if (this.tasks.size() == 0) {
            ui.printOut("No tasks added yet!");
        } else {
            String[] messages = new String[this.tasks.size()];
            int i = 0;
            for (Task task : this.tasks) {
                messages[i] = String.format("%d. %s", ++i, task.toString());
            }
            ui.printOut(messages);
        }
    }

    public String[] deleteTask(int i) throws InvalidTaskNumberException {
        Task task = this.getTask(i);
        boolean removed = this.tasks.remove(task);
        return new String[] {"I have deleted this task:","    " + task.toString()};
    }

    public String[] markDone(int i) throws InvalidTaskNumberException {
        Task task = this.getTask(i);
        boolean marked = task.markDone();
        if (marked) {
            return new String[] {"Nice! I've marked this task as done:", "    " + task.toString()};
        } else {
            return new String[] {"This was completed previously!:", "    " + task.toString()};
        }
    }

    public String getData() {
        StringBuilder data = new StringBuilder();
        for (Task task : tasks) {
            data.append(task).append("\n");
        }
        return data.toString();
    }
}
