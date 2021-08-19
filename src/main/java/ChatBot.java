public class ChatBot {
    private final InputHandler inputHandler;
    private TaskList taskList;
    private final String EXIT_COMMAND = "bye";
    private final String LIST_COMMAND = "list";
    private final String DONE_COMMAND = "done";
    private final String ADD_TODO_COMMAND = "todo";
    private final String ADD_DEADLINE_COMMAND = "deadline";
    private final String ADD_EVENT_COMMAND = "event";
    private final String WELCOME_MESSAGE = "Hello! I'm Duke. What can I do for you?";
    private final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    private final int INDENT = 4;
    private final String BORDER = "-".repeat(100);

    private boolean isRunning;

    public ChatBot() {
        this.isRunning = true;
        this.inputHandler = new InputHandler();
        this.taskList = new TaskList();
    }

    public void start() {
        display(WELCOME_MESSAGE);
        while (isRunning) {
            String userInput = inputHandler.getInput();
            respond(userInput);
        }
    }

    private void respond(String userInput) {
        String[] userInputSplit = userInput.split(" ", 2);
        String command = userInputSplit[0];
        switch (command) {
        case EXIT_COMMAND:
            display(EXIT_MESSAGE);
            isRunning = false;
            break;
        case LIST_COMMAND:
            display(taskList.list());
            break;
        case DONE_COMMAND:
            int taskIndex = Integer.valueOf(userInputSplit[1]);
            display(taskList.markTaskDone(taskIndex));
            break;
        case ADD_TODO_COMMAND:
            String description = userInputSplit[1];
            Task task = Todo.create(description);
            display(taskList.add(task));
            break;
        case ADD_DEADLINE_COMMAND:
            Task deadlineTask = Deadline.create(userInputSplit[1]);
            display(taskList.add(deadlineTask));
            break;
        case ADD_EVENT_COMMAND:
            Task eventTask = Event.create(userInputSplit[1]);
            display(taskList.add(eventTask));
            break;
        default:
            display("Command not supported");
        }
    }

    private void display(String... messages) {
        printWithIndent(BORDER);
        for(String message : messages){
            String[] newLineSeparated = message.split("\n");
            for(String line: newLineSeparated) {
                printWithIndent(line);
            }
        }
        printWithIndent(BORDER);
    }

    private void printWithIndent(String string) {
        System.out.println(" ".repeat(INDENT) + string);
    }
}
