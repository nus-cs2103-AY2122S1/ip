import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public enum Command {BYE, LIST, DONE, TODO, DEADLINE, EVENT, DELETE}

    private boolean active;
    private final List<Task> list;

    public Duke() {
        this.active = true;
        this.list = SaveHelper.readData();
    }

    public void executeCommand(String input) throws DukeException {
        String[] parsedInput = input.split(" ");
        Command command;
        try {
            command = Command.valueOf(parsedInput[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException("Invalid Command");
        }
        switch (command) {
            case BYE:
                this.terminate();
                break;
            case LIST:
                this.listTasks();
                break;
            case DONE:
                if (parsedInput.length <= 1) {
                    throw new TaskIndexOutOfBoundException("Missing Task Number!");
                }
                this.done(Integer.parseInt(parsedInput[1]) - 1);
                break;
            case TODO:
                this.addTodo(input);
                break;
            case DEADLINE:
                this.addDeadline(input);
                break;
            case EVENT:
                this.addEvent(input);
                break;
            case DELETE:
                if (parsedInput.length <= 1) {
                    throw new TaskIndexOutOfBoundException("Missing Task Number!");
                }
                this.delete(Integer.parseInt(parsedInput[1]) - 1);
                break;
            default:
                throw new InvalidCommandException("Invalid Command");
        }
        SaveHelper.writeData(list);
    }

    public void terminate() {
        this.active = false;
        String message =
                "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";
        System.out.println(message);
    }

    public void listTasks() {
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < this.list.size(); i++) {
            Task task = this.list.get(i);
            System.out.println(String.format(" %d.%s", i + 1, task.toString()));
        }
        System.out.println("____________________________________________________________\n");
    }

    /*public void addTask(String input) {
        this.list.add(new Task(input));
        String message =
                "____________________________________________________________\n" +
                " added: " + input + "\n" +
                "____________________________________________________________\n";
        System.out.println(message);
    }*/

    public void addTodo(String input) throws ToDoDescriptionNotFoundException {
    if (input.length() <= 5 || input.substring(5).stripLeading().length() <= 0) {
        throw new ToDoDescriptionNotFoundException("Missing Description!");
    }
        String toDo = input.substring(5);
        ToDo newToDo = new ToDo(toDo);
        this.list.add(newToDo);
        String message =
                "____________________________________________________________\n" +
                " Got it. I've added this task:\n   " +
                newToDo.toString() +
                String.format("\n Now you have %d tasks in the list.\n", this.list.size()) +
                "____________________________________________________________\n";
        System.out.println(message);
    }

    public void addDeadline(String input) throws DeadlineDescriptionNotFoundException ,DeadlineNotFoundException{
        if (input.length() <= 9 || input.substring(9).stripLeading().length() <= 0) {
            throw new DeadlineDescriptionNotFoundException("Missing Description!");
        } else if (!input.contains(" /by ")) {
            throw new DeadlineNotFoundException("Deadline Missing!");
        }
        int delimiter = input.indexOf(" /by ");
        String task = input.substring(9, delimiter);
        String date = input.substring(delimiter + 5);
        Deadline newDeadline = new Deadline(task, date);
        this.list.add(newDeadline);
        String message =
                "____________________________________________________________\n" +
                " Got it. I've added this task:\n   " +
                newDeadline.toString() +
                String.format("\n Now you have %d tasks in the list.\n", this.list.size()) +
                "____________________________________________________________\n";
        System.out.println(message);
    }

    public void addEvent(String input) throws EventDescriptionNotFoundException , EventTimeNotFoundException {
        if (input.length() <= 6 || input.substring(6).stripLeading().length() <= 0) {
            throw new EventDescriptionNotFoundException("Missing Description!");
        } else if (!input.contains(" /at ")) {
            throw new EventTimeNotFoundException("Event Time Missing!");
        }
        int delimiter = input.indexOf(" /at ");
        String task = input.substring(6, delimiter);
        String time = input.substring(delimiter + 5);
        Event newEvent = new Event(task, time);
        this.list.add(newEvent);
        String message =
                "____________________________________________________________\n" +
                " Got it. I've added this task:\n   " +
                newEvent.toString() +
                String.format("\n Now you have %d tasks in the list.\n", this.list.size()) +
                "____________________________________________________________\n";
        System.out.println(message);
    }

    public void done(int index) throws TaskIndexOutOfBoundException{
        if (index >= this.list.size()) {
            throw new TaskIndexOutOfBoundException("Task index is invalid!");
        }
        Task task = this.list.get(index);
        task.setCompleted();
        String message =
                "____________________________________________________________\n" +
                " Nice! I've marked this task as done:\n" +
                String.format("   [%s] %s\n", task.getCompletedMarker(), task.getTask()) +
                "____________________________________________________________\n";
        System.out.println(message);
    }

    public void delete(int index) throws TaskIndexOutOfBoundException{
        if (index >= this.list.size()) {
            throw new TaskIndexOutOfBoundException("Task index is invalid!");
        }
        Task task = this.list.get(index);
        this.list.remove(index);
        String message =
                "____________________________________________________________\n" +
                " Noted. I've removed this task:\n   " +
                task.toString() +
                String.format("\n Now you have %d tasks in the list.\n", this.list.size()) +
                "____________________________________________________________\n";
        System.out.println(message);
    }

    public void echo(String input) {
        String message =
                "____________________________________________________________\n" +
                " " + input + "\n" +
                "____________________________________________________________\n";
        System.out.println(message);
    }

    public void handleException(Exception e) {
        String message;
        if (e instanceof InvalidCommandException) {
            message =
                    "____________________________________________________________\n" +
                    " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                    "____________________________________________________________\n";
        } else if (e instanceof ToDoDescriptionNotFoundException) {
            message =
                    "____________________________________________________________\n" +
                    " ☹ OOPS!!! The description of a todo cannot be empty.\n" +
                    "____________________________________________________________\n";
        } else if (e instanceof EventDescriptionNotFoundException) {
            message =
                    "____________________________________________________________\n" +
                    " ☹ OOPS!!! The description of an event cannot be empty.\n" +
                    "____________________________________________________________\n";
        } else if (e instanceof DeadlineDescriptionNotFoundException) {
            message =
                    "____________________________________________________________\n" +
                    " ☹ OOPS!!! The description of a deadline cannot be empty.\n" +
                    "____________________________________________________________\n";
        } else if (e instanceof DeadlineNotFoundException) {
            message =
                    "____________________________________________________________\n" +
                    " ☹ OOPS!!! The date of the deadline cannot be empty.\n" +
                    "____________________________________________________________\n";
        } else if (e instanceof EventTimeNotFoundException) {
            message =
                    "____________________________________________________________\n" +
                    " ☹ OOPS!!! The time of an event cannot be empty.\n" +
                    "____________________________________________________________\n";
        } else if (e instanceof TaskIndexOutOfBoundException) {
            message =
                    "____________________________________________________________\n" +
                    " ☹ OOPS!!! The task number is invalid.\n" +
                    "____________________________________________________________\n";
        } else {
            message =
                    "____________________________________________________________\n" +
                    " ☹ OOPS!!! An unknown error has occurred!\n" +
                    "____________________________________________________________\n";
        }
        System.out.println(message);
    }

    public void run(Scanner sc) {
        String message =
                "____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.println(message);

        while (this.active) {
            try {
                String input = sc.nextLine();
                this.executeCommand(input);
            } catch (Exception e) {
                this.handleException(e);
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        Scanner sc = new Scanner(System.in);
        duke.run(sc);
    }
}

