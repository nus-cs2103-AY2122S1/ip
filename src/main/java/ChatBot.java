public class ChatBot {
    private final InputHandler inputHandler;
    private TaskList taskList;
    private final String EXIT_COMMAND = "bye";
    private final String LIST_COMMAND = "list";
    private final String DONE_COMMAND = "done";
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
        if (command.equals(EXIT_COMMAND)) {
            display(EXIT_MESSAGE);
            isRunning = false;
        } else if (command.equals(LIST_COMMAND)) {
            display(taskList.list());

        } else if (command.equals(DONE_COMMAND)){
            int taskIndex = Integer.valueOf(userInputSplit[1]);
            display(taskList.markTaskDone(taskIndex));
        }
        else if (!userInput.equals("")) {
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

    private String getCommand(String userInput) {
        return userInput.split(" ")[0];
    }
}
