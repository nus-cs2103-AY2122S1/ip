package stubs;

import java.util.ArrayList;
import java.util.List;

import petal.components.Responses;
import petal.components.TaskList;
import petal.components.Ui;
import petal.task.Task;

public class TaskListStub extends TaskList {

    private List<TaskStub> taskList = new ArrayList<>();
    private Ui ui;

    /**
     * Constructor for the TaskListStub class
     * @param ui The Ui instance
     */
    public TaskListStub(Ui ui) {
        super(ui);
        this.ui = ui;
        taskList.add(new TaskStub());
    }

    @Override
    public String printList() {
        return "No items in list yet!";
    }

    @Override
    public void markTaskAsDone(String indexOfTask) {
        TaskStub taskStub = taskList.get(0);
        taskStub.taskDone();
        System.out.println(Responses.LINE + "\nYou have completed the task: " + "'"
                                          + "run!"
                                          + "\nI am so happy for you!\n"
                                          + Responses.LINE);
    }

    @Override
    public void deleteTask(String index) {
        TaskStub toBeDeleted = new TaskStub();
        System.out.println("Okay. I've deleted this task:\n" + toBeDeleted + "\nYou now have 1 task(s)!");
    }

    @Override
    public void addTask(Task task) {
        System.out.println("Okay. I've added this task:\n" + task + "\nYou now have 1 task!");
    }
}
