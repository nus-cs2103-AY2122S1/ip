import java.util.ArrayList;

public class TaskList {
    private ArrayList<String> listOfTasks;

    private TaskList() {
        this.listOfTasks = new ArrayList<>();
    }

    /**
     * Add a task into the list of tasks
     *
     * @param task the task to be added into the list of tasks
     */
    public void addTaskToList(String task) {
        this.listOfTasks.add(task);
    }

    /**
     * Public method to create a TaskList
     *
     * @return a new TaskList that has no tasks stored
     */
    public static TaskList makeNewTaskList() {
        return new TaskList();
    }

    @Override
    public String toString() {
        if (this.listOfTasks.size() == 0) { // if there is no task within list
            return "\tYou have not added any tasks to your list.\n"
                    + "\tLog any task you wish to add.\n";
        }
        int i = 1;
        String toPrint = "";

        for (String task : this.listOfTasks) {
            toPrint = toPrint + "\t" + i + ". " + task + "\n";
            i++;
        }
        return toPrint;
    }

}
