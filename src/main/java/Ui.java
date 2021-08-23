public class Ui {
    private final String LINEBREAK = "_________________________________________\n";

    private StringBuilder message = new StringBuilder();

    // prints the message between two lines and resets the message
    public void print() {
        System.out.println(LINEBREAK + message + '\n' + LINEBREAK);
        resetMessage();
    }

    public void prompt() {
        System.out.println("How can I help you?");
    }

    // adds message to be printed in the specified color
    public void addMessage(String message, TextColor color) {
        this.message.append(color);
        this.message.append(message);
        this.message.append(TextColor.DEFAULT);
    }

    public void resetMessage() {
        message = new StringBuilder();
    }

    public void greetNewUser() {
        addMessage("Hello, I'm iP Man! How may I help you?\n", TextColor.DEFAULT);
        addMessage("Supported commands: ", TextColor.DEFAULT);
        for (Commands command : Commands.values()) {
            addMessage(command.getCommand().getCommandString() + ", ", TextColor.DEFAULT);
        }
        print();
    }

    public void greetReturningUser() {
        addMessage("Welcome back! How may I help you?\n", TextColor.DEFAULT);
        addMessage("Tasks in list:\n", TextColor.DEFAULT);
        Duke.taskList.list();
        print();
    }
}
