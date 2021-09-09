package duke.taskList;

import duke.exceptions.*;
import duke.tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<Task>();

    /**
     * A constructor for TaskList specifying list of saved tasks.
     *
     * @param tasks array list of Task.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * A constructor for TaskList.
     */
    public TaskList() {
    }

    /**
     * Marks the task done and returns the task description.
     *
     * @param str user input to indicate a task is done.
     * @return description of task that is marked done.
     */
    public String done(int index) throws OutOfBoundException {
        return tasks.get(index).markDone();
    }

    /**
     * Deletes the task and returns the task description.
     *
     * @param str user input to indicate deletion of a task.
     * @return description of task that is deleted.
     */
    public String delete(int index) throws OutOfBoundException {
        String deleteMsg = tasks.get(index).delete();
        tasks.remove(index);
        return deleteMsg;
    }

    /**
     * Returns the task description if it is successfully added,
     * or return error message if the user input is invalid.
     *
     * @param str user input to add task.
     * @return description of task added or error message.
     */
    public String addTask(Task task) {
        tasks.add(task);
        return task.getTask();
    }

    /**
     * Returns the size of the current task list.
     *
     * @return an integer of task size.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns a list of matching tasks with the input keyword.
     *
     * @param str keyword input by user.
     * @return a list of matching tasks in the current list.
     */
//    public String findTask(String str) {
//        String[] splitted = str.split(" ", 2);
//        String keyword = splitted[1].trim();
//        int count = 1;
//        String result = "Here are the matching tasks in your list:";
//        for (Task t : tasks) {
//            if (t.getTask().contains(keyword)) {
//                result += "\n" + count + "." + t.getTask();
//                count += 1;
//            }
//        }
//        return result;
//    }

    public String getTask(int index) {
        return tasks.get(index).getTask();
    }
}
