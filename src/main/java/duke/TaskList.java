package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> taskList = new ArrayList<>();

    public int getCount() {
        return taskList.size();
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public void addTodo(String item) {
        taskList.add(new ToDo(item));
    }

    public void addDeadline(String item, String by) throws IrisException {
        taskList.add(new Deadline(item, by));
    }

    public void addEvent(String item, String at) throws IrisException {
        taskList.add(new Event(item, at));
    }

    private void validateTaskIndex(int index) throws IrisException {
        if (index <= 0) throw new IrisException("Please enter a valid task index.");
        int count = taskList.size();
        if (index > count) throw new IrisException(String.format("Your task list only has %d items", count));
    }

    public Task done(int index) throws IrisException {
        validateTaskIndex(index);
        Task task = taskList.get(index - 1);
        task.markComplete();
        return task;
    }

    public Task delete(int index) throws IrisException {
        validateTaskIndex(index);
        Task task = taskList.get(index - 1);
        taskList.remove(index - 1);
        return task;
    }

    public List<Task> find(String searchTerm) {
        List<Task> result = new ArrayList<>();
        for (Task task : taskList) {
            if (task.contains(searchTerm)) {
                result.add(task);
            }
        }
        return result;
    }

    public String[] toCommands() {
        List<String> commands = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            commands.add(taskList.get(i).toCommand(i + 1));
        }
        String[] result = new String[commands.size()];
        return commands.toArray(result);
    }
}
