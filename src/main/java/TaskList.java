import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public boolean addTask(Task task) {
        try {
            tasks.add(task);
        } catch (Exception e) {
            System.err.println("Fail to add task: " + e.getMessage());
            return false;
        }
        return true;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public String printList() {
        StringBuilder listAsString = new StringBuilder();
        int count = 0;
        for (Task task : tasks) {
            count++;
            listAsString.append(DukeCore.space)
                    .append(count)
                    .append(".")
                    .append(task.getDescriptionWithStatus())
                    .append("\n");
        }
        if (count > 0) {
            listAsString.setLength(listAsString.length() - 1);
        }
        return listAsString.toString();
    }

}
