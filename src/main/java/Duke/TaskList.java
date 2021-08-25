package Duke;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private final List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public List<Task> getTaskList() {
        return this.taskList;
    }

    public String size() {
        int size = taskList.size();
        if (size < 2) {
            return "Now you have " + size + " task in your list";
        } else {
            return "Now you have " + size + " tasks in your list";
        }
    }

    public String printList() {
        StringBuilder output = new StringBuilder("Here is the list of all tasks: \n");
        for (int i = 0; i < taskList.size(); i++) {
            output.append(i + 1).append(". ").append(taskList.get(i)).append("\n");
        }
        return output.toString();
    }

    public String done(int index) {
        if (index > taskList.size() || index <= 0) {
            throw new DukeException("", DukeException.TYPE.OUT_OF_BOUND);
        } else {
            index--;
            Task calledTask = taskList.remove(index);
            calledTask.markAsCompleted();
            taskList.add(index, calledTask);
            return "Nice! I've marked this task as done: \n" + calledTask;
        }
    }

    public String doneAll() {
        for (Task task : taskList) {
            task.markAsCompleted();
        }
        return "Nice! I've marked all tasks in your list as done!";
    }

    public String delete(int index) {
        if (index > taskList.size() || index <= 0) {
            throw new DukeException("Error", DukeException.TYPE.OUT_OF_BOUND);
        } else {
            index--;
            Task deletedTask = taskList.remove(index);
            return "Noted. I've removed this task:\n" + deletedTask + "\n" + size();
        }
    }

    public String deleteAll() {
        taskList.clear();
        return "Noted. I've reset your list and remove all tasks";
    }

    public String addTask(String task, Task.Type type) {
        task = task.trim();
        Task newTask;
        switch (type) {
            case TODO:
                newTask = new Todo(task);
                break;
            case DEADLINE:
                newTask = new Deadline(task);
                break;
            case EVENT:
                newTask = new Event(task);
                break;
            default:
                throw new DukeException("Error: ", DukeException.TYPE.SYNTAX_ERROR);
        }
        this.taskList.add(newTask);
        return "Got it! I've added this task:\n" + newTask +
                "\n" + size();
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }
}
