package duke.tasks;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task addTodo(String input, boolean isDone) {
        Task t = new Todo(input, isDone);
        tasks.add(t);
        return t;
    }

    public Task addDeadline(String name, LocalDateTime deadline, boolean isDone) {
        Task t = new Deadline(name, deadline, isDone);
        tasks.add(t);
        return t;
    }

    public Task addEvent(String name, LocalDateTime time, boolean isDone) {
        Task t = new Event(name, time, isDone);
        tasks.add(t);
        return t;
    }

    public Task deleteTask(int index) {
        Task delete = tasks.get(index - 1);
        tasks.remove(index - 1);
        return delete;
    }

    public Task completeTask(int index) {
        Task complete = tasks.get(index - 1);
        complete.completeTask();
        return complete;
    }

    public String getAllTasks() {
        String listMessage = "";
        for (int i = 0; i < tasks.size(); i++) {
            listMessage = listMessage + (i + 1) + "." + tasks.get(i).toString() + "\n";
        }
        return listMessage;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getTotalTasksNumber() {
        return tasks.size();
    }
}
