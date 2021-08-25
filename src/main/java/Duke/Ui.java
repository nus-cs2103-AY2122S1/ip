package Duke;

public class Ui {
    private final String BORDER = "---------------------------------------------------";
    private final String GREETING = "Hello! I'm Duke, what can I do for you?";
    private final String FAREWELL = "Bye. Hope to see you again soon!";

    private void printDuke(String str) {
        System.out.printf(String.format("%s\n%s\n%s\n", BORDER, str, BORDER)
                .replaceAll("(?m)^", "\t"));
    }

    public void showWelcome() {
        printDuke(GREETING);
    }

    public void showFarewell() {
        printDuke(FAREWELL);
    }

    public void showMessage(String message) {
        printDuke(message);
    }
}
