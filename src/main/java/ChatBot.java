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
    private final String BORDER = "-".repeat(150);

    private boolean isRunning;

    public ChatBot() {
        this.isRunning = true;
        this.inputHandler = new InputHandler();
        this.taskList = new TaskList();
    }

    public void start() {
        display(WELCOME_MESSAGE);
        while (isRunning) {
            try {
                String userInput = inputHandler.getInput();
                respond(userInput);
            } catch(UnsupportedCommandException | MalformedCommandException e){
                display(e.getMessage());
            }
        }
    }

    private void respond(String userInput) throws UnsupportedCommandException, MalformedCommandException {
        String command = userInput.split(" ", 2)[0];
        switch (command) {
            case EXIT_COMMAND:
                display(EXIT_MESSAGE);
                isRunning = false;
                break;
            case LIST_COMMAND:
                display(taskList.list());
                break;
            case DONE_COMMAND:
                display(taskList.markTaskDone(userInput));
                break;
            case ADD_TODO_COMMAND:
                Task task = Todo.create(userInput);
                display(taskList.add(task));
                break;
            case ADD_DEADLINE_COMMAND:
                Task deadlineTask = Deadline.create(userInput);
                display(taskList.add(deadlineTask));
                break;
            case ADD_EVENT_COMMAND:
                Task eventTask = Event.create(userInput);
                display(taskList.add(eventTask));
                break;
            default:
                throw new UnsupportedCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
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
