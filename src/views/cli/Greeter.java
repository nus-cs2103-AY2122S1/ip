package views.cli;

public class Greeter {
    private static String welcomeMessage;

    static {
        init();
    }

    public static void init() {
        String hello = "Hello! I'm Duke";
        String query = "What can I do for you?";
        welcomeMessage = String.format("%s%s%s%s", hello, System.lineSeparator(), query, System.lineSeparator());
    }

    public void greet() {
        System.out.println(welcomeMessage);
    }
}
