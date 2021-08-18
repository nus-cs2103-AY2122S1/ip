import java.util.*;

public class ToDoList {
    List<ToDo> toDoList = new ArrayList<>();

    public void addItem(String description) {
        ToDo t = new ToDo(description);
        toDoList.add(t);
        System.out.println("added: " + t.getDescription());
    }

    public void showList() {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 1; i <= toDoList.size(); i++) {
            ToDo t = getToDo(i - 1);
            System.out.println(i + ".[" + t.getStatusIcon() + "] " + t.getDescription());
        }
    }

    public ToDo getToDo(int index) {
        return toDoList.get(index);
    }

    public void markAsDone(int index) {
        ToDo t = getToDo(index);
        t.setDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("    [" + t.getStatusIcon() + "] " + t.getDescription());
    }
}
