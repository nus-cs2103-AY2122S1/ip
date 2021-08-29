package duke.task;

import java.util.ArrayList;

import duke.DukeDate;
/**
 * Represents the list of tasks the user has. Stores the tasks in the form of <code>Task</code> objects.
 */
public class TaskList {
    private ArrayList<Task> userList;
    private int listSize = 0;

    /**
     * Creates a new TaskList.
     */
    public TaskList() {
        userList = new ArrayList<Task>(100);
    }

    /**
     * Returns the current number of tasks in the list.
     *
     * @return Number of tasks in the list.
     */
    public int getListSize() {
        return listSize;
    }

    /**
     * Returns a String representing the list of tasks.
     *
     * @return String representing list of tasks, or a message if the list is empty.
     */
    public String getTasks() {
        if (listSize == 0) {
            return "The list is empty! *quack*";
        }
        StringBuilder sb = new StringBuilder();
        Task t;
        String time;
        for (int i = 1; i <= this.listSize; i++) {
            t = userList.get(i - 1);
            time = getTaskTime(t);
            sb.append(
                    String.format(
                            "%d. %s  [%s] [%s] (%s)\n",
                            i,
                            t.getName(),
                            t.getTaskType(),
                            t.hasCompleted() ? "X" : " ",
                            time
                    )
            );
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    /**
     * Adds a task into the task list.
     *
     * @param task Task to be added.
     * @return Message that the task has been successfully added.
     */
    public String addTask(Task task) {
        userList.add(task);
        listSize++;
        String time = getTaskTime(task);
        return String.format(
                "added: %s [%s] (%s)\nCurrently %d tasks in the list",
                task.getName(),
                task.getTaskType(),
                time,
                listSize
        );
    }

    /**
     * Sets a task in the task list as complete.
     * Does not do anything if taskId doesn't match any of the tasks in the list.
     *
     * @param taskId ID of the task in the list.
     * @return Message depending on the outcome of the action.
     */
    public String markComplete(int taskId) {
        if (taskId - 1 < listSize) {
            if (userList.get(taskId - 1).completeTask()) {
                return String.format(
                        "You have completed duke.task %d. %s",
                        taskId,
                        userList.get(taskId - 1).getName()
                );
            } else {
                return "Something seems to have went terribly wrong";
            }
        } else {
            return String.format("Uh oh, seems like there is no duke.task number %d", taskId);
        }
    }

    /**
     * Removes task from the task list.
     * Does not do anything if taskId doesn't match any of the tasks in the list.
     *
     * @param taskId ID of the task in the list.
     * @return Message depending on the outcome of the action.
     */
    public String deleteTask(int taskId) {
        if (taskId - 1 < listSize) {
            Task t = userList.get(taskId - 1);
            userList.remove(taskId - 1);
            listSize--;
            return String.format(
                    "Alright,\nduke.duke.Task.Task: %s [%s] [%s] (%s)\nHas been removed, you have %d tasks in the list",
                    t.getName(),
                    t.getTaskType(),
                    t.hasCompleted() ? "X" : " ",
                    getTaskTime(t),
                    listSize
            );
        } else {
            return String.format("Uh oh, seems like there is no duke.task number %d", taskId);
        }
    }

    /**
     * Returns the specified task in a string format used for saving in txt file.
     *
     * @param taskIndex Index of the task in the list, starting from 0.
     * @return String representing the Task.
     */
    public String getTaskSaveFormat(int taskIndex) {
        if (taskIndex < listSize) {
            Task t = userList.get(taskIndex);
            return t.getSaveFormat();
        } else {
            return "";
        }
    }

    /**
     * Searches for a specific keyword in the task list.
     * This is done by comparing keyword with the task names, in a linear fashion.
     *
     * @param keyword Keyword to be used in the search.
     * @return String representing the results of the search.
     */
    public String searchKeyword(String keyword) {
        int tasksMatched = 0;
        StringBuilder sb = new StringBuilder();
        Task t;

        for (int i = 1; i <= this.listSize; i++) {
            t = userList.get((i - 1));
            if (t.getName().contains(keyword)) {
                sb.append(
                        String.format(
                                "%d. %s [%s]\n",
                                i,
                                t.getName(),
                                t.getTaskType()
                        )
                );
                tasksMatched++;
            }
        }

        if (tasksMatched == 0) {
            return String.format(
                    "I couldn't find any tasks that matched the keyword \"%s\", *Quack*!",
                    keyword
            );
        } else {
            return String.format(
                    "I found %d results when looking for \"%s\"!\n%s",
                    tasksMatched,
                    keyword,
                    sb.toString()
            );
        }
    }

    private String getTaskTime(Task t) {
        String time;
        switch (t.getTaskType()) {
        case "D":
            Deadline d = (Deadline) t;
            time = "by: " + DukeDate.formatDateOutput(d.getTime());
            break;
        case "E":
            Event e = (Event) t;
            time = "at: " + DukeDate.formatDateOutput(e.getTime());
            break;
        default:
            time = "No time specified";
        }
        return time;
    }
}
