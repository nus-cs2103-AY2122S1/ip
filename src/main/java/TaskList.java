import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructor of the TaskList class
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
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

    protected void markTaskDone(String id) throws UnableToParseException, InvalidTaskSelectedException {
        int index = Parser.parseTaskId(id) - 1;
        if (index < 0 || index >= this.tasks.size()) {
            throw new InvalidTaskSelectedException();
        }
        Task task = this.tasks.get(index);
        task.markAsDone();

        Duke.print("Nice! I've marked this task as done:");
        System.out.println("\t" + task);
    }

    protected void addTodo(String description) {
        Todo todo = new Todo(description);
        this.tasks.add(todo);

        printTaskAdded(todo);
        printNumOfTasks();
    }

    protected void addDeadline(String args) throws InvalidArgumentsException {
        String[] splitArgs = Parser.parseDeadline(args);
        Deadline deadline = new Deadline(splitArgs[0], splitArgs[1]);
        this.tasks.add(deadline);

        printTaskAdded(deadline);
        printNumOfTasks();
    }

    protected void addEvent(String args) throws InvalidArgumentsException {
        String[] splitArgs = Parser.parseEvent(args);
        Event event = new Event(splitArgs[0], splitArgs[1]);
        this.tasks.add(event);

        printTaskAdded(event);
        printNumOfTasks();
    }

    protected void printAllTasks() throws NoTasksException {
        if (tasks.size() == 0) {
            throw new NoTasksException();
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
