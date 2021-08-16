import java.util.ArrayList;

public class List extends ArrayList<Item> {
    public static ArrayList<Item> todos;

    public List() {
        todos = new ArrayList<>();
    }

    public void addTask(String input) {
        if (input.equals("list")) {
            for (int i = 0; i < todos.size(); i++) {
                System.out.println(i + 1 + ". " + todos.get(i).getName());
            }
        } else {
            todos.add(new Item(input));
            System.out.println("added: " + input);
        }
    }
}
