import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }

    public Task lastTask() {
        return tasks.get(tasks.size() - 1);
    }

    public void addTodoTask(String title) {
        tasks.add(new Todo(title));
    }

    public void addDeadlineTask(String title, String deadline) {
        tasks.add(new Deadline(title, deadline));
    }

    public void addEventTask(String title, String deadline) {
        tasks.add(new Event(title, deadline));
    }

    public Task deleteTask(int taskNo) {
        try {
            Task task = tasks.get(taskNo);
            tasks.remove(task);
            return task;
        } catch (IndexOutOfBoundsException e) {
            if (tasks.isEmpty()) {
                throw new DukeException("Nothing in the list to delete!");
            }
            throw new DukeException(String.format("Enter a valid number between 1 - %d", tasks.size()));
        }
    }

    public Pair<Boolean, Task> markTaskDone(int taskNo) {
        try {
            Task task = tasks.get(taskNo);
            return new Pair<Boolean, Task>(task.markAsDone(), task);
        } catch (IndexOutOfBoundsException e) {
            if (tasks.isEmpty()) {
                throw new DukeException("Nothing in the list!");
            }
            throw new DukeException(String.format("Enter a valid number between 1 - %d", tasks.size()));
        }
    }

}
