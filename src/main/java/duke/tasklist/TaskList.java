package duke.tasklist;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidNumberInputException;
import duke.tasks.Task;

/**
 * The TaskList is an abstraction for a list of tasks
 */
public class TaskList {

    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public Task get(int i) {
        try {
            return taskList.get(i);
        } catch (IndexOutOfBoundsException ex) {
            throw new InvalidNumberInputException();
        }
    }

    public int size() {
        return taskList.size();
    }

    /**
     * Prints the list of tasks
     *
     * @return list of tasks as a string
     */
    public String print() {
        boolean allTasksDone = true;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < this.size(); i++) {
            if (!this.get(i).getStatus()) {
                allTasksDone = false;
            }
            String taskString = this.get(i).toString();
            stringBuilder.append("\n").append(i+1).append(". ").append(taskString);
        }

        return  stringBuilder.toString() + taskDoneMessage(allTasksDone);
    }

    public String taskDoneMessage(boolean allTasksDone){
        if (allTasksDone) {
            return "\nAll tasks are complete!!";
        } else {
            return "";
        }
    }

    public Task remove(int i) {
        assert !taskList.isEmpty() : new DukeException("The task list is empty");
        return taskList.remove(i);
    }

    public String printRemainingTasks() {
        if (taskList.size() == 1) {
            return "Now you have 1 task in the list.";
        } else {
            return "Now you have "+ taskList.size() +" tasks in the list.\n";
        }
    }


    public void add(Task task) {
        taskList.add(task);
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    public void set(int i, Task task) {
        taskList.set(i, task);
    }
    /**
     * Checks if a list of tasks contains the given task
     *
     * @param task task to be checked
     * @return boolean value of whether the task is contained in the list
     */
    public boolean containsTask(Task task) {
        boolean ans = false;
        for (Task value : taskList) {
            if (value.getDetails().trim().equals(task.getDetails().trim())) {
                ans = true;
                break;
            }
        }
        return ans;
    }
}

