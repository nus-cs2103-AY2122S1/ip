import java.util.*;

public class ToDoList {
    List<String> toDoList = new ArrayList<>();

    public void addItem(String item) {
        toDoList.add(item);
        System.out.println("added: " + item);
    }

    public void showList() {
        for (int i = 1; i <= toDoList.size(); i++) {
            System.out.println(i + ". " + toDoList.get(i - 1));
        }
    }
}
