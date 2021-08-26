package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> taskList = new ArrayList<>();

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {

    }

    public int getTotalNumberOfTask() {
        return taskList.size();
    }

    public Task getTaskById(int id) {
        return taskList.get(id);
    }

    public void markTaskDoneById(int id) {
        Task task = taskList.get(id);
        task.markDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("\t" + task);
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Search and display item matching the query.
     *
     * @param searchItem The
     */
    public void findAndDisplay(String searchItem) {
        List<Task> searchList = new ArrayList<>();

        for (Task task : taskList) {
            if (task.getValue().contains(searchItem)) {
                searchList.add(task);
            }
        }

        if (searchList.size() > 0) {
            System.out.println("Here are the matching tasks in your list: ");
            for (int i = 0; i < searchList.size(); i++) {
                int index = i + 1;
                System.out.println(index + "." + searchList.get(i));
            }
        } else {
            System.out.println("Sorry there are no items that match your query!");
        }
    }


    public void removeTaskById(int id) {
        taskList.remove(id);
    }
}
