package duke.ui;

import java.util.Scanner;

public class Ui {
    private final String LINE = "____________________________________________________________";
    private final Scanner scanner = new Scanner(System.in);

    public void showLoadingError() {
        String message = "Oops, looks like there is a problem loading duke.Duke";
        System.out.println(message);
    }

    public void showWelcome() {
        String logo = """
                 ____        _       \s
                |  _ \\ _   _| | _____\s
                | | | | | | | |/ / _ \\
                | |_| | |_| |   <  __/
                |____/ \\__,_|_|\\_\\___|
                """;
        System.out.println(LINE);
        System.out.println(logo);
        System.out.println("Hi, I'm duke.Duke, your Personal Assistant Chatbot\n" +
                "What can I do for you today?");
        System.out.println(LINE);
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public String readInput() {
        return scanner.nextLine();
    }

    public void reply(String message) {
        System.out.println(message);
    }
}
