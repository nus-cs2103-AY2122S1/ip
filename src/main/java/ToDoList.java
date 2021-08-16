import java.util.ArrayList;

public class ToDoList {
    private ArrayList<Task> record = new ArrayList<>();
    private Integer totalNumber = 0;

    ToDoList() {
    }

    public void echo(String statement) {
        System.out.println("------------------");
        System.out.println(statement + "\n");
        System.out.println("------------------\n");
    }

    public void populate(String item) {
        Task task = new Task(item);
        this.record.add(task);
        this.totalNumber++;
        System.out.println("------------------");
        System.out.println("added: " + item + "\n");
        System.out.println("------------------\n");
    }

    public void displayList() {
        Integer number = 1;
        System.out.println("------------------");
        for (Task a : this.record) {
            if (a.isCompleted()) {
                System.out.println(number.toString() + "." + a.logo() + "[X] " + a.toString());
            } else {
                System.out.println(number.toString() + "." + a.logo() + "[ ]" + a.toString());
            }
            number++;
        }
        System.out.println("------------------\n");
    }

    public void markAsDone(int num) {
        this.record.get(num-1).setCompleted();
        System.out.println("------------------");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + "[X] " + record.get(num-1).toString());
        System.out.println("------------------\n");
    }

    public void addToDo(String item) {
        ToDo todo = new ToDo(item);
        this.record.add(todo);
        this.totalNumber++;
        System.out.println("------------------");
        System.out.println("Got it. I've added this task:");
        System.out.println("  [T][ ] " + item);
        System.out.println("Now you have " + this.totalNumber.toString() + " in the list");
        System.out.println("------------------");
    }

    public void addEvent(String item, String duration) {
        Event event = new Event(item, duration);
        this.record.add(event);
        this.totalNumber++;
        System.out.println("------------------");
        System.out.println("Got it. I've added this task:");
        System.out.println("  [E][ ] " + item + "(at: " + duration + ")");
        System.out.println("Now you have " + this.totalNumber.toString() + " in the list");
        System.out.println("------------------");
    }

    public void addDeadline(String item, String deadline) {
        Deadline dl = new Deadline(item, deadline);
        this.record.add(dl);
        this.totalNumber++;
        System.out.println("------------------");
        System.out.println("Got it. I've added this task:");
        System.out.println("  [D][ ] " + item + "(by: " + deadline + ")");
        System.out.println("Now you have " + this.totalNumber.toString() + " in the list");
        System.out.println("------------------");
    }
}
