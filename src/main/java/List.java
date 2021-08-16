import java.util.ArrayList;
import static java.lang.Integer.parseInt;

public class List extends ArrayList<Task> {
    public static ArrayList<Task> todos;

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
            process(input);
        }
    }

    public void showList() {
        for (int i = 0; i < todos.size(); i++) {
            System.out.println(i + 1 + ". " + todos.get(i).toString());
        }
    }

    public void done(int index) {
        Task temp = todos.get(index - 1);
        temp.markAsDone();
        System.out.println("Nice! I've marked this task as done: \n" + temp);
    }

    public void process(String input) {
        if (input.startsWith("todo ")) {
            Task newItem = new Todo(input.substring(5));
            todos.add(newItem);
            echo(newItem);
        } else if (input.startsWith("deadline ")) {
            Task newItem = new Deadline(input.substring(9));
            todos.add(newItem);
            echo(newItem);
        } else if (input.startsWith("event ")) {
            Task newItem = new Event(input.substring(6));
            todos.add(newItem);
            echo(newItem);
        }
    }

    public void echo(Task item) {
        System.out.println("Got it. I've added this task: \n"
                + item
                + "\nNow you have "
                + todos.size()
                + " task"
                + (todos.size() == 1 ? "" : "s")
                + " in the list"); // will take care of when it is 1
    }

    public void echo(String input) {
        System.out.println(input);
    }
}
