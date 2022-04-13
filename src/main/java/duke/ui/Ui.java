package duke.ui;

public abstract class Ui {
    private static final String WELCOME_TEXT = "Hey there I'm Duke!\nHow can I help you today?";
    private static final String BYE_TEXT = "Bye! Hope to see you again!";

    public abstract void printMessage(String string);

    public void printGreeting() {
        printMessage(WELCOME_TEXT);
    }

    public void printGoodbye() {
        printMessage(BYE_TEXT);
    }
}
