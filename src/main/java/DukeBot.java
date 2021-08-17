import java.util.Scanner;

class DukeBot {
    private final Scanner sc;
    private final TaskList taskList;

    public DukeBot(Scanner sc) {
        this.sc = sc;
        this.taskList = new TaskList();
    }

    private String handleBye(String[] inputs) throws InvalidCommandException {
        if (inputs.length > 1) {
            throw new InvalidCommandException("Unknown command! Did you mean 'bye'?");
        }
        return "Bye. Hope to see you again soon!";
    }

    private String handleList(String[] inputs) throws InvalidCommandException {
        if (inputs.length > 1) {
            throw new InvalidCommandException("Unknown command! Did you mean 'list'?");
        }
        return taskList.toString();
    }

    private String handleDone(String[] inputs) throws InvalidCommandException {
        if (inputs.length < 2) {
            throw new InvalidCommandException("Which task would you like to mark as done?");
        }
        int taskNo;
        try {
            taskNo = Integer.parseInt(inputs[1]);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("I don't see a task number! >.<");
        }
        if (taskNo > taskList.size() || taskNo <= 0) {
            String msg = taskList.size() == 0
                    ? "You don't have any tasks!"
                    : "Invalid take number! Must be between 1 and " + taskList.size();
            throw new InvalidCommandException(msg);
        }
        return taskList.markAsDone(taskNo)
                ? "I've marked this task as done:\n  " + taskList.get(taskNo)
                : "Task already done.";
    }

    private String handleToDo(String[] inputs) throws InvalidCommandException {
        if (inputs.length < 2) {
            throw new InvalidCommandException("The description of a todo cannot be empty.");
        }
        Task toDo = new ToDo(inputs[1]);
        taskList.add(toDo);
        return "I've added:\n  " + toDo;
    }

    private String handleDeadline(String[] inputs) throws InvalidCommandException {
        if (inputs.length < 2) {
            throw new InvalidCommandException("The description of a deadline cannot be empty.");
        }
        String[] tokens = inputs[1].split(" /by ");
        if (tokens.length != 2) {
            throw new InvalidCommandException("Wrong deadline format! Requires <task name> /by <time>");
        }
        Task deadline = new Deadline(tokens[0], tokens[1]);
        taskList.add(deadline);
        return "I've added:\n  " + deadline;
    }

    private String handleEvent(String[] inputs) throws InvalidCommandException {
        if (inputs.length < 2) {
            throw new InvalidCommandException("The description of an event cannot be empty.");
        }
        String[] tokens = inputs[1].split(" /at ");
        if (tokens.length != 2) {
            throw new InvalidCommandException("Wrong event format! Requires <task name> /at <time>");
        }
        Task event = new Event(tokens[0], tokens[1]);
        taskList.add(event);
        return "I've added:\n  " + event;
    }

    private String getResponseToCommand(String[] inputs) throws InvalidCommandException {
        switch (inputs[0]) {
        case "bye":
            return handleBye(inputs);
        case "list":
            return handleList(inputs);
        case "done":
            return handleDone(inputs);
        case "todo":
            return handleToDo(inputs);
        case "deadline":
            return handleDeadline(inputs);
        case "event":
            return handleEvent(inputs);
        default:
            throw new InvalidCommandException("Unknown command!");
        }
    }

    public void start() {
        String greetings = "Hello! What can I do for you?\n";
        System.out.println(greetings);

        while (true) {
            String command = sc.nextLine();
            String[] inputs = command.split(" ", 2);   // split at the first white space

            String response;
            try {
                response = getResponseToCommand(inputs);
            } catch (InvalidCommandException e) {
                response = e.getMessage();
            }
            System.out.println(response + "\n");

            if (command.equals("bye")) {
                break;
            }
        }
    }
}
