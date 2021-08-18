public class ChatBot {
    private final InputHandler inputHandler;
    private TaskList taskList;
    private final String EXIT_COMMAND = "bye";
    private final String LIST_COMMAND = "list";
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
        if (userInput.equals(EXIT_COMMAND)) {
            display(EXIT_MESSAGE);
            isRunning = false;
        } else if (userInput.equals(LIST_COMMAND)) {
            display(taskList.list());
        } else if (!userInput.equals("")) {
            display(taskList.add(userInput));
        }
    }

    private void display(String... messages) {
        printWithIndent(BORDER);
        for(String message : messages){
            printWithIndent(message);
        }
        printWithIndent(BORDER);
    }

    private void printWithIndent(String string) {
        System.out.println(" ".repeat(INDENT) + string);
    }
}
