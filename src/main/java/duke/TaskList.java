package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    
    private ArrayList<Task> tasks;
    
    public TaskList() {
        tasks = new ArrayList<>();
    }
    
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    
    public String addTask(Task newTask) {
        tasks.add(newTask);
        return "Got it. I've added this task:\n" 
                + "  " + newTask + "\n" 
                + numOfTasks();
    }
    
    public String markDone(int taskIndex) {
        Task currTask = tasks.get(taskIndex - 1);
        currTask.markDone();
        return "Nice! I've marked this task as done:\n"
                + "  " + taskIndex + ". " + currTask;
    }

    public String deleteTask(int taskIndex) {
        Task currTask = tasks.get(taskIndex - 1);
        tasks.remove(taskIndex - 1);
        return "Noted. I've removed this task:\n"
                + "  " + taskIndex + ". " + currTask + "\n"
                + numOfTasks();
    }

    public ArrayList<Task> getList() {
        return tasks;
    }
    
    public String getTaskStorage(int taskIndex) {
        return tasks.get(taskIndex - 1).toStorage();    
    }
    
    public boolean isIndexValid(int taskIndex) {
        return ((taskIndex - 1) < tasks.size() && (taskIndex - 1) > -1);
    }

    public ArrayList<Task> findTasks(String content) {
        ArrayList<Task> findTasksResult = new ArrayList<>();

        for (Task currTask: tasks) {
            if (currTask.contains(content)) {
                findTasksResult.add(currTask);
            }
        }

        return findTasksResult;
    }
    
    private String numOfTasks() {
        if (tasks.size() > 0) {
            return "Now you have " + tasks.size() + " tasks in the list.";
        } else {
            return "There are no tasks in your list.";
        }
    }
}
