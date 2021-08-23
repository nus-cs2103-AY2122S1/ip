package stubs;

import petal.components.TaskList;

public class TaskListStub extends TaskList {

    @Override
    public String printList() {
        return "No items in list yet!";
    }

    @Override
    public void markTaskAsDone(String indexOfTask) {
        System.out.println("This task was marked as done!");
    }

    @Override
    public void deleteTask(String index) {
        System.out.println("Okay, I have deleted this task");
    }
}
