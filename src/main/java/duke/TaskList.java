package duke;

import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public int getSize() {
        return taskList.size();
    }

    public void addToList(Task task) {
        taskList.add(task);
    }

    /**
     * Removes the element in the list represented by the index.
     *
     * @param index The index of the element to be removed.
     */
    public Task deleteFromList(int index) {
        if (index <= 0 || index > taskList.size()) {
            // number given is out of bounds of the taskList
            System.out.println("Invalid Argument: Index " + index + " is out of bounds!");
            return null;
        } else {
            // no problems with the input, a task is added
            Task toDelete = taskList.get(index - 1);
            taskList.remove(index - 1);
            return toDelete;
        }
    }

    /**
     * Prints all the elements in the TaskList.
     */
    public String printList() {
        if (taskList == null || taskList.isEmpty()) {
            return "You currently have no tasks!";
        } else {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < taskList.size(); i++) {
                result.append(i + 1).append(". ").append(taskList.get(i)).append("\n");
            }
            return result.toString();
        }
    }

    /**
     * Marks the task at the given index as done.
     *
     * @param index The index of the task to be marked as done.
     */
    public Task markAsDone(int index) {
        if (index <= 0 || index > taskList.size()) {
            // number given is out of bounds of the taskList
            System.out.println("Invalid Argument: Index " + index + " is out of bounds!");
            return null;
        } else {
            // no problems with the input, a task is added
            Task toMark = taskList.get(index - 1);
            taskList.get(index - 1).markAsDone();
            return toMark;
        }
    }

    /**
     * Finds the tasks that match a given String.
     *
     * @param str The given String.
     * @return An ArrayList of the tasks that contain the string.
     */
    public ArrayList<Task> findMatches(String str) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task task : this.taskList) {
            if (task.toString().contains(str)) {
                result.add(task);
            }
        }
        return result;
    }
}
