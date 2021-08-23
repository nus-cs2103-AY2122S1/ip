package duke;

import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> taskList = new ArrayList<>();

    public boolean isEmpty() {
        return (taskList.size() == 0);
    }

    public Task markDone(String taskName, Duke.TaskTypes tt) throws DukeException {
        int index = getTaskIndex(taskName, tt);
        if (index == -1) {
            throw new TaskNotFound(tt + ": " + taskName);
        } else {
            return markDone(index);
        }
    }

    public Task markDone(int index) throws TaskIndexOutOfBounds {
        if (index >= 0 && index < taskList.size()) {
            Task t = taskList.get(index);
            t.markAsDone();
            return t;
        } else {
            throw new TaskIndexOutOfBounds(index, taskList.size());
        }
    }

    public int size() {
        return taskList.size();
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public Task remove(int index) throws TaskIndexOutOfBounds {
        if (index >= 0 && index < taskList.size()) {
            return taskList.remove(index);
        } else {
            throw new TaskIndexOutOfBounds(index, taskList.size());
        }
    }

    public Task remove(String taskName, Duke.TaskTypes tt) throws DukeException {
        int index = getTaskIndex(taskName, tt);
        if (index == -1) {
            throw new TaskNotFound(tt + ": " + taskName);
        } else {
            return remove(index);
        }
    }

    public String saveString() {
        StringBuilder toWrite = new StringBuilder();
        taskList.forEach((t) -> toWrite.append(t.saveString()).append("\n"));
        return toWrite.toString();
    }

    public int getTaskIndex(String taskName, Duke.TaskTypes tt) {
        int i;
        switch (tt) {
        case TODO:
            i = taskList.indexOf(new Todo(taskName));
            break;
        case DEADLINE:
            i = taskList.indexOf(new Deadline(taskName));
            break;
        case EVENT:
            i = taskList.indexOf(new Event(taskName));
            break;
        default:
            i = -1;      // unreachable by design if all task cases are there
        }
        return i;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Here are the tasks in your list, meow:");
        for (int i = 0; i < taskList.size(); i++) {
            s.append("\n   ").append(i + 1).append(". ").append(taskList.get(i));
        }
        return s.toString();
    }


}
