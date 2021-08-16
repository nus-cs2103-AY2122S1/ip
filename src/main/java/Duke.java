import java.util.ArrayList;
import java.util.List;

public class Duke {
    private List<Task> tasks = new ArrayList<Task>();

    /**
     * Greet the user.
     */
    public void greet() {
        String greet = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(greet);
    }

    /**
     * exit behavior.
     */
    public void bye() {
        System.out.println("___________________________________________________");
        String byeCommand = "Bye. Hope to see you again soon!";
        System.out.println(byeCommand);
        System.out.println("___________________________________________________\n");
    }

    /**
     * display a list of tasks added.
     */
    public void displayTasks() {
        System.out.println("___________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
        System.out.println("___________________________________________________\n");
    }

    /**
     * Process the command input by user.
     *
     * @param command command input of the user.
     */
    public void processCommand(String command) throws DukeException {
        String action = this.getAction(command);
        switch (action) {
            case "list":
                this.displayTasks();
                break;
            case "todo": {
                String task = this.getTask(command);
                ToDo toDo = this.createTodo(task);
                this.addTask(toDo);
                break;
            }
            case "deadline": {
                String task = this.getTask(command);
                DeadLine ddl = this.createDeadLine(task);
                this.addTask(ddl);
                break;
            }
            case "event": {
                String task = this.getTask(command);
                Event event = this.createEvent(task);
                this.addTask(event);
                break;
            }
            case "done": {
                String task = this.getTask(command);
                int taskIdx = this.getTaskIdx(task);
                this.markTaskAsDone(taskIdx - 1);
                break;
            }
            default:
                throw new UnrecognizableCommandException();
        }
    }

    private void addTask(Task task) {
        this.tasks.add(task);
        String output = "Added: " + task.toString();
        System.out.println("___________________________________________________");
        System.out.println(output);
        System.out.println("Now you have " + this.tasks.size() + " task" + ((tasks.size() <= 1) ? "" : "s") + " in the list");
        System.out.println("___________________________________________________\n");
    }

    private void markTaskAsDone(int taskIdx) {
        Task task = this.tasks.get(taskIdx);
        task.markAsDone();
        System.out.println("___________________________________________________");
        System.out.println(" Nice! I've marked this task as done:\n" + task);
        System.out.println("___________________________________________________\n");
    }

    private String getAction(String command) {
        return command.split(" ")[0];
    }

    private String getTask(String command) throws EmptyDescriptionException {
        String action = this.getAction(command);
        try {
            return command.substring(action.length() + 1);
        } catch (StringIndexOutOfBoundsException e) {
            throw new EmptyDescriptionException(action);
        }

    }

    private ToDo createTodo(String command) {
        return new ToDo(command);
    }

    private DeadLine createDeadLine(String command) throws UnrecognizableCommandException {
        if (!command.contains("/by ")) {
            throw new UnrecognizableCommandException();
        }
        try {
            String[] lst = command.split("/by ");
            String description = lst[0];
            String ddl = lst[1];
            return new DeadLine(description, ddl);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new UnrecognizableCommandException();
        }

    }

    private Event createEvent(String command) throws UnrecognizableCommandException {
        if (!command.contains("/at ")) {
            throw new UnrecognizableCommandException();
        }
        try {
            String[] lst = command.split("/at ");
            String description = lst[0];
            String period = lst[1];
            return new Event(description, period);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw  new UnrecognizableCommandException();
        }
    }

    private int getTaskIdx(String idxString) throws InvalidTaskIndexException, NoSuchTaskException {
        int taskIdx;
        try {
             taskIdx = Integer.parseInt(idxString);
        } catch (NumberFormatException e) {
            throw new InvalidTaskIndexException();
        }

        if (taskIdx > 0 && taskIdx <= this.tasks.size()) {
            return taskIdx;
        } else {
            throw new NoSuchTaskException();
        }
    }
}
