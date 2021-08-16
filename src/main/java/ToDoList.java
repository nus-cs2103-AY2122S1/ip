import java.util.ArrayList;

public class ToDoList {
    private static ArrayList<Task> todoList;

    public ToDoList () {
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

}
