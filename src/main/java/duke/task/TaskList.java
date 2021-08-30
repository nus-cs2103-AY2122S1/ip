package duke.task;

import java.util.ArrayList;

import duke.commands.Command.CommandType;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> arrayList) {
        this.taskList = arrayList;
    }

    /**
     * This method searches the TaskList for any Tasks with the
     * description containing the searchString.
     *
     * @param searchString The String being searched for
     * @param tasks The TaskList being searched
     * @return The ArrayList of Tasks that contain the searchString
     */
    public ArrayList<Task> searchTaskList(String searchString, TaskList tasks) {
        ArrayList<Task> taskList = tasks.getArrayList();
        ArrayList<Task> arrayList = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getDescription().contains(searchString)) {
                arrayList.add(task);
            }
        }
        return arrayList;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The Task to be added
     */
    public void addToTaskList(Task task) {
        this.taskList.add(task);
    }

    /**
     * This method edits the Task List depending on the
     * CommandType of the Command.
     *
     * @param lineNumber
     * @param commandType
     * @return
     */
    public Task editTaskList(int lineNumber, CommandType commandType) {
        switch(commandType) {
        case DONE:
            taskList.get(lineNumber).markDone();
            return taskList.get(lineNumber);
        case UNDO:
            taskList.get(lineNumber).markUndone();
            return taskList.get(lineNumber);
        case DELETE:
            return taskList.remove(lineNumber);
        default:
            return null;
        }
    }

    public int getCapacity() {
        return taskList.size();
    }
    public ArrayList<Task> getArrayList() {
        return this.taskList;
    }
}
