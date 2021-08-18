import java.util.ArrayList;
import java.util.List;

public class TodoList {
    private final List<Todo> todoArr;

    public TodoList() {
        this.todoArr = new ArrayList<>();
    }

    public String addTodo(Todo todo) {
        this.todoArr.add(todo);
        return String.format("added: %s", todo);
    }

    @Override
    public String toString() {
        StringBuilder printedList = new StringBuilder();
        for (int i = 0; i < todoArr.size(); i++) {
            String index = Integer.toString(i + 1);
            printedList.append(String.format("%s. %s\n", index, this.todoArr.get(i)));
        }
        return printedList.toString();
    }

}
