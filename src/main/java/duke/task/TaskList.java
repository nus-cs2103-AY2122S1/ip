package duke.task;

import java.util.ArrayList;

import duke.DukeDate;
/**
 * Represents the list of tasks the user has. Stores the tasks in the form of <code>Task</code> objects.
 */
public class TaskList {
    private static final int INITIAL_CAPACITY = 100;
    private ArrayList<Task> userList;
    private int listSize = 0;

    /**
     * Creates a new TaskList.
     */
    public TaskList() {
        userList = new ArrayList<Task>(INITIAL_CAPACITY);
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
        for (int i = 1; i <= this.listSize; i++) {
            int taskIndex = taskIdToIndex(i);
            Task task = userList.get(taskIndex);
            String time = getTaskTime(task);
            String isCompleted = task.hasCompleted() ? "X" : " ";
            sb.append(
                    String.format(
                            "%d. %s  [%s] [%s] [%s] (%s)\n",
                            i,
                            task.getName(),
                            task.getTaskType(),
                            isCompleted,
                            task.getTags(),
                            time
                    )
            );
        }
        //Remove trailing new line character
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
        assert task != null;
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
        if (!isValidId(taskId)) {
            return String.format("Uh oh, seems like there is no task number %d", taskId);
        }

        int taskIndex = taskIdToIndex(taskId);
        Task task = userList.get(taskIndex);
        assert task != null;
        task.completeTask();
        return String.format(
                "You have completed task %d. %s",
                taskId,
                userList.get(taskId - 1).getName()
        );
    }

    /**
     * Removes task from the task list.
     * Does not do anything if taskId doesn't match any of the tasks in the list.
     *
     * @param taskId ID of the task in the list.
     * @return Message depending on the outcome of the action.
     */
    public String deleteTask(int taskId) {
        if (!isValidId(taskId)) {
            return String.format("Uh oh, seems like there is no task number %d", taskId);
        }

        int taskIndex = taskIdToIndex(taskId);
        Task task = userList.get(taskIndex);
        userList.remove(taskIndex);
        listSize--;
        String isCompleted = task.hasCompleted() ? "X" : " ";
        return String.format(
                "Alright,\nTask: %s [%s] [%s] [%s] (%s)\nHas been removed, you have %d tasks in the list",
                task.getName(),
                task.getTaskType(),
                isCompleted,
                task.getTags(),
                getTaskTime(task),
                listSize
        );
    }

    /**
     * Returns the specified task in a string format used for saving in txt file.
     *
     * @param taskIndex Index of the task in the list, starting from 0.
     * @return String representing the Task.
     */
    public String getTaskSaveFormat(int taskIndex) {
        if (!isValidIndex(taskIndex)) {
            return "";
        }

        Task task = userList.get(taskIndex);
        return task.getSaveFormat();
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
        for (int i = 1; i <= this.listSize; i++) {
            Task task = userList.get(taskIdToIndex(i));
            if (task.getName().contains(keyword)) {
                sb.append(String.format("%d. %s [%s]\n", i, task.getName(), task.getTaskType()));
                tasksMatched++;
            }
        }
        if (tasksMatched == 0) {
            return String.format(
                    "I couldn't find any tasks that matched the keyword \"%s\", *Quack*!",
                    keyword
            );
        }

        return String.format(
                "I found %d results when looking for \"%s\"!\n%s",
                tasksMatched,
                keyword,
                sb.toString()
        );
    }

    public String getTaskTags(int taskId) {
        if (!isValidId(taskId)) {
            return String.format("Uh oh, seems like there is no task number %d", taskId);
        }

        int taskIndex = taskIdToIndex(taskId);
        Task task = userList.get(taskIndex);
        String tags = task.getTags();
        return String.format(
                "Task: %s [%s]\nTags: %s",
                task.getName(),
                task.getTaskType(),
                tags
        );
    }

    public String addTaskTag(int taskId, String tag) {
        assert !tag.contains(",");
        if (!isValidId(taskId)) {
            return String.format("Uh oh, seems like there is no task number %d", taskId);
        }

        int taskIndex = taskIdToIndex(taskId);
        Task task = userList.get(taskIndex);
        return task.addTag(tag);
    }

    public String deleteTaskTag(int taskId, String tag) {
        assert !tag.contains(",");
        if (!isValidId(taskId)) {
            return String.format("Uh oh, seems like there is no task number %d", taskId);
        }

        int taskIndex = taskIdToIndex(taskId);
        Task task = userList.get(taskIndex);
        return task.deleteTag(tag);
    }

    private String getTaskTime(Task t) {
        assert t != null;
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

    private boolean isValidId(int id) {
        boolean isBiggerThanZero = id > 0;
        boolean isWithinListLength = id <= listSize;
        return isBiggerThanZero && isWithinListLength;
    }

    private boolean isValidIndex(int index) {
        boolean isNonNegative = index >= 0;
        boolean isLessThanListLength = index < listSize;
        return isNonNegative && isLessThanListLength;
    }

    private int taskIdToIndex(int taskId) {
        return taskId - 1;
    }
}
