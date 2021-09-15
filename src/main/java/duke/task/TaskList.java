package duke.task;

import duke.exception.DukeException;
import duke.exception.TaskNotFoundException;

import java.time.LocalDateTime;
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
            String display = "Noted. I've removed this task:\n  " + deletedTask.displayTask();
            tasks.remove(item - 1);
            return display + "\n Now you have " + tasks.size() + " tasks in the list.";
        }
    }

    /**
     * Provides the Prompt to update task
     *
     * @return a String of how to update task information
     */
    public String updatePrompt() {
        return "To update Task Name input:\n  edit-N/(Task Index) (New Task Name)\n"
                + "\nTo update Task Duration input:\n  edit-D/(Task Index) (New Task Date and Time)";
    }

    /**
     * Updates the Name of the specified task
     *
     * @param index The index of the task
     * @param newData The new name of the task
     * @return a String to show that the task has been updated
     * @throws DukeException if the task cannot be found
     */
    public String updateTaskName(int index, String newData) throws DukeException {
        if (index > tasks.size() || index <= 0) {
            throw new TaskNotFoundException("☹ OH NO!!! The task cannot be found. \n   Please try again.");
        } else {
            Task taskToUpdate = tasks.get(index - 1);
            taskToUpdate.setTaskName(newData);
            taskToUpdate.uncheckTask();
            return "Got it. I've updated this task:\n "+ taskToUpdate.displayTask();
        }
    }

    /**
     * Updates the Date and Time of the task
     *
     * @param index The index of the task
     * @param newData The new date and time of the task
     * @return a String to show that the task has been updated
     * @throws DukeException if the task cannot be found
     */
    public String updateTaskDuration(int index, LocalDateTime newData) throws DukeException {
        if (index > tasks.size() || index <= 0) {
            throw new TaskNotFoundException("☹ OH NO!!! The task cannot be found. \n   Please try again.");
        } else {
            Task taskToUpdate = tasks.get(index- 1);
            if (taskToUpdate instanceof Deadline) {
                ((Deadline) taskToUpdate).setDuration(newData);
            } else if (taskToUpdate instanceof Event) {
                ((Event) taskToUpdate).setDuration(newData);
            } else {
                throw new DukeException("This Task type does not have a Duration tied to it!");
            }
            taskToUpdate.uncheckTask();
            return "Got it. I've updated this task:\n "+ taskToUpdate.displayTask();
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
     * Gets the index of the Task
     *
     * @param message the input command to check a task
     * @return the index for the task in the list
     */
    public int getTaskIndex(String message) {
        StringBuilder number;
        if (message.startsWith("done") && message.length() > 5) {
            char firstNumber = message.charAt(5);
            number = new StringBuilder(Character.toString(firstNumber));
            int counter = 6;
            while (counter < message.length()) {
                char next = message.charAt(counter);
                number.append(next);
                counter++;
            }
            return Integer.parseInt(number.toString());

        } else if (message.startsWith("edit") && message.length() > 7) {
            char firstNumber = message.charAt(7);
            number = new StringBuilder(Character.toString(firstNumber));
            int len = message.substring(0, message.indexOf(" ")).length();
            int counter = 8;
            while (counter < len) {
                char next = message.charAt(counter);
                number.append(next);
                counter++;
            }
            return Integer.parseInt(number.toString());
        } else {
            return 0;
        }
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
