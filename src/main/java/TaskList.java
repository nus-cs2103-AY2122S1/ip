import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructor of the TaskList class.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor of the TaskList class, initialized with existing tasks.
     * @param tasks the tasks to be initialized with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    protected void delete(String id) throws UnableToParseException, InvalidTaskSelectedException {
        int index = Parser.parseTaskId(id) - 1;
        if (index < 0 || index >= this.tasks.size()) {
            throw new InvalidTaskSelectedException();
        }
        Task task = this.tasks.remove(index);

        Duke.print("Noted. I've removed this task:");
        System.out.println("\t" + task);
        printNumOfTasks();
    }
    
    protected void markTaskDone(String id) throws UnableToParseException, InvalidTaskSelectedException, IOException {
        int index = Parser.parseTaskId(id) - 1;
        if (index < 0 || index >= this.tasks.size()) {
            throw new InvalidTaskSelectedException();
        }
        Task task = this.tasks.get(index);
        task.markAsDone();
        task.updateToFile(index); // persist to file
        
        Duke.print("Nice! I've marked this task as done:");
        System.out.println("\t" + task);
    }

    protected void addTodo(String description) throws IOException {
        Todo todo = new Todo(description);
        this.tasks.add(todo);
        todo.appendToFile();
        
        printTaskAdded(todo);
        printNumOfTasks();
    }

    protected void addDeadline(String args) throws InvalidArgumentsException, IOException {
        Deadline deadline = Parser.parseDeadline(args);
        this.tasks.add(deadline);
        deadline.appendToFile();

        printTaskAdded(deadline);
        printNumOfTasks();
    }

    protected void addEvent(String args) throws InvalidArgumentsException, IOException {
        Event event = Parser.parseEvent(args);
        this.tasks.add(event);
        event.appendToFile();

        printTaskAdded(event);
        printNumOfTasks();
    }

    protected void printAllTasks() {
        if (tasks.size() == 0) {
            Duke.print("You have no tasks in your list.");
            return;
        }

        Duke.print("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + ((i + 1) + ". " + tasks.get(i)));
        }
    }

    private static void printTaskAdded(Task task) {
        Duke.print("Got it. I've added this task:");
        System.out.println("\t" + task);
    }

    private void printNumOfTasks() {
        String numOfTasks = this.tasks.size() + " task" + (this.tasks.size() != 1 ? "s" : "");
        Duke.print("Now you have " + numOfTasks + " in the list.");
    }
}
