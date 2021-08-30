package duke;
import java.util.ArrayList;
import duke.tasks.Task;
import duke.status.Status;
import duke.exceptions.DoneOutOfBoundsException;
import duke.exceptions.DeleteOutOfBoundsException;

public class TaskList {
    private final ArrayList<Task> allTasks;

    public TaskList(ArrayList<Task> allTasks) {
        this.allTasks = allTasks;
    }

    public TaskList() {
        this.allTasks = new ArrayList<>();
    }

    public boolean addNewTask(Task newTask) {
        return allTasks.add(newTask);
    }

    public ArrayList<Task> getTaskList() {
        return allTasks;
    }

    public Task markTaskAsDone(int taskNumber) throws DoneOutOfBoundsException {
        if (taskNumber > allTasks.size()) {
            throw new DoneOutOfBoundsException(allTasks.size());
        }
        Task updatedTask = allTasks.
        get(taskNumber - 1).
        updateStatus(Status.COMPLETED.getStatus());
        allTasks.set(taskNumber - 1, updatedTask);
        return updatedTask;
    }

    public Task deleteTask(
            int taskNumber) throws DeleteOutOfBoundsException {
        if (taskNumber > allTasks.size()){ 
            throw new DeleteOutOfBoundsException(allTasks.size());
        }
        return allTasks.remove(taskNumber - 1);
    }

    public int getTaskListLength() {
        return allTasks.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= allTasks.size(); i++) {
            if (i == allTasks.size()) {
                sb.append(i + ". " + allTasks.get(i - 1) + "\n");
                continue;
            }
            sb.append(i + ". " + allTasks.get(i - 1) + "\n");
        }
        return sb.toString();
    }
}
