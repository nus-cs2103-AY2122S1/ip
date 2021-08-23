package duke;

import java.util.ArrayList;
import java.util.List;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class TaskList {
    private final List<Task> tasks = new ArrayList<>();

    public int getCount() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public void addTodo(String item) {
        tasks.add(new ToDo(item));
    }

    public void addDeadline(String item, String by) throws IrisException {
        tasks.add(new Deadline(item, by));
    }

    public void addEvent(String item, String at) throws IrisException {
        tasks.add(new Event(item, at));
    }

    private void validateTaskIndex(int index) throws IrisException {
        if (index <= 0) {
            throw new IrisException("Please enter a valid task index.");
        }
        int count = tasks.size();
        if (index > count) {
            throw new IrisException(String.format("Your task list only has %d items", count));
        }
    }

    public Task done(int index) throws IrisException {
        validateTaskIndex(index);
        Task task = tasks.get(index - 1);
        task.markDone();
        return task;
    }

    public Task delete(int index) throws IrisException {
        validateTaskIndex(index);
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        return task;
    }

    public String[] toCommands() {
        List<String> commands = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            commands.add(tasks.get(i).toCommand(i + 1));
        }
        String[] result = new String[commands.size()];
        return commands.toArray(result);
    }
}
