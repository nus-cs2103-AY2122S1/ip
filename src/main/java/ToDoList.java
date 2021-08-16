import java.util.ArrayList;

public class ToDoList {
    private static ArrayList<String> todoList;

    public ToDoList () {
        todoList = new ArrayList<>();
    }

    public static ArrayList<String> getTodoList () {
        return todoList;
    }

    public void add (String task) {
        todoList.add(task);
    }


}
