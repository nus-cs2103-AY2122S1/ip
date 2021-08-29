package duke;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> todoList;

    public TaskList() {
        todoList = new ArrayList<>();
    }

    public static ArrayList<Task> getTodoList () {
        return todoList;
    }

    public void add (Task task) {
        todoList.add(task);
    }

    public Task getTask (int index) {
        return todoList.get(index - 1);
    }

    public void removeTask (int index) {
        todoList.remove(index - 1);
    }

}
