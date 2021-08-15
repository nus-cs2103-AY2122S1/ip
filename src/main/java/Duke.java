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
     * Echo the command entered.
     *
     * @param command a command said by the user
     */
    public void echo(String command) {
        System.out.println(command);
    }

    /**
     * exit behavior.
     */
    public void bye() {
        String byeCommand = "Bye. Hope to see you again soon!";
        System.out.println(byeCommand);
    }

    /**
     * Add a task.
     *
     * @param task the task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        String output = "Added: " + task.toString();
        System.out.println(output);
        System.out.println("Now you have " + this.tasks.size() + " task" + ((tasks.size() <= 1) ? "" : "s") + " in the list");
    }

    /**
     * display a list of tasks added.
     */
    public void displayTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
    }


    private void markTaskAsDone(int taskIdx) {
        Task task = this.tasks.get(taskIdx);
        task.markAsDone();
        System.out.println(" Nice! I've marked this task as done: \n" + task);
    }

    private String getAction(String command) {
        return command.split(" ")[0];
    }

    /**
     * Process the command input by user.
     *
     * @param command command input of the user.
     */
    public void processCommand(String command) {
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
            case "done":
                try {
                    String task = this.getTask(command);
                    int taskIdx = Integer.parseInt(task);
                    if (taskIdx > 0 && taskIdx <= this.tasks.size()) {
                        this.markTaskAsDone(taskIdx - 1);
                    } else {
                        System.out.println("Task does not exist.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println(e);
                    break;
                }
                break;
            default:
                System.out.println("Invalid command");
                break;
        }
    }

    private String getTask(String command) {
        String action = this.getAction(command);
        return command.substring(action.length() + 1);
    }

    private ToDo createTodo(String command) {
        return new ToDo(command);
    }

    private DeadLine createDeadLine(String command) {
        String[] lst = command.split("/by ");
        String description = lst[0];
        String ddl = lst[1];
        return new DeadLine(description, ddl);
    }

    private Event createEvent(String command) {
        String[] lst = command.split("/at ");
        String description = lst[0];
        String period = lst[1];
        return new Event(description, period);
    }

}
