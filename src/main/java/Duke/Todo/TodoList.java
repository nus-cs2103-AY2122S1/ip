package Duke.Todo;

import java.util.ArrayList;
import java.util.List;

public class TodoList {
    private List<Todo> list = new ArrayList<>();

    public void add(Todo newTodo) {
        this.list.add(newTodo);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < this.list.size(); i++) {
           builder.append(String.format("%d. %s\n", i + 1, this.list.get(i).toString()));
        }
        return builder.toString();
    }
}
