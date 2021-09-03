package duke.task;

import duke.exception.TaskNotFoundException;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructor of a list of tasks
     *
     * @param tasks ArrayList containing all task input
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets the list of tasks
     *
     * @return ArrayList of all the tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds task to the list
     *
     * @param task new input task
     */
    public String addTask(Task task) {
        tasks.add(task);
        return "Got it. I've added this task:\n "+ task.displayTask()
                + "\n Now you have " + tasks.size() +  " tasks in the list.";
    }

    /**
     * Deletes current task from list
     *
     * @param item the list index of the task to be deleted
     * @throws TaskNotFoundException if the input index is not valid
     */
    public String deleteTask(int item) throws TaskNotFoundException {
        if (item > tasks.size() || item <= 0) {
            throw new TaskNotFoundException("☹ OH NO!!! The task cannot be found. \n   Please try again.");
        } else {
            Task deletedTask = tasks.get(item - 1);
            tasks.remove(item);
            String display = "Noted. I've removed this task:\n  " + deletedTask.displayTask();
            return display + "\n Now you have " + tasks.size() + " tasks in the list.";
        }
    }

    /**
     * Finds all tasks that contains the input keyword
     *
     * @param keyword the input String to search for
     */
    public String findTask(String keyword) {
        int order = 1;
        int numOfMatchingTasks = 0;
        StringBuilder tasksToPrint = new StringBuilder("Here are the matching tasks in your list: \n");
        if (tasks.size() == 0) {
            return "No tasks in your list.";
        } else {
            for (Task t : tasks) {
                if (t.getTaskName().contains(keyword)) {
                    String matchingTask = order++ +"." + t.displayTask() + "\n";
                    tasksToPrint.append(matchingTask);
                    numOfMatchingTasks++;
                }
            }
            if (numOfMatchingTasks == 0) {
                return "No matching tasks found.";
            } else {
                return tasksToPrint.toString();
            }
        }
    }

    /**
     * Gets the index of the Task to check
     *
     * @param message the input command to check a task
     * @return the list index for the task to be checked
     */
    public int taskToCheck(String message) {
        StringBuilder number;
        if (message.length() > 5) {
            String check = message.substring(0, 4);
            if (check.equals("done")) {
                char firstNumber = message.charAt(5);
                number = new StringBuilder(Character.toString(firstNumber));
                int counter = 6;
                while (counter < message.length()) {
                    char next = message.charAt(counter);
                    number.append(next);
                    counter++;
                }
                return Integer.parseInt(number.toString());
            }
        }
        return 0;
    }

    /**
     * Mark the task as completed
     *
     * @param item the list index of the task to be mark as done
     * @throws TaskNotFoundException if the list index is not valid
     */
    public String markAsCheckedTask(int item) throws TaskNotFoundException {
        if (item > tasks.size()) {
            throw new TaskNotFoundException("☹ OH NO!!! The task cannot be found. \n   Please try again.");
        } else {
            Task t = tasks.get(item -1);
            t.checkOffTask();
            return "Nice! I've marked this task as done:\n  " + t.displayTask();
        }
    }

    /**
     *  Gets the index of the Task to delete
     *
     * @param message the input command to delete a task
     * @return the list index for the task to be checked
     */
    public int taskToDelete(String message) {
        StringBuilder number;
        if (message.length() > 6) {
            String check = message.substring(0, 6);
            if (check.equals("delete")) {
                char firstNumber = message.charAt(7);
                number = new StringBuilder(Character.toString(firstNumber));
                int counter = 8;
                while (counter < message.length()) {
                    char next = message.charAt(counter);
                    number.append(next);
                    counter++;
                }
                return Integer.parseInt(number.toString());
            }
        }
        return 0;
    }

    /**
     * Prints all the tasks that are in the ArrayList
     */
    public String listTasks() {
        if (this.tasks.size() == 0) {
            return "You have no tasks.";
        } else {
            int order = 1;
            StringBuilder listOfTasks = new StringBuilder("Here are the tasks in your list:\n");
            for (Task s : tasks) {
                listOfTasks.append(order++).append(".").append(s.displayTask()).append("\n");
            }
            return listOfTasks.toString();
        }
    }
}
