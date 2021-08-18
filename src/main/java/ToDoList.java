import java.util.*;

public class ToDoList {
    List<Task> toDoList = new ArrayList<>();

    public void addItem(String input) {
        Task t;

        if (input.contains("todo")) {
            String description = input.substring(input.indexOf(' ') + 1);
            t = new ToDo(description);
        } else {
            String description = input.substring(input.indexOf(' ') + 1, input.lastIndexOf('/') - 1);
            String time = input.substring(input.lastIndexOf("/") + 4);
            if (input.contains("deadline")) {
                t = new Deadline(description, time);
            } else {
                t = new Event(description, time);
            }
        }

        toDoList.add(t);
        System.out.println("Got it. I've added this task:");
        System.out.println(t.toString());
        System.out.println("Now you have " + toDoList.size() + " tasks in the list.");
    }

    public void showList() {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 1; i <= toDoList.size(); i++) {
            Task t = getToDo(i - 1);
            System.out.println(i + "." + t.toString());
        }
    }

    public Task getToDo(int index) {
        return toDoList.get(index);
    }

    public void markAsDone(int index) {
        Task t = getToDo(index);
        t.setDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(t.toString());
    }
}
