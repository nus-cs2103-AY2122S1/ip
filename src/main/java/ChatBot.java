public class ChatBot {
    private final InputHandler inputHandler;
    private final String WELCOME_MESSAGE = "Hello! I'm Duke. What can I do for you?";
    private final String EXIT_COMMAND = "bye";
    private final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    private boolean isRunning;

    public ChatBot(InputHandler inputHandler) {
        this.isRunning = true;
        this.inputHandler = inputHandler;
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
        } else if (!userInput.equals("")) {
            display(userInput);
        }
    }

    private void display(String message) {
        System.out.println("    --------------------------------------------------------------------");
        System.out.println("    " + message);
        System.out.println("    --------------------------------------------------------------------");
    }
}
