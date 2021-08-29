package duke;

import duke.tasks.Task;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    
    public TaskList() {
        tasks = new ArrayList<>();
    }
    
    public void printTaskList() {
        if (tasks.size() == 0) {
            System.out.println("There are no tasks in the list.");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
    
    protected int getLength() {
        return tasks.size();
    }
    
    public void addNewTask(Task task) {
        tasks.add(task);
    }
    
    public Task markTaskDone(int taskIndex) throws DukeException {
        validateTaskIndex(taskIndex);
        Task task = tasks.get(taskIndex);
        task.markAsDone();
        return task;
    }

    public Task deleteTask(int taskIndex) throws DukeException {
        validateTaskIndex(taskIndex);
        Task task = tasks.get(taskIndex);
        tasks.remove(task);
        return task;
    }

    public ArrayList<String> getTaskStrings() {
        ArrayList<String> taskStrings = new ArrayList<>();
        for (Task task : tasks) {
            taskStrings.add(task.toFileString());
        }
        return taskStrings;
    }
    
    private void validateTaskIndex(int taskIndex) throws DukeException { 
        int taskCount = tasks.size();
        if (taskCount == 0) {
            throw new DukeException("There are no tasks in the list.");
        } else if (taskIndex >= taskCount) {
            throw new DukeException("Invalid task number. There are only " + taskCount + "tasks in the list");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TaskList) {
            TaskList other = (TaskList) obj;
            return tasks.equals(other.tasks);
        }
        return false;
    }
}
