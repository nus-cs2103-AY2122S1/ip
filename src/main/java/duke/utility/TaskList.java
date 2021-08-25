package duke.utility;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TaskList {

    private final List<Task> tasks;
    protected final HashSet<String> existingTasks;
    
    public TaskList() {
        this.tasks = new ArrayList<Task>(100);
        this.existingTasks = new HashSet<String>();
    }
    
    public TaskList(List<Task> previousTasks) {
        this.tasks = new ArrayList<Task>(100);
        this.existingTasks = new HashSet<String>();
        for (Task previousTask : previousTasks) {
            this.tasks.add(previousTask);
            this.existingTasks.add(previousTask.getTaskName());
        }
    }
    
    String add(Task task) {
        this.tasks.add(task);
        this.existingTasks.add(task.getTaskName());
        return String.format("New task added to list:\n%s", task);
    }

    protected String markAsCompleted(String taskName) throws DukeException.TaskAlreadyCompleteException,
            DukeException.NoSuchTaskException {
        int taskIdx = this.getTaskIdx(taskName);
        Task completedTask = this.tasks.get(taskIdx);
        if (completedTask.getIsCompleted()) {
            throw new DukeException.TaskAlreadyCompleteException("Task is already complete!!");
        }
        this.tasks.remove(taskIdx);
        this.tasks.add(taskIdx, completedTask.markAsCompleted());
        return "Task marked as completed:\n" + this.tasks.get(taskIdx).toString();
    }
    
    String deleteTask(int taskNum) throws DukeException.InvalidTaskNumException {
        if (taskNum > this.tasks.size() || taskNum < 1) {
            throw new DukeException.InvalidTaskNumException("Task number " + taskNum + " does not exist!");
        } else {
            Task toRemove = this.tasks.get(taskNum - 1);
            this.tasks.remove(taskNum - 1);
            this.existingTasks.remove(toRemove.getTaskName());
            return "Successfully deleted:\n" + toRemove;
        }
    }

    public String getAllTasks() throws DukeException.EmptyTaskListException {
        StringBuilder sb = new StringBuilder();
        if (this.tasks.size() == 0) {
            throw new DukeException.EmptyTaskListException("There are no items in your list!");
        }
        sb.append("Your list contains:\n");
        for (int i = 0; i < this.tasks.size(); i++) {
            String itemNum = i + 1 + ". ";
            sb.append(itemNum);
            sb.append(this.tasks.get(i).toString());
            if (i < this.tasks.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    protected int getTaskIdx(String taskName) throws DukeException.NoSuchTaskException {
        int currentTaskNum = 0;
        while (currentTaskNum < this.tasks.size()) {
            if (this.tasks.get(currentTaskNum).getTaskName().equals(taskName)) {
                return currentTaskNum;
            } else {
                currentTaskNum++;
            }
        }
        throw new DukeException.NoSuchTaskException("Task is not in list!");
    }
}
