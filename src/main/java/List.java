import java.util.ArrayList;
import static java.lang.Integer.parseInt;

public class List extends ArrayList<Item> {
    public static ArrayList<Item> todos;

    public List() {
        todos = new ArrayList<>();
    }

    public void addTask(String input) {
        if (input.equals("list")) {
            showList();
        } else if (input.startsWith("done ")) {
            int index = parseInt(input.substring(5));
            done(index);
        } else {
            todos.add(new Item(input));
            System.out.println("added: " + input);
        }
    }

    public void showList() {
        for (int i = 0; i < todos.size(); i++) {
            System.out.println(i + 1 + ". " + todos.get(i).toString());
        }
    }

    public void done(int index) {
        Item temp = todos.get(index - 1);
        temp.markAsDone();
        System.out.println("Nice! I've marked this task as done: \n" + temp);
    }
}
