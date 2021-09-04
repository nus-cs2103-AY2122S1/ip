package duke.tasks;
import java.util.ArrayList;

/** Manage tasklists */
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
        boolean isFirst = true;
        for (int i = 0; i < storedList.size(); i++) {
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append("\n");
            }
            sb.append(String.format("%d.%s", i + 1, storedList.get(i)));
        }
        if (isFirst) {
            sb.append("\n");
        }
        return sb.toString();
    }

    public void add(Task task) {
        storedList.add(task);
    }

    /**
     * Converts the whole task list to a String containing each individual
     * task serialized and separated in different lines.
     *
     * @return String representation of entire task list serialized.
     */
    public String serialize() {
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for(Task task : storedList) {
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append("\n");
            }
            sb.append(TaskUtils.taskToString(task));

        }
        return sb.toString();
    }

    /**
     * Gets the size of the tasklist
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
