package Duke.Main;

import Duke.Task.*;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private final List<Task> taskList;

    /**
     * Constructor for TaskList
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * return the TaskList itself
     * @return task list
     */
    public List<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Retrieve the size of the Task List in form of a String
     * @return size of task list
     */
    public String size() {
        int size = taskList.size();
        if (size < 2) {
            return "Now you have " + size + " task in your list";
        } else {
            return "Now you have " + size + " tasks in your list";
        }
    }

    /**
     * Print all available tasks inside the task list out
     * @return all tasks printed
     */
    public String printList() {
        StringBuilder output = new StringBuilder("Here is the list of all tasks: \n");
        for (int i = 0; i < taskList.size(); i++) {
            output.append(i + 1).append(". ").append(taskList.get(i)).append("\n");
        }
        return output.toString();
    }

    /**
     * Indicate that a task in the list is finished
     * @param index index number of the queried task
     * @return confirmation that the task is completed
     * @throws DukeException if index is not within task size
     */
    public String done(int index) {
        if (index > taskList.size() || index <= 0) {
            throw new DukeException("", DukeException.Type.OUT_OF_BOUND);
        } else {
            index--;
            Task calledTask = taskList.remove(index);
            calledTask.markAsCompleted();
            taskList.add(index, calledTask);
            return "Nice! I've marked this task as done: \n" + calledTask;
        }
    }

    /**
     * Indicate that all tasks are completed
     * @return confirmation that all tasks are completed
     */
    public String doneAll() {
        for (Task task : taskList) {
            task.markAsCompleted();
        }
        return "Nice! I've marked all tasks in your list as done!";
    }

    /**
     * Delete a specified task on the task list
     * @param index index of the task queried from the list
     * @return Confirmation that the specified task is deleted from the list
     * @throws DukeException if index is not within task size
     */
    public String delete(int index) {
        if (index > taskList.size() || index <= 0) {
            throw new DukeException("Error", DukeException.Type.OUT_OF_BOUND);
        } else {
            index--;
            Task deletedTask = taskList.remove(index);
            return "Noted. I've removed this task:\n" + deletedTask + "\n" + size();
        }
    }

    /**
     * Delete all tasks and reset the Task List to empty
     * @return Confirmation that the Task List is reset to empty
     */
    public String deleteAll() {
        taskList.clear();
        return "Noted. I've reset your list and remove all tasks";
    }

    /**
     * Add a particular task into the list
     * @param task details of the task being added
     * @param type type of task being added (TODO, DEADLINE, or EVENT)
     * @return Confirmation that a task is added
     * @throws DukeException if the task type is not recognizable
     */
    public String addTask(String task, Task.Type type) {
        task = task.trim();
        Task newTask;
        switch (type) {
        case TODO:
            newTask = new Todo(task);
            break;
        case DEADLINE:
            newTask = new Deadline(task);
            break;
        case EVENT:
            newTask = new Event(task);
            break;
        default:
            throw new DukeException("Error: ", DukeException.Type.SYNTAX_ERROR);
        }
        this.taskList.add(newTask);
        return "Got it! I've added this task:\n" + newTask +
                "\n" + size();
    }

    /**
     * Add a Task object to the list (for shortcutting purposes)
     * @param task the task being added
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public String find(String query) {
        StringBuilder output = new StringBuilder("Here is the list of tasks that match query '" +
                query + "':\n");
        int count = 0;
        for (Task currentTask : taskList) {
            if (currentTask.getTaskName().contains(query)) {
                output.append(count + 1).append(". ").append(currentTask).append("\n");
                count++;
            }
        }
        if (count == 0) {
            return "OOPS! No task has a description that matches your query!";
        } else {
            return output.toString();
        }
    }
}
