import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> items;
    private static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");

    public TaskList(List<Task> items) {
        this.items = items;
    }

    public Task getTask(int index) {
        return items.get(index);
    }

    public void markTaskDone(int index) {
        items.get(index).markAsDone();
    }

    public void addTodo(String userCommand) {
        Todo newTodo = new Todo(userCommand.substring(5));
        items.add(newTodo);
    }


//    public void addTodo(String[] task) {
//        items.add(new Todo(task[2], task[1].equals("1")));
//    }
//
//    public void addDeadline(String[] task) {
//        items.add(new Event(task[2], task[1].equals("1"), LocalDateTime.parse(task[3], outputFormatter)));
//    }
//
//    public void addEvent(String[] task) {
//        items.add(new Deadline(task[2], task[1].equals("1"), LocalDateTime.parse(task[3], outputFormatter)));
//    }

    /**
     * Prints the list of tasks the user has currently.
     */
    public void printList() {
        if (items.size() > 0) {
            System.out.println("Here are the tasks in your list:");

            for (int i = 0; i < items.size(); i++) {
                System.out.printf("%d. " + items.get(i) + "\n", i + 1);
            }
        } else {
            System.out.println("You have no tasks in your list.");
        }

    }
}
