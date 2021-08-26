package duke.task;

import duke.storage.Storage;
import java.util.ArrayList;
public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    private static Integer getTaskNumber(String input) {
        String arr[] = input.split(" ", 2);
        Integer taskNumber = Integer.parseInt(arr[1]);
        return taskNumber;
    }

    public void markTaskDone(String input) {
        try {
            int taskDoneNum = getTaskNumber(input);
            Task taskDone = tasks.get(taskDoneNum - 1);
            taskDone.markAsDone();
            System.out.println("Nice! I've marked this task as done:" + '\n' + taskDone.toString());
            Storage.saveData(this);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! No such task can be marked as done!");
        }
    }

    public void deleteTask(String input) {
        try {
            int taskDeleteNum = getTaskNumber(input);
            Task taskToDelete = tasks.get(taskDeleteNum - 1);
            taskToDelete.markUndone();
            System.out.println("Noted. I've removed this task:" + '\n' + taskToDelete.toString());
            tasks.remove(taskDeleteNum - 1);
            printTaskNumber(this);
            Storage.saveData(this);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! No such task can be deleted!");
        }
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    public static void printItemList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + "." + tasks.get(i).toString());
        }
    }
    public void printTaskNumber(TaskList tasks) {
        System.out.println("Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in the list.");
    }

}
