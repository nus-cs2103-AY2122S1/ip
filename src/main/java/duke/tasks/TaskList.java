package duke.tasks;
import java.util.ArrayList;

/** Manage tasklists with filecontroller */
public class TaskList {
    ArrayList<Task> storedList;

    /**
     * Constructor for tasklist
     * @param string a string representation of tasks
     */
    public TaskList(String string) {
        storedList = new ArrayList<Task>();

        string.lines().forEach((line) -> {
            Task savedTask = TaskUtils.stringToTask(line);
            if (savedTask != null) {
                storedList.add(savedTask);
            } else {
                System.out.println("Line " + line + " is not a task");
            }
        });
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (int i = 0; i < storedList.size(); i++) {
            if (first) {
                first = false;
            } else {
                sb.append("\n");
            }
            sb.append(String.format("%d.%s", i + 1, storedList.get(i)));
        }
        if (first) {
            sb.append("\n");
        }
        return sb.toString();
    }

    public void add(Task task) {
        storedList.add(task);
    }

    public String serialize() {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for(Task task : storedList) {
            if (first) {
                first = false;
            } else {
                sb.append("\n");
            }
            sb.append(TaskUtils.taskToString(task));

        }
        return sb.toString();
    }

    /**
     * gets the size of the tasklist
     * @return the size of the tasklist
     */
    public int size() {
        return storedList.size();
    }

    public Task get(int index) {
        return storedList.get(index);
    }

    public Task remove(int index) {
        return storedList.remove(index);
    }
}
