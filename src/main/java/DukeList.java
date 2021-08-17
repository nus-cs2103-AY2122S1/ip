import java.util.ArrayList;

public class DukeList {
    private ArrayList<Task> list = new ArrayList<>();
    private int count = 0;

    public DukeList() {}


    private void displayTask(Task task) {
        String response = "Got it. I've added this task:\n";
        String taskCount = "\nNow you have " + list.size() + " tasks in the list.";
        System.out.println(response + task.toString() + taskCount);
    }


    public void add(String text) {
        list.add(new Task(text));
        count += 1;
        System.out.println("added: " + text);
    }


    public void addTodo(String text) throws DukeException {
        String message = text.trim();

        if (message.equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }

        ToDos input = new ToDos(message);
        list.add(input);
        count += 1;

        displayTask(input);
    }


    public void addDeadlines(String text) {
        String[] strings = text.split(" /by ", 2);

        String limit = strings.length == 1 ? "" : strings[1];

        Deadlines input = new Deadlines(strings[0].trim(), limit);
        list.add(input);
        count += 1;

        displayTask(input);
    }

    
    public void addEvents(String text) {
        String[] strings = text.split(" /at ", 2);

        String limit = strings.length == 1 ? "" : strings[1];

        Events input = new Events(strings[0].trim(), limit);
        list.add(input);
        count += 1;

        displayTask(input);
    }


    public void list() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + "." + list.get(i).toString());
        }
    }


    public void done(int item) {
        Task task = list.get(item - 1);
        task.done();

        String response = "Nice! I've marked this task as done:\n";
        System.out.println(response + task.toString());
    }


    public void delete(int item) {
        Task task = list.get(item - 1);
        list.remove(item - 1);

        String response = "Noted. I've removed this task:\n";
        String taskCount = "\nNow you have " + list.size() + " tasks in the list.";
        System.out.println(response + task.toString() + taskCount);
    }


}
