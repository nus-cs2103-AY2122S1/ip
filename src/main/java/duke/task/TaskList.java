package duke.task;

import java.util.ArrayList;

/**
 * Represents a TaskList object. The TaskList is able to add tasks, delete tasks, mark tasks as done,
 * as well as print out all the tasks in its list.
 */
public class TaskList {
    private ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Adds a task to the TaskList object
     *
     * @param task task to be added to the task list
     */
    public void addTask(Task task) {
        assert task != null : "task cannot be null";

        this.list.add(task);
    }

    /**
     * Marks the task with the respective index as done in the TaskList object
     *
     * @param index index of the task in the task list
     */
    public String markDone(int index) {
        int trueIndex = index - 1;

        list.get(trueIndex).isDone();

        String taskDoneMessage = "     Nice! I've marked this task as done:\n"
                + "       " + "[" + list.get(trueIndex).showType() + "]"
                + list.get(trueIndex).checkDone() + " "
                + list.get(trueIndex).showDescription() + "\n";

        return taskDoneMessage;
    }

    /**
     * Deletes the task with the respective index in the TaskList object
     *
     * @param index index of the task in the task list
     */
    public String deleteTask(int index) {
        int trueIndex = index - 1;

        String listData = "[" + list.get(trueIndex).showType() + "]"
                + list.get(trueIndex).checkDone() + " "
                + list.get(trueIndex).showDescription() + "\n";

        String deletedData = "     Noted. I've removed this task:\n"
                + "       " + listData
                + "     Now you have " + (list.size() - 1) + " tasks in the list\n";

        list.remove(trueIndex);

        return deletedData;
    }

    /**
     * Returns the size of the TaskList object.
     *
     * @return size/length of the TaskList
     */
    public int length() {
        return list.size();
    }

    /**
     * Prints out all the tasks in the entire TaskList object.
     */
    public String showList() {
        String fullList = "     " + "Here are the tasks in your list:";

        for (int i = 0; i < list.size(); i++) {
            String taskItem = "\n" + "     " + (i + 1) + "." + "["
                    + list.get(i).showType() + "]"
                    + list.get(i).checkDone() + " "
                    + list.get(i).showDescription();
            fullList += taskItem;
        }

        return fullList;
    }

    /**
     * Stores all the task type, task's done status and task name and deadline
     * in a format readable by Duke's storage component.
     *
     * @return String containing the details of all the tasks
     */
    public String refreshList() {
        String refreshList = "";

        for (Task task : list) {
            if (task.showType().equals("D") || task.showType().equals("E")) {
                refreshList += task.showType() + " | "
                        + (task.checkDone().equals("[X]") ? "1" : "0") + " | "
                        + task.showTaskOnly() + " | "
                        + task.showWhen() + "\n";
            } else {
                refreshList += task.showType() + " | "
                        + (task.checkDone().equals("[X]") ? "1" : "0") + " | "
                        + task.showDescription() + "\n";
            }
        }

        return refreshList;
    }

    /**
     * Searches the list for tasks containing the keyword and returns the respective tasks.
     *
     * @param keyword keyword of tasks to search in the taskList
     * @return tasks that contain the respective keyword
     */
    public String searchList(String keyword) {
        String searchList = "     " + "Here are the matching tasks in your list:";

        for (int k = 0; k < list.size(); k++) {
            if (list.get(k).showTaskOnly().contains(keyword)) {
                String searchItem = "\n" + "     " + (k + 1) + "." + "["
                        + list.get(k).showType() + "]"
                        + list.get(k).checkDone() + " "
                        + list.get(k).showDescription();
                searchList += searchItem;
            }
        }

        return searchList;
    }

}
