package duke.task;

import java.util.ArrayList;
import java.util.Comparator;

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

    /**
     * Sorts the task list by task type and date.
     */
    public void sort() {
        taskList.sort(new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                return compareTwoTasks(t1, t2);
            }
        });
    }

    private int compareTwoTasks(Task t1, Task t2) {
        String type1 = t1.taskType;
        String type2 = t2.taskType;
        if (!type1.equals(type2)) {
            return type1.compareTo(type2);
        } else {
            if (type1.equals("T")) {
                return t1.description.compareTo(t2.description);
            } else {
                SingleTimedTask tT1 = (SingleTimedTask) t1;
                SingleTimedTask tT2 = (SingleTimedTask) t2;
                return tT1.getDate().compareTo(tT2.getDate());
            }
        }
    }

    public int getCapacity() {
        return taskList.size();
    }
    public ArrayList<Task> getArrayList() {
        return this.taskList;
    }
}
