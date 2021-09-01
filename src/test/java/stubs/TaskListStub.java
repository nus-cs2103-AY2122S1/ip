package stubs;

import java.util.ArrayList;
import java.util.List;

import petal.components.TaskList;
import petal.task.Task;

public class TaskListStub extends TaskList {

    private List<TaskStub> tasks = new ArrayList<>();

    /**
     * Constructor for the TaskListStub class
     */
    public TaskListStub() {
        tasks.add(new TaskStub());
    }

    @Override
    public String printList() {
        return "1. [T][ ] Go for a run";
    }

    @Override
    public String markTaskAsDone(String indexOfTask) {
        TaskStub taskStub = tasks.get(0);
        taskStub.taskDone();
        return "\nYou have completed the task: "
                + "'"
                + "go for a run" + "'!"
                + "\nI am so happy for you!\n";
    }

    @Override
    public String deleteTask(String index) {
        TaskStub toBeDeleted = new TaskStub();
        return "Okay. I've deleted this task:\n" + toBeDeleted + "\nYou now have 1 task(s)!";
    }

    @Override
    public String addTask(Task task) {
        return "Okay. I've added this task:\n" + task + "\nYou now have " + tasks.size() + "task!";
    }

    @Override
    public String handleTask(String type, String message) {
        Task task = new TaskStub();
        return addTask(task);
    }

    @Override
    public String findTaskWithKeyword(String keyword) {
        return "No tasks!";
    }

    @Override
    public String showTaskOnDate(String date) {
        return "No tasks on this date!";
    }

}
