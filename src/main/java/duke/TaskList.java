package duke;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

import duke.task.Task;

/**
 * TaskList contains the list of Tasks.
 * It handles display, add, markAsDone and delete operations.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Initialises an empty tasks arraylist.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Initialises the tasks with the tasks from storage.
     *
     * @param tasks the tasks obtained from storage.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a newTask into the taskList.
     *
     * @param newTask the task to be added.
     * @return the response String to be displayed.
     */
    public String add(Task newTask) {
        String addedMessage = "Got it. I've added this task:\n";
        tasks.add(newTask);
        return addedMessage + newTask.toString()
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Marks a current task as done.
     *
     * @param taskNumber the index of the task to be marked.
     * @return the response String to be displayed.
     */
    public String markAsDone(int taskNumber) throws DukeException {
        if (taskNumber > tasks.size()) {
            throw new DukeException("☹ OOPS!!! "
                    + "There isn't a task number " + taskNumber + ".");
        }
        Task t = tasks.get(taskNumber - 1);
        t.setDone();
        return "Nice! I've marked this task as done:\n" + "  " + t.toString();
    }

    /**
     * Deletes a task from the taskList.
     *
     * @param taskNumber the index of the task to be deleted.
     * @return the response String to be displayed.
     */
    public String delete(int taskNumber) throws DukeException {
        if (taskNumber > tasks.size()) {
            throw new DukeException("☹ OOPS!!! "
                    + "There isn't a task number " + taskNumber + ".");
        }
        Task t = tasks.remove(taskNumber - 1);
        return "Noted! I've removed this task:\n" + "  "
                + t.toString() + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Prints the current taskList.
     *
     * @param listString the title of the list to be printed.
     * @return the list of tasks as a String.
     */
    public String display(String listString) {
        for (int i = 0; i < tasks.size(); i++) {
            listString += "\n" + (i + 1) + "." + tasks.get(i).toString();
        }
        return listString;
    }

    /**
     * Returns the tasks arraylist.
     *
     * @return the tasks arraylist.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the size of the tasksList.
     *
     * @return the size of the tasksList.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Prints the list of tasks with description matching the keyWord.
     *
     * @param keyWord the search keyWord to filter the tasks.
     * @return the response to be displayed.
     * @throws DukeException thrown when there is no tasks matching the keyWord.
     */
    public String findTasks(String keyWord) throws DukeException {
        ArrayList<Task> filteredTasks = tasks.stream()
                .filter(t -> t.getDescription().contains(keyWord))
                .collect(Collectors.toCollection(ArrayList::new));
        if (filteredTasks.size() == 0) {
            throw new DukeException("☹ OOPS!!! "
                    + "There isn't a task matching the keyword: "
                    + keyWord);
        } else {
            return new TaskList(filteredTasks).display("I have found "
                    + "these tasks in your list matching the keyword:");
        }
    }

    /**
     * Finds the list of tasks within the period of the reminder.
     *
     * @param period the period of the reminder.
     * @return the list of tasks as a String.
     */
    public String getTasksForReminder(String period) {
        ArrayList<Task> filteredTasks = tasks.stream()
                .filter(t -> filterTask(t, period))
                .collect(Collectors.toCollection(ArrayList::new));
        if (filteredTasks.size() == 0) {
            return "You have no tasks for " + period;
        } else {
            return new TaskList(filteredTasks).display("I have found "
                    + "these tasks in your list for " + period);
        }
    }

    private boolean filterTask(Task task, String period) {
        if (task.hasDate()) {
            switch (period) {
            case "today":
                return task.getDate().isEqual(LocalDate.now());
            case "tomorrow":
                return task.getDate().isEqual(LocalDate.now().plusDays(1));
            case "week":
                return task.getDate().isBefore(LocalDate.now().plusWeeks(1))
                        && task.getDate().isAfter(LocalDate.now().minusDays(1));
            case "month":
                return task.getDate().getMonth().equals(LocalDate.now().getMonth());
            default:
                assert false;
                return false;
            }
        } else {
            return true;
        }
    }
}
