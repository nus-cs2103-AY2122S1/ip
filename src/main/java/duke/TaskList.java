package duke;

import java.util.ArrayList;

import duke.task.Task;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns a string message indicating success after adding a task to the list of tasks.
     *
     * @param newTask New task to be added.
     * @return String success message.
     */
    public String addTask(Task newTask) {
        tasks.add(newTask);
        return "Got it. I've added this task:\n"
                + "  " + newTask + "\n"
                + countTasks();
    }

    /**
     * Returns a string message indicating success after marking a task done in the list of tasks.
     *
     * @param taskIndex Index of the Task.
     * @return String success message.
     */
    public String markTaskDone(int taskIndex) {
        Task currTask = tasks.get(taskIndex - 1);
        currTask.setDone();
        return "Nice! I've marked this task as done:\n"
                + "  " + taskIndex + ". " + currTask;
    }

    /**
     * Returns a string message indicating success after deleting a task from the list of tasks.
     *
     * @param taskIndex Index of the Task.
     * @return String success message.
     */
    public String deleteTask(int taskIndex) {
        Task currTask = tasks.get(taskIndex - 1);
        tasks.remove(taskIndex - 1);
        return "Noted. I've removed this task:\n"
                + "  " + taskIndex + ". " + currTask + "\n"
                + countTasks();
    }

    /**
     * Returns the ArrayList of tasks maintained by this TaskList object.
     *
     * @return ArrayList of Task objects.
     */
    public ArrayList<Task> getList() {
        return tasks;
    }

    /**
     * Returns the string representation of a Task (based on the Index) to be used for storage.
     *
     * @param taskIndex Index of the Task.
     * @return String for storage.
     */
    public String getTaskStorage(int taskIndex) {
        return tasks.get(taskIndex - 1).toStorage();
    }

    /**
     * Returns a boolean indicating the validity of the Task's Index.
     *
     * @param taskIndex Index of the Task.
     * @return Validity of the Index.
     */
    public boolean isIndexValid(int taskIndex) {
        return ((taskIndex - 1) < tasks.size() && (taskIndex - 1) > -1);
    }

    /**
     * Returns an ArrayList containing all the tasks that have the content in their description.
     *
     * @param content Content to be searched for.
     * @return ArrayList of Task objects.
     */
    public ArrayList<Task> findTasks(String content) {
        ArrayList<Task> findTasksResult = new ArrayList<>();

        for (Task currTask: tasks) {
            if (currTask.hasContent(content)) {
                findTasksResult.add(currTask);
            }
        }

        return findTasksResult;
    }

    private String countTasks() {
        if (tasks.size() > 0) {
            return "Now you have " + tasks.size() + " tasks in the list.";
        } else {
            return "There are no tasks in your list.";
        }
    }
}
