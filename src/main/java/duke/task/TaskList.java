package duke.task;

import duke.commands.Command.CommandType;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> arrayList) {
        this.taskList = arrayList;
    }

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

    public void addToTaskList(Task task) {
        this.taskList.add(task);
    }

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
