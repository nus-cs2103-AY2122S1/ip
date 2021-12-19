package taubot;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * This class represents the task list stored by the chatbot.
 */
public class TaskList {

    /**
     * Contains tasks added by user.
     */
    private final ArrayList<Task> tasks;

    /**
     * Represents the index of the next task to be added by the user.
     */
    private int taskIndex;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>(100);
    }

    /**
     * Adds a new todo to the list
     *
     * @param task The task description to be added.
     *
     */
    public void addTask(String task) {
        this.tasks.add(new Todo(task));
        this.taskIndex++;
    }

    /**
     * Adds task to list.
     * @param task Task to add.
     * @param type Type of task.
     * @param ld LocalDate of task.
     * @param time Time of task.
     */
    public void addTask(String task, Task.TaskType type, LocalDate ld, String time) {
        switch (type) {
        case EVENT:
            this.tasks.add(new Event(task, ld, time));
            break;
        case DEADLINE:
            this.tasks.add(new Deadline(task, ld, time));
            break;
        default:
            break;
        }
        this.taskIndex++;
    }

    /**
     * Deletes a task.
     *
     * @param taskIndex Index from list to delete task.
     */
    public void deleteTask(int taskIndex) {
        this.tasks.remove(taskIndex - 1);
        this.taskIndex--;
    }

    /**
     * Lists out the tasks inside the list (if any).
     */
    public String listTasks() {
        if (this.taskIndex == 0) {
            return "No tasks added yet!";
        } else {
            String response = "";
            for (int i = 0; i < this.getTasksLength(); i++) {
                response += (i + 1) + "." + this.tasks.get(i) + "\n";
            }
            return response;
        }
    }

    /**
     * Marks a task as done.
     *
     * @param taskIndex The index of the task.
     */
    public void markTaskDone(int taskIndex) {
        Task doneTask = this.tasks.get(taskIndex - 1);
        doneTask.markAsDone();
    }

    /**
     * Returns task from the list using the index.
     *
     * @param index Index of the task on the list.
     * @return Returns task.
     */
    public Task getTask(int index) {
        return tasks.get(index - 1);
    }

    /**
     * Returns the length of the current task list.
     *
     * @return Length of tasks.
     */
    public int getTasksLength() {
        return this.taskIndex;
    }

    /**
     * Find tasks containing the keyword.
     *
     * @param keyword Keyword to find tasks with
     * @return ArrayList of tasks containing the keyword.
     */
    public ArrayList<Task> findTasksUsingKeyword(String keyword) {
        ArrayList<Task> tasksContainingKeyword = new ArrayList<>(100);
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            String taskDescription = currentTask.getTaskDescription();
            String[] taskDescArray = taskDescription.split(" ");
            for (int j = 0; j < taskDescArray.length; j++) {
                if (taskDescArray[j].equals(keyword)) {
                    tasksContainingKeyword.add(currentTask);
                    break;
                }
            }
        }
        return tasksContainingKeyword;
    }

    /**
     * Finds all the tasks with a date, and filters using the optional date parameter.
     * @param dates Optional argument to filter by a specific date.
     * @return An ArrayList of Task objects that have dates or that date (if date argument passed in).
     */
    public ArrayList<Task> findTasksUsingDate(LocalDate ...dates) {
        ArrayList<Task> tasksWithDate = new ArrayList<>();
        if (tasks.size() == 0) {
            return tasksWithDate;
        }
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            if (dates.length == 0 && (isDeadline(currentTask) || isEvent(currentTask))) {
                tasksWithDate.add(currentTask);
            } else if (isDeadline(currentTask)) {
                LocalDate date = dates[0];
                LocalDate deadlineDate = ((Deadline) currentTask).getDate();
                if (deadlineDate.equals(date)) {
                    tasksWithDate.add(currentTask);
                }
            } else if (isEvent(currentTask)) {
                LocalDate date = dates[0];
                LocalDate eventDate = ((Event) currentTask).getDate();
                if (eventDate.equals(date)) {
                    tasksWithDate.add(currentTask);
                }
            }
        }
        return tasksWithDate;
    }

    public boolean isDeadline(Task task) {
        return task.getType() == Task.TaskType.DEADLINE;
    }
    public boolean isEvent(Task task) {
        return task.getType() == Task.TaskType.EVENT;
    }
}
