import java.util.ArrayList;
import java.util.List;

public class TodoList {
    private final List<Todo> todoArr;

    public TodoList() {
        this.todoArr = new ArrayList<>();
    }

    public String addTodo(String text) {
        Todo todo = new Todo(text);
        this.todoArr.add(todo);
        return String.format("added: %s", todo);
    }

    public String markTodoAsDone(int todoIndex) {
        // Task index starts from 1
        int index = todoIndex - 1;
        Todo todo = todoArr.get(index);
        todo.markDone();
        return todo.toString();
    }

    @Override
    public String toString() {
        StringBuilder printedList = new StringBuilder();
        for (int i = 0; i < todoArr.size(); i++) {
            // Index from 1 onwards
            String index = Integer.toString(i + 1);
            printedList.append(String.format("%s. %s\n", index, this.todoArr.get(i)));
        }
        // Remove the last newline
        printedList.delete(printedList.length() - 1, printedList.length());
        return printedList.toString();
    }

}
